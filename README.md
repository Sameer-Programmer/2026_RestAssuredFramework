# REST Assured API Automation Framework

## Overview

This project is a **Hybrid/Data-Driven REST Assured API Automation Framework** developed using **Java, REST Assured, TestNG, Maven, Apache POI, Extent Reports, and Log4j2**.

The framework is designed to achieve:

* **Reusability** – Common methods and utilities can be reused across multiple test cases.
* **Maintainability** – URLs, payloads, test data, and configurations are maintained separately.
* **Readability** – Organized project structure with modular design and meaningful logging.

---

# Framework Objectives

* Reusability
* Maintainability
* Readability

---

# Framework Development Phases

## Phase 1 – Understanding Requirements

Before automation starts, understand the API specifications.

* Functional Requirements
* Swagger/OpenAPI Documentation

---

## Phase 2 – Choose Automation Tool

Technology Stack:

* Java
* REST Assured Library
* Maven
* TestNG

---

## Phase 3 – Framework Design

Design a modular Hybrid/Data-Driven Framework.

Separate the project into:

* Endpoints
* Payloads
* Tests
* Utilities
* Reports
* Logs

---

## Phase 4 – Framework Development

Develop reusable components.

Implement:

* CRUD APIs
* Payload classes
* Utilities
* Reports
* Logging

---

## Phase 5 – Execution & Continuous Integration

Execute tests using:

* TestNG XML
* Maven
* Jenkins (CI)

---

# Framework Initialization

## Step 1 – Create Maven Project

Create a Maven project and configure the Java version.

---

## Step 2 – Update pom.xml

Add all required dependencies.

### Dependencies

1. REST Assured
2. Json Path
3. org.json
4. TestNG
5. Java Faker
6. Apache POI
7. Apache POI OOXML
8. Extent Reports
9. JavaFaker
10. JSON Schema Validator
11. Log4j2

### Maven Plugins

* maven-compiler-plugin
* maven-surefire-plugin

---

# Project Structure

```
Project
│
├── Reports
├── Logs
├── pom.xml
├── testng.xml
│
└── src
    └── test
        ├── java
        │   ├── api.endpoints
        │   ├── api.payload
        │   ├── api.tests
        │   └── api.utilities
        │
        └── resources
            ├── routes.properties
            └── log4j2.xml
```

---

# Step 3 – Create Project Folders

Project Root

```
Reports
Logs
```

Test Source

```
api.endpoints
api.payload
api.tests
api.utilities
```

---

# Step 4 – Routes Class

Create a **Routes** class.

Purpose:

* Store Base URL
* Store API Endpoints

Example

```
Base URL
https://petstore.swagger.io/v2

Create User URL

Get User URL

Update User URL

Delete User URL
```

Keeping URLs in one place makes maintenance easy.

---

# Step 5 – User Endpoint Implementation

Create:

* UserEndPoints.java
* User POJO Class

### User POJO

Contains

* Variables
* Getters
* Setters

The POJO object stores request payload data.

### UserEndPoints

Implement CRUD operations.

* Create User
* Read User
* Update User
* Delete User

Each method internally sends the REST Assured request.

---

# Step 6 – Create Test Classes

Create

```
UserTests.java
```

Responsibilities

* Create Payload
* Call Endpoint Methods
* Validate Response
* Assert Status Codes

---

# Step 7 – Data Driven Testing

Create an Excel file containing user data.

Example

```
UserID
Username
FirstName
LastName
Email
Password
Phone
```

Create utility classes

* ExcelUtility
* DataProviders

The Data Provider reads data from Excel and supplies it to TestNG test methods.

Benefits

* No hardcoded test data
* Easy maintenance
* Multiple data execution

---

# Step 8 – Generate Extent Reports

Create

```
ExtentReportUtility
```

Execute tests using

```
testng.xml
```

Generate rich HTML reports containing

* Passed Tests
* Failed Tests
* Skipped Tests
* Execution Time
* Screenshots (if implemented)

---

# Step 9 – Logging with Log4j2

## Dependencies

Add Log4j2 dependencies in pom.xml.

Create

```
src/test/resources/log4j2.xml
```

Example location

```
src/test/resources/log4j2.xml
```

Also create

```
Logs
```

folder in the project root.

### Configure Logger

Import

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
```

Create Logger

```java
private static final Logger logger =
LogManager.getLogger(UserTests.class);
```

Use logging inside every test.

Example

```java
@Test(priority = 1)
public void testPostUser() {

    logger.info("******** Create User ********");

    Response response =
            UserEndPoints.createUser(userPayload);

    response.then().log().all();

    Assert.assertEquals(response.getStatusCode(), 200);

    logger.info("******** User Created Successfully ********");
}
```

Benefits

* Easy debugging
* Better execution tracking
* Professional execution logs

---

# Step 10 – Externalize URLs Using Properties File

Instead of hardcoding URLs inside the Routes class, move them to a properties file.

Create

```
routes.properties
```

inside

```
src/test/resources
```

Create

```
UserEndPoints2.java
```

Read URLs from

```
routes.properties
```

Create

```
UserTests2.java
```

Execute using

```
testng.xml
```

Benefits

* Environment independent
* Easy URL updates
* Better maintainability

---

# Execution Flow

```
TestNG XML
      │
      ▼
UserTests
      │
      ▼
Data Provider (Optional)
      │
      ▼
Excel Utility
      │
      ▼
Payload (POJO)
      │
      ▼
UserEndPoints
      │
      ▼
Routes / routes.properties
      │
      ▼
REST Assured API Request
      │
      ▼
Response Validation
      │
      ▼
Extent Report + Log4j2 Logs
```

---

# Technologies Used

* Java
* Maven
* REST Assured
* TestNG
* Apache POI
* Java Faker
* Extent Reports
* Log4j2
* JSON Schema Validator
* org.json

---

# Features

* Hybrid Framework
* Data-Driven Testing
* Reusable CRUD Methods
* POJO Payload Design
* Externalized API URLs
* Excel Data Provider
* Extent HTML Reports
* Log4j2 Logging
* TestNG Execution
* Maven Build Support
* Easy Maintenance
* Scalable Framework

---

# Future Enhancements

* Jenkins CI/CD Integration
* GitHub Actions
* Parallel Execution
* Environment Profiles (QA/UAT/Production)
* Retry Mechanism
* Request & Response Filters
* Allure Reports
* Docker Support
* API Chaining
* Authentication Modules (OAuth2, JWT, Basic Auth)

---

# Author

**Mohammed Sameer**

REST Assured API Automation Framework developed for learning and enterprise-level API automation using Java and REST Assured.
