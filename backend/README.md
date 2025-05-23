# Tuul Scooter Backend

This project is a backend service for a scooter rental platform, built as a technical assignment. It implements user registration and authentication, scooter reservations, and cost calculations. The project uses **Clean Architecture** and **Layered Architecture** principles to ensure scalability and maintainability.

## Features

- 🚀 Built with **Spring Boot 3.4.5** and **Java 17**
- 🔒 JWT-based authentication
- 🛴 Reservation API with cost calculation
- 📂 Layered folder structure: `controller`, `service`, `command`, `model`, `row`
- 📚 Auto-generated API documentation at:  
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Technologies

- Spring Boot
- Spring Security
- Firebase Firestore (NoSQL)
- JSON Web Tokens (JJWT)
- Lombok
- OpenAPI / Swagger UI

## Firestore Configuration

This project uses **Firebase Firestore** as the primary database (NoSQL).  
⚠️ Your `application.yml` file must contain valid Firebase credentials.

Demo (Author demo-credentials) credentials will be auto-copied from `application.example.yml` when you first run the project.

## How to Run the Project

### Requirements

- Java 17
- Gradle (you can use `./gradlew`)

### Run Locally

```bash
./gradlew bootRun
```

This will:

- Create `application.yml` from the example if it doesn't exist
- Start the backend server on `http://localhost:8080`

## API Documentation

After the app is running, open:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## What's Missing (yet planned)

- ❌ Proper logging (only basic at the moment)
- ❌ Testing (unit, integration)
- ❌ Caching (to improve performance)
- ❌ Event-driven architecture (for async logic like payment, analytics)

---

> **Note from the author:**  
> I really enjoyed building this project! It was genuinely fun and interesting.  
> If it weren't for the time constraints of the assignment, I could have kept adding more and more features — there's so much potential! 😄
