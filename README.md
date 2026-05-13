# Voting Session API

API for creating voting sessions and registering votes by CPF. Each CPF can vote only once per session.

Live deployment: https://voting-session-api.onrender.com/

## Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Maven
- Angular 20
- TypeScript
- JUnit and Mockito

## Features

- Voting session creation
- Voting session listing
- Active session filtering
- CPF-based voting
- One-vote-per-CPF rule for each session
- Automatic vote counting
- Global exception handling

## API Overview

- `POST /api/sessions` - create session
- `GET /api/sessions` - list sessions
- `GET /api/sessions/activated` - list active sessions
- `POST /api/votes/{cpf}/sessions/{id}` - vote in a session

## Running Locally

Requirements: Java 21+, Maven, Node.js, npm and Angular CLI.

Run the backend:

```bash
cd backend
./mvnw spring-boot:run
```

On Windows:

```bash
cd backend
mvnw.cmd spring-boot:run
```

Run the frontend:

```bash
cd frontend
npm install
npm start
```

URLs:

```text
Backend:  http://localhost:8080
Frontend: http://localhost:4200
```

For local backend execution, configure a PostgreSQL database named `voting_session_api` or set the datasource environment variables.

## Testing

```bash
cd backend
./mvnw test
```

## Project Structure

```text
backend/
|-- api
|-- votingsession
|-- vote
|-- member
|-- exception
`-- infrastructure

frontend/     Angular application
```

## Author

Matheus Silva  
GitHub: [MathSilvaDev](https://github.com/MathSilvaDev)
