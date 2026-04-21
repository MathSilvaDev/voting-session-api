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
* H2 Database (in-memory)
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

* H2 in-memory database
* Access console:

```
http://localhost:8080/h2-console
```

### Default Configuration:

* JDBC URL: jdbc:h2:mem:testdb
* User: sa
* Password: (empty)

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

Matheus R.M Silva