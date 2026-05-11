# Charter Rewards Program API

## Project Overview

This project is a Spring Boot REST API implementation for calculating customer reward points based on retail purchase transactions.

The application calculates:

* Monthly reward points
* Total reward points
* Rewards across a three-month period

The solution follows proper Java coding standards, layered architecture, exception handling, unit testing, integration testing, and RESTful API practices.

---

# Reward Calculation Rules

A retailer offers reward points to customers:

* 2 points for every dollar spent over $100
* 1 point for every dollar spent between $50 and $100

## Example

For a purchase of $120:

* Amount between $50 and $100 = 50 points
* Amount above $100 = 20 × 2 = 40 points
* Total = 90 points

---

# Technologies Used

| Technology      | Version      |
| --------------- | ------------ |
| Java            | 17           |
| Spring Boot     | 3.2.5        |
| Maven           | Latest       |
| Spring Data JPA | Included     |
| H2 Database     | In-Memory    |
| JUnit 5         | Testing      |
| Mockito         | Unit Testing |
| Postman         | API Testing  |

---

# Project Structure

```text
charter-rewards-program-api/
│
├── src/main/java/com/charter/springboot/apis/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── exception/
│   ├── repository/
│   ├── service/
│   ├── util/
│   └── RewardsApplication.java
│
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
│
├── src/test/java/
│   ├── service/
│   └── controller/
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# Features Implemented

## Functional Features

* Reward point calculation
* Monthly reward aggregation
* Total reward aggregation
* REST API endpoint
* Dynamic month calculation
* Multiple customer support
* Multiple transaction support

---

# API Details

## Base URL

```text
http://localhost:8080
```

---

# Get Rewards By Customer

## Endpoint

```http
GET /api/rewards/{customerId}
```

---

# Sample Request

```http
GET http://localhost:8080/api/rewards/1
```

---

# Sample Success Response

```json
{
  "customerId": 1,
  "customerName": "John Doe",
  "monthlyRewards": [
    {
      "month": "JANUARY",
      "points": 90
    },
    {
      "month": "FEBRUARY",
      "points": 25
    },
    {
      "month": "MARCH",
      "points": 250
    }
  ],
  "totalPoints": 365
}
```

---

# Negative Scenario Response

## Request

```http
GET http://localhost:8080/api/rewards/999
```

## Response

```json
{
  "timestamp": "2026-05-11T17:00:00",
  "message": "Customer not found with ID: 999",
  "status": 404
}
```

---

# Sample Dataset

Sample transaction records inserted through:

```text
data.sql
```

Customers:

* John Doe
* Jane Smith

Transactions span across:

* January
* February
* March

---

# Reward Formula

## Between $50 and $100

```text
Points = Amount - 50
```

## Above $100

```text
Points = ((Amount - 100) × 2) + 50
```

---

# How to Run the Application

## Build Project

```bash
mvn clean install
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

# Test API Using Browser

```text
http://localhost:8080/api/rewards/1
```

---

# Test API Using Postman

Method:

```text
GET
```

URL:

```text
http://localhost:8080/api/rewards/1
```


