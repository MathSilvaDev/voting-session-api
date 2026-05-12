# Voting Session API

Simple voting session API where users can create 
sessions and vote using CPF. Each CPF can vote only 
once per session.

> Backend-focused project built with Spring Boot. Frontend (Angular) only consumes the API.

---

## Technologies
* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Angular
* TypeScript
* HTML / SCSS
* Node.js
* npm
* JUnit
* Mockito

---

## Requirements

Make sure you have installed:

* Java 21+
* Maven
* Node.js
* npm
* Angular CLI

---

## Features
* Create voting sessions 
* List all sessions 
* List active sessions 
* Vote in a session (YES / NO)
* CPF-based voting (1 vote per session)
* Automatic vote counting 
* Global exception handling

---

## API Endpoints

### Voting Sessions
* POST /api/sessions → Create session
* GET /api/sessions → List all sessions
* GET /api/sessions/activated → List active sessions

### Votes
* POST /api/votes/{cpf}/sessions/{id} → Vote in a session

---

## Running the Project

### 1. Run Backend (in backend folder)

Before running the backend, configure PostgreSQL connection values.
For local development, the application expects a PostgreSQL database named `voting_session_api` unless environment variables override it.

```
./mvnw spring-boot:run
```

API will be available at:

```
http://localhost:8080
```

### 2. Run Frontend (in frontend folder)


```
npm install
```

```
ng serve --open
```

Frontend will be available at:

```
http://localhost:4200
```

---

## Database

* PostgreSQL database
* Production database: Neon PostgreSQL
* The datasource is configured with environment variables:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://host/database?sslmode=require&channel_binding=require
SPRING_DATASOURCE_USERNAME=database_user
SPRING_DATASOURCE_PASSWORD=database_password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
```

The Hibernate dialect is not configured explicitly. Spring Boot detects PostgreSQL automatically.

---

## Deployment

The application is prepared for deployment on Render using Docker:

* Frontend: Angular production build served by Spring Boot static resources
* Backend: Spring Boot running in a Docker container
* Database: Neon PostgreSQL

Required production environment variables:

```
PORT=8080
SPRING_DATASOURCE_URL=jdbc:postgresql://host/database?sslmode=require&channel_binding=require
SPRING_DATASOURCE_USERNAME=database_user
SPRING_DATASOURCE_PASSWORD=database_password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
```

---

## Tests

* Unit tests implemented

---

## Notes

* CPF is normalized before saving
* Each CPF can vote only once per session (database constraint)
* Sessions have start and end time validation
* Default session duration: 60 seconds (if not provided)

---

## Author

Matheus Silva<br>
GitHub: [MathSilvaDev](https://github.com/MathSilvaDev)
