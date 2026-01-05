# Payment Processing

A small Spring Boot payment-processing service example demonstrating a layered architecture with DTOs, service, domain models, repository and a simple in-memory idempotency adapter.

**Tech stack:**
- **Language:** Java 25
- **Framework:** Spring Boot (Maven)
- **Build:** Maven (includes `mvnw` wrapper)

**Project layout (key files):**
- [src/main/java/com/mglvarella/paymentprocessing/PaymentProcessingApplication.java](src/main/java/com/mglvarella/paymentprocessing/PaymentProcessingApplication.java)
- [src/main/java/com/mglvarella/paymentprocessing/api/controller/PaymentController.java](src/main/java/com/mglvarella/paymentprocessing/api/controller/PaymentController.java)
- [src/main/java/com/mglvarella/paymentprocessing/application/service/PaymentService.java](src/main/java/com/mglvarella/paymentprocessing/application/service/PaymentService.java)
- [src/main/java/com/mglvarella/paymentprocessing/domain/payment/repository/PaymentRepository.java](src/main/java/com/mglvarella/paymentprocessing/domain/payment/repository/PaymentRepository.java)
- [src/main/java/com/mglvarella/paymentprocessing/infraestructure/cache/InMemoryIdempotencyAdapter.java](src/main/java/com/mglvarella/paymentprocessing/infraestructure/cache/InMemoryIdempotencyAdapter.java)
- DTOs: [src/main/java/com/mglvarella/paymentprocessing/api/dto](src/main/java/com/mglvarella/paymentprocessing/api/dto)

**What it does**
- Exposes a payment API via `PaymentController` that accepts payment requests, maps DTOs to domain objects, delegates business logic to `PaymentService`, and persists via `PaymentRepository`.
- Includes a simple in-memory idempotency storage adapter to demonstrate idempotent request handling.

Getting started

Prerequisites
- Java 25 installed
- Git (optional)

Build and run (from repository root)

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run
```

By default the application runs on port 8080 (configurable via `src/main/resources/application.properties`).

API
- The primary HTTP entrypoint is implemented in `PaymentController`.
- Common DTOs live in `api/dto` and include `PaymentRequestDTO`, `PaymentResponseDTO`, `MoneyRequestDTO`, and `PaymentStatusResponseDTO`.

Domain
- Domain models are in `domain/payment/model` and include `Payment`, `Money`, `PaymentMethod`, and `PaymentStatus`.
- A `PaymentFactory` exists to create domain `Payment` instances according to business rules.

Idempotency
- The `IdempotencyStorage` port and `InMemoryIdempotencyAdapter` provide a simple example of storing idempotency keys and results to avoid duplicate processing.

Tests
- Basic tests (if any) are in `src/test/java` and can be run with:

```bash
./mvnw test
```

Next steps and suggestions
- Extend `PaymentRepository` with a persistent implementation (JPA, JDBC).
- Add integration tests for controller/service flows.
- Externalize configuration for environments and add health checks.

License
- No license specified. Add a `LICENSE` file if you plan to open-source this project.

Contact
- For questions, inspect the controllers and services listed above and run the app locally.
