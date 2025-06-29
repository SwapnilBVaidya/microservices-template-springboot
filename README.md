# 🧰 Microservices Template with Spring Boot

This project is a modular microservices architecture built using **Spring Boot 3.5**, **Spring Cloud 2025.0.0**, and **Docker**. It demonstrates the integration of multiple Spring Cloud components including:

- ✅ **Spring Cloud Config Server** (Centralized configuration)
- ✅ **Eureka Server** (Service Discovery)
- ✅ **Spring Cloud API Gateway** (Routing and load balancing)
- ✅ Modular services: `user-service`, `product-service`, `order-service`

---

## 🏗️ Architecture Overview

```text
                +-------------+
                |  API-Gateway|
                +------+------+
                       |
        +--------------+--------------+
        |              |              |
+---------------+ +---------------+ +---------------+
| user-service  | | product-service | | order-service  |
+---------------+ +----------------+ +----------------+
        \                 |                 /
         \                |                /
          \         +-----------------+   /
           +------->|  Config Server  |<--+
                    +-----------------+
                          |
                   [ config-repo folder ]

```
---

## 📌 Table of Contents

- [Spring Cloud Config Server](#spring-cloud-config-server)
- [Eureka Service Discovery](#eureka-service-discovery)
- [Spring Cloud API Gateway](#spring-cloud-api-gateway)
- [Microservices Included](#microservices-included)
- [Docker for PostgreSQL & PgAdmin](#docker-for-postgresql--pgadmin)
- [How to Run the Project](#how-to-run-the-project)
- [Directory Structure](#directory-structure)
- [Author](#author)
- [License](#license)

## 🌐 Spring Cloud Config Server

Spring Cloud Config Server centralizes configuration for all services and supports dynamic configuration updates.
### 🛠 Configuration

In your `config-server/src/main/resources/application.yml`:

```yaml
server:
  port: 8888

spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          uri: file://<path-to-your-config-repo>   # Use a Git repo in production
```

## 🧭 Eureka Service Discovery
Eureka acts as a registry where all services register themselves. This allows services to discover and communicate with each other using logical names.
### 🛠 Configuration
```yaml
spring:
  application:
    name: user-service  # Replace with actual service name

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
```
### 🌐 Eureka Dashboard
Access the Eureka UI:
📍 http://localhost:8761

## 🚪 Spring Cloud API Gateway
The API Gateway handles request routing, load balancing, and acts as the single entry point to the system.

### 🛠 Gateway Config (api-gateway/src/main/resources/application.yml)
```yaml
server:
  port: 8080

spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
        - id: product-service
          uri: lb://product-service
          predicates:
            - Path=/api/products/**
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**

```
### ✅ Example Endpoints via Gateway
GET http://localhost:8080/api/users/1

GET http://localhost:8080/api/products/1

GET http://localhost:8080/api/orders/1


## 🔧 Microservices Included
Service Name	Port	Description
config-server	8888	Centralized configuration management
eureka-server	8761	Service discovery registry
api-gateway	    8080	API routing & entry point
user-service	8081	Manages user data
product-service	8082	Manages products
order-service	8083	Manages orders

## 🐳 Docker for PostgreSQL & PgAdmin
Use the following Docker Compose file to run PostgreSQL and PgAdmin locally:
```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15
    container_name: postgres-db
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: dev-database
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  pgadmin:
    image: dpage/pgadmin4
    container_name: pgadmin
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@local.com
      PGADMIN_DEFAULT_PASSWORD: admin
    ports:
      - "5050:80"
    depends_on:
      - postgres

volumes:
  postgres_data:
```
Access PgAdmin at:
📍 http://localhost:5050
Login with admin@local.com / admin

## 🚀 How to Run the Project
Clone the repository
```bash
git clone <your-repo-url>
cd microservices-template-springboot
```
Start Config Server
```bash
cd config-server
./gradlew bootRun
```
Start Eureka Server
```bash
cd eureka-server
./gradlew bootRun
```
Start Core Services
```bash
cd user-service && ./gradlew bootRun
cd product-service && ./gradlew bootRun
cd order-service && ./gradlew bootRun
```
Start API Gateway
```bash
cd api-gateway
./gradlew bootRun
```

Test APIs through the gateway:

curl http://localhost:8080/api/users/1

curl http://localhost:8080/api/products/1

curl http://localhost:8080/api/orders/1

## 📁 Directory Structure
```text
microservices-template-springboot/
├── api-gateway/          --> API Gateway
├── config-server/        --> Central Config Server
├── eureka-server/        --> Eureka Discovery Server
├── user-service/         --> Manages user data
├── product-service/      --> Product catalog
├── order-service/        --> Order processing
└── config-repo/          --> Shared config files
```

## 👨‍💻 Author
Swapnil Vaidya
Backend Engineer | 7+ years of experience
Tech: Java, Spring Boot, AWS, Kafka, PostgreSQL, Microservices

## 📝 License
This project is licensed under the MIT License.

---