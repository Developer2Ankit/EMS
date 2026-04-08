# Employee Management System (Spring Boot + JWT)
### base url:http://localhost:8080/swagger-ui/index.html#/
## Overview

This is a Spring Boot application secured with JWT authentication and role-based authorization.
### checking
---

## Tech Stack

* Spring Boot
* Spring Security
* JWT (jjwt)
* Spring Data JPA
* MySQL
* Swagger (OpenAPI)

---

## Authentication Flow

### 1. Register

```http
POST /api/auth/register
```

Creates a new user with:

* Encrypted password (BCrypt)
* Default role: `ROLE_USER`

---

### 2. Login

```http
POST /api/auth/login
```

Returns:

```json
{
  "accessToken": "JWT_TOKEN"
}
```

---

## Authorization

### Roles

* ROLE_USER
* ROLE_ADMIN

---

## API Access Rules

| Endpoint        | Access        |
| --------------- | ------------- |
| `/api/auth/**`  | Public        |
| `/api/user/**`  | Authenticated |
| `/api/admin/**` | ADMIN only    |

---

## Secured API Usage

All protected APIs should require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## ⚠️ Issue Observed

Some APIs were accessible **without Bearer token**, which is incorrect.

### Possible Reason

JWT filter is not validating requests properly or skipping authentication.

---

## Fix

Ensure JWT filter runs for all protected APIs and validates token:

```java
if (header == null || !header.startsWith("Bearer ")) {
    filterChain.doFilter(request, response);
    return;
}
```

And make sure:

```java
.anyRequest().authenticated()
```

---

## Role-Based Access Example

### USER Access

```http
GET /api/user/profile
```

### ADMIN Access

```http
GET /api/admin/dashboard
```

---

## Role Issue

If user has:

```
ROLE_USER
```

Then:

```
/api/admin/** → 403 Forbidden
```

### Fix:

```sql
UPDATE employees 
SET role = 'ROLE_ADMIN' 
WHERE email = 'yadav@gmail.com';
```

---

## Key Points

* JWT is required for secured APIs
* 401 → Invalid/Missing token
* 403 → Role not allowed
* Application is stateless (no session)

---

## Conclusion

This project demonstrates:

* JWT-based authentication
* Role-based authorization
* Secure REST APIs using Spring Security
