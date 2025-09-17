# Freelance Marketplace

A microservices-based freelance marketplace platform built with Spring Boot that connects clients with freelancers.

## 🏗️ Architecture

This project follows a **microservices architecture** with the following components:

- **gRPC Communication**: Inter-service communication using Protocol Buffers (protobuf)
- **Message Queue**: RabbitMQ for asynchronous messaging between services
- **Caching**: Redis for session management and caching
- **Database**: PostgreSQL with Hibernate JPA for data persistence
- **Authentication**: JWT-based authentication and authorization

## 🛠️ Tech Stack

- **Framework**: Spring Boot
- **Communication**: gRPC with Protocol Buffers
- **Message Broker**: RabbitMQ
- **Cache**: Redis
- **Database**: PostgreSQL
- **ORM**: Hibernate JPA
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Redis 6+
- RabbitMQ 3.8+
- Protocol Buffers Compiler (protoc)

## 🚀 Installation

1. **Clone the repository**
   ```
   git clone https://github.com/Masih-Roughani/Freelance-Marketplace.git
   cd Freelance-Marketplace
   ```

2. **Install dependencies**
   ```
   mvn clean install
   ```

3. **Setup Database**
   ```sql
   CREATE DATABASE freelance_marketplace;
   ```

4. **Start Required Services**
   - Start PostgreSQL
   - Start Redis  
   - Start RabbitMQ

5. **Configure Environment Variables**
   Set the following environment variables:
   - `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/freelance_marketplace`
   - `SPRING_DATASOURCE_USERNAME=your_username`
   - `SPRING_DATASOURCE_PASSWORD=your_password`
   - `SPRING_REDIS_HOST=localhost`
   - `SPRING_REDIS_PORT=6379`
   - `SPRING_RABBITMQ_HOST=localhost`
   - `SPRING_RABBITMQ_PORT=5672`
   - `SPRING_RABBITMQ_USERNAME=guest`
   - `SPRING_RABBITMQ_PASSWORD=guest`
   - `JWT_SECRET=your_jwt_secret_key`
   - `JWT_EXPIRATION=86400000`

6. **Generate Protocol Buffers (if needed)**
   ```
   mvn protobuf:compile
   mvn protobuf:compile-custom
   ```

7. **Run the application**
   ```
   mvn spring-boot:run
   ```

## 🔧 Configuration

### application.yml
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/freelance_marketplace
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver
    
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        
  redis:
    host: localhost
    port: 6379
    
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000

grpc:
  server:
    port: 9090
```

## 🔗 gRPC Services

The application uses gRPC for inter-service communication with Protocol Buffers:

Generate protobuf classes: `mvn protobuf:compile` and `mvn protobuf:compile-custom`

## 📦 Maven Dependencies

Key dependencies included:

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-amqp` (RabbitMQ)
- `spring-boot-starter-data-redis`
- `spring-boot-starter-security`
- `postgresql`
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` (JWT)
- `grpc-spring-boot-starter`
- `protobuf-java`

## 🗄️ Database Schema

Using Hibernate JPA with PostgreSQL for data persistence. Entity relationships managed through JPA annotations.

## 🔐 Authentication & Authorization

- **JWT-based authentication** for secure user sessions
- **Role-based authorization** for different user types
- **Redis caching** for session management

## 🐰 Message Queue

RabbitMQ handles asynchronous communication between microservices for:
- Event-driven architecture
- Decoupled service communication
- Reliable message delivery

## 🚀 Deployment

To deploy the application:

1. Package the application: `mvn clean package`
2. Run the JAR file: `java -jar target/freelance-marketplace-*.jar`

## 📁 Project Structure

```
Freelance-Marketplace/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/yourpackage/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── controller/      # REST controllers
│   │   │       ├── service/         # Business logic
│   │   │       ├── repository/      # JPA repositories
│   │   │       ├── entity/          # JPA entities
│   │   │       ├── grpc/            # gRPC services
│   │   │       └── security/        # JWT & Security config
│   │   ├── resources/
│   │   │   ├── application.yml
│   │   │   └── proto/               # Protocol buffer definitions
│   │   └── proto/                   # .proto files
├── target/                          # Maven build output
├── pom.xml                          # Maven configuration
└── README.md
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👨‍💻 Author

**Masih Roughani**
- GitHub: [@Masih-Roughani](https://github.com/Masih-Roughani)

---

Built with Spring Boot microservices architecture using gRPC, RabbitMQ, Redis, and PostgreSQL.
