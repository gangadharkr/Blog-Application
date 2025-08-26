# 📝 Blog Application (Spring Boot)

A **full-featured Blog Application** built with **Spring Boot**, featuring **CRUD operations**, **authentication & authorization**, and **REST APIs**.  
This project can serve as a starter template for any blog or content management system.

---

## 🚀 Features
- 👤 User registration & authentication (**Spring Security - Basic Auth** for now, JWT planned)  
- 🔑 Role-based access control (**User, Admin**)  
- 📝 Create, update, delete, and view blog posts  
- 📂 Add and manage categories  
- 💬 Add and manage comments on posts  
- 🔐 Secure password storage using **BCrypt**  
- ⚠️ Centralized exception handling  
- 💾 MySQL database integration  

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot (REST APIs)  
- **Security:** Spring Security (Basic Authentication)  
- **Database:** MySQL with Spring Data JPA & Hibernate  
- **Build Tool:** Maven  
- **Other Tools:** ModelMapper, Lombok (optional), Postman (API testing)  

---

## 📂 Project Structure
src/main/java/com/project/blog
│── controller/ # REST controllers
│── dto/ # Data Transfer Objects
│── exceptions/ # Custom exception handling
│── model/ # Entities (User, Post, Comment, Category, Role)
│── repository/ # Spring Data JPA repositories
│── service/ # Service interfaces
│── service/impl/ # Service implementations
│── config/ # Security configurations


---

## 🗄️ Database Setup
1. Create a MySQL database:
   ```sql
   CREATE DATABASE blog_app_apis;
Update Application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/blog_app_apis
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.url=jdbc:mysql://localhost:3306/blog_app_apis
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

📧 Author

Gangadhar Pandit
(Spring Boot | Java | )
