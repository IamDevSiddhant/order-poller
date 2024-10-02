# Order Polling Service

### Part of the Spring Boot Microservices Project

The **Order Polling Service** is a microservice responsible for polling the outbox table for new order events and publishing them to an external messaging broker (e.g., Kafka or RabbitMQ). This service ensures reliable communication between microservices by implementing the **Transactional Outbox Design Pattern**.

---

## 🛠️ **Technologies Used**

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Hibernate**
- **MySQL** (as the database)
- **Kafka** (for event processing)

---

## 🕒 **Service Functionality**

The **Order Polling Service** performs the following tasks:

1. **Outbox Polling**: Periodically polls the outbox table for new events.
2. **Event Processing**: Once a new event is detected, it processes the event and publishes it to the message broker (Kafka or RabbitMQ).
3. **Transactional Guarantees**: Ensures that events are reliably processed and published.

---

## 🗃️ **Outbox Table Schema**

This service works with the outbox table, which stores pending events for publishing.

### Outbox Table

| Column        | Type          | Description                         |
|---------------|---------------|-------------------------------------|
| `eventId`     | `BIGINT`      | Unique identifier for the event     |
| `aggregateId` | `VARCHAR(255)`| ID of the aggregate (e.g., Order ID)|
| `eventType`   | `VARCHAR(255)`| Type of event (e.g., OrderCreated)  |
| `payload`     | `TEXT`        | Event data (serialized JSON)        |
| `status`      | `VARCHAR(255)`| Status of the event (PENDING, SENT) |
| `createdAt`   | `DATETIME(6)` | Event creation timestamp            |

The `Order Polling Service` queries this table to check for `PENDING` events, processes them, and updates the status to `SENT` after successful publishing.

---

## ⚙️ **Configuration**

### Application Properties

The following properties can be configured in the `application.yml` or `application.properties` file for the **Order Polling Service**:

| Property                        | Description                                  | Default Value |
|----------------------------------|----------------------------------------------|---------------|
| `polling.interval`               | Time interval for polling the outbox table   | `5000ms`      |
| `kafka.bootstrap-servers`        | Kafka broker addresses                       | `localhost:9092` |
| `spring.datasource.url`          | Database connection URL                      | `jdbc:mysql://localhost:3306/orders` |
| `spring.datasource.username`     | Database username                            | `root`        |
| `spring.datasource.password`     | Database password                            | `password`    |

---

## 🏃 **Running the Service**

Follow these steps to run the **Order Polling Service**:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/IamDevSiddhant/order-poller.git
    ```

## ✍🏻 Author - SIDDHANT PAWAR