package com.mglvarella.paymentprocessing.infraestructure.cache;

import com.mglvarella.paymentprocessing.api.dto.PaymentResponseDTO;
import com.mglvarella.paymentprocessing.application.ports.IdempotencyStorage;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class InMemoryIdempotencyAdapter implements IdempotencyStorage {

    private final Map<String, PaymentResponseDTO> responses = new ConcurrentHashMap<>();
    private final Set<String> processingKeys = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public boolean tryLock(String key, Duration timeout) {
        if (responses.containsKey(key)) return false;

        boolean locked = processingKeys.add(key);
        if (locked) {
            scheduler.schedule(() -> processingKeys.remove(key), timeout.toMillis(), TimeUnit.MILLISECONDS);
        }
        return locked;
    }

    @Override
    public void saveResponse(String key, PaymentResponseDTO response, Duration timeout) {
        responses.put(key, response);
        processingKeys.remove(key);

        scheduler.schedule(() -> responses.remove(key), timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public Optional<PaymentResponseDTO> getResponse(String key) {
        return Optional.ofNullable(responses.get(key));
    }
}
