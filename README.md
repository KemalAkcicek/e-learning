# 📚 E-Learning Platform Backend (Spring Boot)

This is the backend implementation of an E-Learning platform built using **Spring Boot**. It provides functionalities such as **user authentication**, **course and category management**, **lesson handling**, and **role-based access control** using **JWT**.

---

## 🔐 Security & Role-Based Access Control

The application uses **JWT (JSON Web Token)** for secure authentication and **Role-Based Access Control (RBAC)** to ensure only authorized users can access specific endpoints.

### 🔑 User Roles

- `Admin`
- `Instructor`
- `Student`
- `Boss`

Each endpoint is protected and accessible only by users with appropriate roles.

---

## ✅ Features

- User Registration & Login
- Role-Based Authorization (`Admin`, `Instructor`, `Student`, `Boss`)
- JWT Authentication & Authorization (Access & Refresh Tokens)
- Course, Lesson, Category, and User Management
- Input Validation
- Pagination & Sorting
- Swagger/OpenAPI Documentation

---

## 🧩 Entity Relationship Diagram (ERD)

The system includes the following entities and relationships:

### Entities:
- `User`
- `Course`
- `Category`
- `Lesson`
- `RefreshToken`

### Relationships:
- **User ↔ Course**: One-to-Many  
- **User ↔ RefreshToken**: One-to-Many  
- **Lesson ↔ Course**: Many-to-One  
- **Course ↔ User**: Many-to-One  
- **Course ↔ Category**: Many-to-One  
- **Category ↔ Course**: One-to-Many

---

## 🛠️ Technologies Used

- **Backend:** Java, Spring Boot  
- **Database:** MySQL  
- **Security:** JWT (Access Token & Refresh Token)  
- **Testing:** JUnit  
- **Documentation:** Swagger (OpenAPI)

---

## 🔗 API Endpoints

### 🔑 Authentication Controller
- `POST /register` → Register a new user  
- `POST /authenticate` → Login and receive token  
- `POST /refreshtoken` → Refresh JWT token

### 🧑‍🎓 Student Controller
- `PUT /api/student/update-password` → Update student password  
- `GET /api/student/{id}` → Get student details

### 📘 Course Controller (Instructor)
- `POST /api/instructor/save` → Create a new course  
- `GET /api/instructor/get/{id}` → Get course details

### 🗂️ Category Controller (Admin)
- `POST /api/admin/update/{id}` → Update a category  
- `GET /api/admin/list/pageable` → Get paginated list of categories  
- `GET /api/admin/get/{id}` → Get category details

### 🎥 Lesson Controller (Instructor)
- `GET /api/instructor/list-all` → List all lessons  
- `GET /api/instructor/get-all` → Get lesson details

### 👔 Boss Controller
- `GET /api/boss/update-role/{id}` → Update user role

### 🛡️ Admin Controller
- `GET /api/admin/all` → Get all admins  
- `DELETE /api/admin/delete/{id}` → Delete an admin

---

## 🔐 JWT Authentication Flow

- **Login:** User logs in with credentials and receives Access & Refresh Tokens  
- **Access Token:** Valid for 2 hours  
- **Refresh Token:** Valid for 4 hours  
- **Refresh:** If Access Token expires, a new one can be obtained with the Refresh Token  
- **Logout:** If both tokens expire, user must log in again

---

## 📑 Swagger API Documentation

The project integrates **Swagger UI** for interactive API documentation. Developers can test endpoints and explore all available routes directly from the browser.

---

## 📊 Pagination & Sorting

The application supports pagination and sorting for all list-returning endpoints (e.g., users, courses, categories).

### Pagination Parameters
| Parameter | Description        | Default |
|-----------|--------------------|---------|
| `page`    | Page number         | `1`     |
| `limit`   | Items per page      | `10`    |

### Sorting Parameters
| Parameter | Description         | Example      |
|-----------|---------------------|--------------|
| `sortBy`  | Field to sort by    | `createdAt`  |
| `order`   | asc / desc          | `asc`        |

---

## 📌 License

This project is licensed under the MIT License.
