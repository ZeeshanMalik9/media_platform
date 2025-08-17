

# Media Platform Service Backend

A secure and scalable backend service for a media platform, built with Java and Spring Boot. This service allows administrative users to manage media assets and generate secure, short-lived streaming links.

---

## ✨ Features

-   **Secure Authentication:** User signup and login functionality using JSON Web Tokens (JWT).
-   **Protected Routes:** API endpoints for media management are secured and accessible only by authenticated admin users.
-   **Media Management:** Add metadata for media assets (video/audio).
-   **Secure Streaming Links:** Generate temporary (10-minute) secure URLs for media streaming to prevent unauthorized access.
-   **Relational Database:** Uses MySQL for persistent data storage, managed by Spring Data JPA.
-   **Organized Architecture:** Follows a standard RESTful API design with a clear separation of concerns (controllers, services, repositories).

---

## 🛠️ Tech Stack

-   **Backend:** Java 17, Spring Boot 3
-   **Database:** MySQL
-   **Authentication:** Spring Security, JSON Web Tokens (JWT)
-   **ORM:** Spring Data JPA (Hibernate)
-   **Build Tool:** Maven
-   **Utilities:** Lombok

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed on your local machine:
-   JDK (Java Development Kit) 17 or newer
-   Apache Maven
-   MySQL Server
-   An API testing tool like [Postman](https://www.postman.com/downloads/)

---

## 🚀 Getting Started

Follow these instructions to get the project up and running on your local machine.

### 1. Clone the Repository
```bash
git clone [https://github.com/your-username/media-platform-backend.git](https://github.com/your-username/media-platform-backend.git)
cd media-platform-backend


### 2\. Configure the Database

1.  Open your MySQL client and create a new database.
    ```sql
    CREATE DATABASE media_platform_db;
    ```
2.  Navigate to `src/main/resources/application.properties`.
3.  Update the database connection and JWT secret properties with your local configuration.
    ```properties
    # MySQL Database Connection Settings
    spring.datasource.url=jdbc:mysql://localhost:3306/media_platform_db
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password

    # JWT Secret Key - USE A LONG, RANDOM, AND SECURE STRING HERE
    jwt.secret=a-very-long-and-secure-secret-key-that-is-at-least-256-bits-long
    ```
    > **⚠️ Security Warning:** The `jwt.secret` should be a strong, unguessable string. Do not commit your actual secret to a public repository. It is recommended to use environment variables for sensitive data in a production environment.

### 3\. Build and Run the Application

You can run the application using the Maven wrapper included in the project.

  - **On macOS/Linux:**
    ```bash
    ./mvnw spring-boot:run
    ```
  - **On Windows:**
    ```bash
    mvnw.cmd spring-boot:run
    ```

The application will start on `http://localhost:8080`. Spring Data JPA will automatically create the necessary tables in your database on the first run.

-----

## 🔌 API Endpoints

The base URL for all endpoints is `http://localhost:8080`.

| Method | Endpoint                       | Protection | Description                                       |
| :----- | :----------------------------- | :--------- | :------------------------------------------------ |
| `POST` | `/auth/signup`                 | **Public** | Registers a new administrative user.              |
| `POST` | `/auth/login`                  | **Public** | Authenticates a user and returns a JWT.           |
| `POST` | `/media`                       | **Protected** | Adds metadata for a new media asset.              |
| `GET`  | `/media/{id}/stream-url`       | **Protected** | Generates a secure, 10-minute streaming URL.      |

### Example Payloads

#### `POST /auth/signup`

**Request Body:**

```json
{
    "email": "admin@example.com",
    "password": "strongpassword123"
}
```

**Success Response:** `200 OK`, `User registered successfully`

-----

#### `POST /auth/login`

**Request Body:**

```json
{
    "email": "admin@example.com",
    "password": "strongpassword123"
}
```

**Success Response:** `200 OK`

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

-----

#### `POST /media`

**Headers:** `Authorization: Bearer <your_jwt_token>`
**Request Body:**

```json
{
    "title": "My First Podcast Episode",
    "type": "AUDIO",
    "fileUrl": "/media/audio/episode1.mp3"
}
```

**Success Response:** `200 OK`

```json
{
    "id": 1,
    "title": "My First Podcast Episode",
    "type": "AUDIO",
    "fileUrl": "/media/audio/episode1.mp3",
    "createdAt": "2025-08-17T18:30:00.12345Z"
}
```

-----

#### `GET /media/{id}/stream-url`

**Headers:** `Authorization: Bearer <your_jwt_token>`
**Success Response:** `200 OK`

```json
{
    "secureUrl": "/api/v1/stream/play?token=eyJhbGciOiJIUzI1NiJ9..."
}
```

-----

## 📂 Project Structure

The project follows a standard layered architecture to ensure separation of concerns.

```
src/main/java/com/mediaplatform/
├── controller/   # API endpoints (REST controllers)
├── dto/          # Data Transfer Objects for API requests/responses
├── model/        # JPA Entity classes (database tables)
├── repository/   # Spring Data JPA repositories for DB access
├── security/     # JWT utilities, security configuration, filters
├── service/      # Business logic
└── MediaPlatformBackendApplication.java # Main application entry point
```

```
```