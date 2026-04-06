# 💰 Financial Dashboard Backend API

A robust backend system built using **Java + Spring Boot + MongoDB**, designed to manage financial records, provide insights, and enforce secure role-based access using **JWT Authentication**.

---

# 🚀 Tech Stack

* **Backend:** Java 21, Spring Boot 3.x
* **Database:** MongoDB Atlas
* **Security:** Spring Security + JWT
* **Build Tool:** Maven
* **API Testing:** Postman
* **Documentation:** Swagger (OpenAPI)

---

# 🧠 Features

## 🔐 Authentication & Security

* JWT-based authentication
* Stateless session management
* Role-based access control:

    * **ADMIN** → Full access
    * **ANALYST** → Records + Dashboard
    * **VIEWER** → Dashboard only

---

## 👤 User Management

* Create users (ADMIN only)
* Password encryption using BCrypt
* Role assignment (ADMIN / ANALYST / VIEWER)

---

## 💸 Financial Records

* Create, update, delete records
* Filter records dynamically:

    * by userId
    * by type (INCOME / EXPENSE)
    * by category
    * by date range
* Pagination support

---

## 📊 Dashboard APIs

* Total Income
* Total Expense
* Net Balance
* Category-wise aggregation

---

## ⚠️ Exception Handling

* Global exception handler
* Structured error responses

---

## 📄 API Response Structure

All APIs follow a consistent format:

```json
{
  "status": "SUCCESS",
  "message": "Operation successful",
  "data": {}
}
```

---

# 🔐 Authentication Flow

## 1. Login

```http
POST /auth/login
```

### Request:

```json
{
  "email": "admin@mail.com",
  "password": "1234"
}
```

### Response:

```json
{
  "status": "SUCCESS",
  "message": "Login successful",
  "data": "JWT_TOKEN"
}
```

---

## 2. Use Token

Add header in all protected APIs:

```text
Authorization: Bearer <JWT_TOKEN>
```

---

# 👥 Roles & Access Control

| Endpoint   | ADMIN | ANALYST | VIEWER |
| ---------- | ----- | ------- | ------ |
| /users     | ✅     | ❌       | ❌      |
| /records   | ✅     | ✅       | ❌      |
| /dashboard | ✅     | ✅       | ✅      |

---

# 📡 API Endpoints

## 🔐 Auth

* `POST /auth/login`

---

## 👤 Users

* `POST /users` → Create user (ADMIN only)
* `GET /users` → Get all users (ADMIN only)

---

## 💸 Records

* `POST /records` → Create record
* `GET /records` → Get records (with filters & pagination)

### Query Params:

```text
userId, type, category, startDate, endDate, page, size
```

---

## 📊 Dashboard

* `GET /dashboard/summary`
* `GET /dashboard/category`

---

# 🧪 Sample Record

```json
{
  "userId": "123",
  "type": "EXPENSE",
  "category": "FOOD",
  "amount": 500,
  "transactionDate": "2026-04-01T10:00:00"
}
```

---

# ⚙️ Setup & Run

## 1. Clone Repo

```bash
git clone <your-repo-url>
cd financial-dashboard
```

---

## 2. Configure MongoDB

Update `application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: YOUR_MONGODB_ATLAS_URI
      database: financial_dashboard
```

---

## 3. Run Application

```bash
mvn spring-boot:run
```

---

# 🔧 Design Decisions

* **MongoDB used** → avoids joins, flexible schema
* **userId stored in records** → faster queries, no joins
* **transactionDate vs createdAt**

    * `transactionDate` → business logic
    * `createdAt` → system tracking
* **JWT chosen** → stateless, scalable
* **Response wrapper** → consistent API structure

---

# ⚡ Future Improvements

* Refresh tokens
* Advanced analytics (monthly trends)
* Export reports (PDF/CSV)
* Rate limiting
* Docker deployment

---

