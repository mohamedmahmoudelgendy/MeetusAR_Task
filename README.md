# MeetusAR_Task
# MeetusAR Task Management API

A Spring Boot REST API for managing users and tasks. This implementation does **not** include security; user operations are handled via userId in endpoints.

---

## Table of Contents

* [Features](#features)
* [Technology Stack](#technology-stack)
* [Project Structure](#project-structure)
* [Configuration](#configuration)
* [Running the Project](#running-the-project)
* [API Endpoints](#api-endpoints)
* [Postman Tests](#postman-tests)
* [Notes](#notes)

---

## Features

* User registration with hashed passwords (BCrypt)
* Task CRUD operations
* MySQL database integration
* Organized packages for Controller, Service, Repository, Entity

---

## Technology Stack

* Java 17+
* Spring Boot 3+
* MySQL
* Maven
* Spring Data JPA
* BCrypt password encoder

---

## Project Structure

```
src/main/java/MeetusAR/Task
├── Controller
│   ├── AuthController.java
│   └── TaskController.java
├── Entity
│   ├── User.java
│   └── Task.java
├── Repository
│   ├── UserRepository.java
│   └── TaskRepository.java
├── Service
│   ├── UserService.java
│   └── TaskService.java
└── and more 
```

* `Controller`: REST endpoints for user and task operations
* `Service`: Business logic
* `Repository`: Database access
* `Entity`: Models for User and Task

---

## Configuration

**MySQL Database** (update `application.properties`):

```
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## Running the Project

1. Clone repository:

```bash
git clone https://github.com/mohamedmahmoudelgendy/MeetusAR_Task.git
cd MeetusAR_Task
```

2. Build and run:

```bash
mvn clean install
mvn spring-boot:run
```

3. Ensure MySQL is running and database exists.

---

## API Endpoints

### User Endpoints

**Register User**

`POST /auth/register`

Request Body:

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "mypassword"
}
```

Response:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$2a$10$..."
}
```

**Login User**

`POST /auth/login`

Request Body:

```json
{
  "email": "john@example.com",
  "password": "mypassword"
}
```

Response:

```json
"Login successful ✅"  // or "Invalid email or password ❌"
```

### Task Endpoints

**Create Task**

`POST /tasks/{userId}`

Request Body:

```json
{
  "title": "Finish project",
  "description": "Complete the Spring Boot assignment",
  "status": "open"
}
```

Response:

```json
{
  "id": 1,
  "title": "Finish project",
  "description": "Complete the Spring Boot assignment",
  "status": "open",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

**Get Tasks**

`GET /tasks/{userId}`

Response:

```json
[
  {
    "id": 1,
    "title": "Finish project",
    "description": "Complete the Spring Boot assignment",
    "status": "open",
    "user": {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com"
    }
  }
]
```

**Update Task**

`PUT /tasks/{userId}/{taskId}`

Request Body:

```json
{
  "title": "Finish project",
  "description": "Complete the Spring Boot assignment",
  "status": "done"
}
```

Response:

```json
{
  "id": 1,
  "title": "Finish project",
  "description": "Complete the Spring Boot assignment",
  "status": "done",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

**Delete Task**

`DELETE /tasks/{taskId}`

Response: `204 No Content`

---

## Postman Tests

1. **Register User** → `POST /auth/register`
2. **Login User** → `POST /auth/login`
3. **Create Task** → `POST /tasks/1`
4. **Get Tasks** → `GET /tasks/1`
5. **Update Task** → `PUT /tasks/1/1`
6. **Delete Task** → `DELETE /tasks/1`

> Replace `userId` and `taskId` with actual IDs from previous responses.

---

## Notes

* MySQL is used instead of H2.
* No authentication implemented; user identification is via path variables.
* Passwords are hashed during registration.
* Clean package structure: Controller, Service, Repository, Entity.

---

## License

MIT License
