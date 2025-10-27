# springboot-docker-demo

## Overview
A simple Spring Boot REST service for transactions, containerized with Docker and run locally with Docker Compose (MySQL). Built as part of a FinTech-focused portfolio to demonstrate containerization workflows and event-ready architecture foundations.

## Tech
- Java 17, Spring Boot
- MySQL
- Docker, Docker Compose
- Maven

## Run locally (Docker)
1. docker-compose up --build
2. API: POST http://localhost:8080/api/transactions
   Example:
   `curl -X POST localhost:8080/api/transactions -H "Content-Type: application/json" -d '{"userId":"u1","amount":100,"currency":"USD"}'`

## What this demonstrates
- Containerized Spring Boot app and local DB for dev
- Basic transaction processing and validation (fraud rule)
- Clean README + run steps suitable for interviews / portfolio

## Next steps (planned)
- Add Kafka producer/consumer for asynchronous processing
- Deploy container to AWS ECS
- Add integration tests (Testcontainers)
