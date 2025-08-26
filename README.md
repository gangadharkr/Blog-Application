Blog Application (Spring Boot)

A full-featured Blog Application built with Spring Boot, featuring CRUD operations, authentication & authorization, and REST APIs.

This project can be used as a starter for any blog or content management system.

🚀 Features

User registration & authentication (Spring Security - Basic Auth for now, JWT planned)

Role-based access control (User, Admin)

Create, update, delete, and view blog posts

Add and manage categories

Add and manage comments on posts

Secure password storage using BCrypt

Error handling with custom exceptions

MySQL database integration

🛠️ Tech Stack

Backend: Spring Boot (REST APIs)

Security: Spring Security (Basic Authentication)

Database: MySQL with Spring Data JPA & Hibernate

Build Tool: Maven

Other Tools: ModelMapper, Lombok (optional), Postman (API testing)

📂 Project Structure
src/main/java/com/project/blog
│── controller/        # REST controllers
│── dto/               # Data Transfer Objects
│── exceptions/        # Custom exception handling
│── model/             # Entities (User, Post, Comment, Category, Role)
│── repository/        # Spring Data JPA repositories
│── service/           # Service interfaces
│── service/impl/      # Service implementations
│── config/            # Security configurations

🗄️ Database Setup

MySQL Database: blog_app_apis

Update application.properties with your DB credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/blog_app_apis
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🔐 Authentication

Currently supports Basic Authentication.

Default login credentials from application.properties:

spring.security.user.name=gangadhar
spring.security.user.password=root123


User passwords are stored BCrypt encoded in the database.

Role-based authorization ensures restricted access.

👉 JWT authentication is planned and will be added later for token-based stateless sessions.

📌 API Endpoints (Sample)
Users

POST /api/users/ → Register new user

GET /api/users/ → Get all users

GET /api/users/{id} → Get user by ID

PUT /api/users/{id} → Update user

DELETE /api/users/{id} → Delete user

Posts

POST /api/posts/ → Create new post

GET /api/posts/ → Get all posts

GET /api/posts/{id} → Get post by ID

PUT /api/posts/{id} → Update post

DELETE /api/posts/{id} → Delete post

Categories

POST /api/categories/ → Create category

GET /api/categories/ → Get all categories

Comments

POST /api/posts/{postId}/comments → Add comment to post

DELETE /api/comments/{id} → Delete comment

▶️ Run the Project

Clone this repo:

git clone https://github.com/your-username/blog-application.git


Open in IntelliJ IDEA / Eclipse

Configure application.properties

Run as Spring Boot Application

✅ Future Improvements

🔒 JWT Authentication & Refresh Tokens

📊 Pagination & Sorting for posts

🖼️ File/image upload for posts

🌐 Swagger/OpenAPI Documentation

📧 Author

Gangadhar Pandit
(Spring Boot | Java | REST APIs Enthusiast)
