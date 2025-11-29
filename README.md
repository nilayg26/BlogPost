# Blogging Platform System by Nilay using SpringBoot & Java

A backend REST API for a blogging platform built with **Spring Boot**. 
This system supports role-based access control, content creation, tagging, and a comment moderation system.

## DBMS Schema
![Image](./assets/dbmsSchema.png)

## System Design
![Image](./assets/arch_schema.png)

## 🚀 Technologies Used
* **Java 17 / 21**
* **Spring Boot 4** (Web, Data JPA)
* **H2 Database** (In-Memory SQL DB)
* **JUnit 5 & Mockito** (Testing)
* **Maven** (Build Tool)

## ⚙️ Setup & Installation

### 1. Prerequisites
* Java Development Kit (JDK) 17 or higher installed.
* Maven installed (or use the included `./mvnw` wrapper).

### 2. Run Locally
1.  Clone the repository using git clone command.
2.  Open the folder in IDE.
3.  Open a terminal in the project root.
4.  Run the application:
    ```bash
    ./mvnw clean spring-boot:run
    ```
5. On a new Terminal Window you can run CURL commands to verify

   ![Image](./assets/output.png)
# Sample Input/Ouput Step by step

## Create User
```bash
curl -X POST "http://localhost:8080/api/users" \
  -H "Content-Type: application/json" \
  -d '{"name": "Nilay", "username": "nilay_dev", "password": "123", "role": "AUTHOR"}'
```

**Response:**

```json
{
  "id": 1,
  "name": "Nilay",
  "password": "123",
  "role": "AUTHOR",
  "username": "nilay_dev"
}
```

---

## Create Post

```bash
curl -X POST "http://localhost:8080/api/posts?userId=1" \
  -H "Content-Type: application/json" \
  -d '{"title": "System Design is Hard", "blogBody": "But I finally got it working.", "tags": ["java", "stress"]}'
```

**Response:**

```json
{
  "blogBody": "But I finally got it working.",
  "id": 1,
  "likes": 0,
  "tags": ["java","stress"],
  "title": "System Design is Hard",
  "user": {
    "id": 1,
    "name": "Nilay",
    "password": "123",
    "role": "AUTHOR",
    "username": "nilay_dev"
  }
}
```

---

## Add Comment

```bash
curl -X POST "http://localhost:8080/api/comments/add?postId=1&userId=1" \
  -H "Content-Type: text/plain" \
  -d "This is a test comment."
```

**Response:**

```json
{
  "blogPost": {
    "blogBody": "But I finally got it working.",
    "id": 1,
    "likes": 0,
    "tags": ["java","stress"],
    "title": "System Design is Hard",
    "user": {
      "id": 1,
      "name": "Nilay",
      "password": "123",
      "role": "AUTHOR",
      "username": "nilay_dev"
    }
  },
  "commentBody": "This is a test comment.",
  "id": 1,
  "status": "PENDING",
  "user": {
    "id": 1,
    "name": "Nilay",
    "password": "123",
    "role": "AUTHOR",
    "username": "nilay_dev"
  }
}
```

---

## Approve Comment

```bash
curl -X PUT "http://localhost:8080/api/comments/1/approve"
```

---

## Get Comments for Post

```bash
curl -X GET "http://localhost:8080/api/comments/post/1"
```

**Response:**

```json
[
  {
    "blogPost": {
      "blogBody": "But I finally got it working.",
      "id": 1,
      "likes": 0,
      "tags": ["java","stress"],
      "title": "System Design is Hard",
      "user": {
        "id": 1,
        "name": "Nilay",
        "password": "123",
        "role": "AUTHOR",
        "username": "nilay_dev"
      }
    },
    "commentBody": "This is a test comment.",
    "id": 1,
    "status": "APPROVED",
    "user": {
      "id": 1,
      "name": "Nilay",
      "password": "123",
      "role": "AUTHOR",
      "username": "nilay_dev"
    }
  }
]
```
 
---

## 🧪 How to Run Tests
To execute the unit tests (JUnit):

**Using Terminal:**
```bash
./mvnw test
