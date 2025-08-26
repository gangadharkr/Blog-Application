# 📝 Blog Application (Spring Boot)

A full-featured **Blog Application** built with **Spring Boot** (Java 17).  
Features include Users, Roles, Posts, Comments, Categories, secure password storage (BCrypt), and REST APIs.  
Authentication currently uses **Spring Security (Basic Auth)**; JWT support can be added (instructions included).

---

## 🔖 Table of Contents
- [Features](#features)  
- [Tech Stack](#tech-stack)  
- [Prerequisites](#prerequisites)  
- [Project Structure](#project-structure)  
- [Configuration (`application.properties`)](#configuration-applicationproperties)  
- [Database Setup & Sample Data](#database-setup--sample-data)  
- [Run the Project](#run-the-project)  
- [API Endpoints & Examples](#api-endpoints--examples)  
- [Authentication (Basic Auth)](#authentication-basic-auth)  
- [How to Add JWT (Short Guide)](#how-to-add-jwt-short-guide)  
- [Common Troubleshooting](#common-troubleshooting)  
- [Future Improvements](#future-improvements)  
- [Author & Contact](#author--contact)  
- [License](#license)

---

## Features
- ✅ User registration, update, delete  
- ✅ Role-based access control (`ROLE_USER`, `ROLE_ADMIN`)  
- ✅ CRUD for Posts, Comments, Categories  
- ✅ Passwords stored securely with **BCrypt**  
- ✅ ModelMapper for DTO → Entity mapping  
- ✅ Centralized exception handling (custom exceptions)  
- ✅ MySQL + Spring Data JPA (Hibernate)  
- ✅ Basic Authentication (Spring Security) — extensible to JWT

---

## Tech Stack
- Java 17  
- Spring Boot 3.x  
- Spring Security 6.x  
- Spring Data JPA, Hibernate  
- MySQL (or MariaDB)  
- Maven  
- ModelMapper, Lombok (optional)  
- Postman / curl (for API testing)

---

## Prerequisites
- Java 17 (JDK)
- Maven 3.6+
- MySQL server
- IDE (IntelliJ / Eclipse recommended)

---

## Project Structure
src/main/java/com/project/blog
├─ controller/ # REST controllers (Users, Posts, Auth, etc.)
├─ payloads/ # DTOs (UserDto, JwtRequest, etc.)
├─ exceptions/ # Custom exception classes & handler
├─ model/ # Entities: User, Role, Post, Comment, Category
├─ repository/ # JPA repositories
├─ service/ # Service interfaces
├─ service/impl/ # Service implementations
├─ config/ # Security & app config
└─ security/ # CustomUserDetailService, JWT classes (optional)

yaml
Copy
Edit

---

## Configuration (`application.properties`)
Replace values with your credentials and secure secrets.

```properties
# Application
spring.application.name=blog-application
server.port=9090

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/blog_app_apis
spring.datasource.username=root
spring.datasource.password=root123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Multipart (images)
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
project.image=images/

# Logging (helps debugging authentication)
logging.level.org.springframework.security=DEBUG

# JWT (if you add JWT later — example properties)
# app.jwt.secret=replace_with_a_very_long_random_secret_at_least_32_chars
# app.jwt.expiration-ms=3600000
# app.jwt.issuer=blog-api
Note: Do not keep spring.security.user.name / spring.security.user.password if you rely on DB-stored users. Those properties create an in-memory user and can be confusing during development.

Database Setup & Sample Data
1) Create the database
sql
Copy
Edit
CREATE DATABASE blog_app_apis;
2) Let JPA create tables
With spring.jpa.hibernate.ddl-auto=update, Hibernate will auto-create tables from your entities (Users, Roles, user_roles, posts, comments, categories).

3) Insert roles and a test user (recommended approach)
Important: passwords must be BCrypt hashes. Use the Java snippet below to generate the hash or use your application to register a user (recommended).

Java snippet to generate BCrypt hash (run once or in a simple main class):

java
Copy
Edit
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password")); // replace "password"
    }
}
Example SQL (replace <BCryptHashHere> with actual output from snippet):

sql
Copy
Edit
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

-- Insert test user (email = what@gmail.com). Replace hashed password.
INSERT INTO users (id, name, email, password, about)
VALUES (1, 'Test User', 'what@gmail.com', '<BCryptHashHere>', 'Test user for API');

-- Link user to role(s)
INSERT INTO user_role (user_id, role_id) VALUES (1, 1); -- ROLE_USER
-- optionally:
INSERT INTO user_role (user_id, role_id) VALUES (1, 2); -- ROLE_ADMIN
4) Quick migration (optional)
If you have many users with plain-text passwords, you can write a one-time Spring CommandLineRunner to encode all plain passwords (we provided a snippet earlier).

Run the Project
Clone the repo:

bash
Copy
Edit
git clone https://github.com/your-username/blog-application.git
cd blog-application
Configure application.properties (DB credentials, port, etc.)

Build and run:

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
or run from IDE (run Application main class).

App runs at: http://localhost:9090 (if using server.port=9090)

API Endpoints & Examples
Base URL: http://localhost:9090

Auth (Basic)
No dedicated login endpoint required for Basic Auth — supply credentials via HTTP Basic (Postman or curl).

Users
POST /api/users — Register (call UserService.registerUser(...) which will encode password before saving)

GET /api/users — Get all users

GET /api/users/{id} — Get user by id

PUT /api/users/{id} — Update user

DELETE /api/users/{id} — Delete user

Posts
POST /api/posts — Create post

GET /api/posts — List posts

GET /api/posts/{id} — Get post

PUT /api/posts/{id} — Update post

DELETE /api/posts/{id} — Delete post

Categories
POST /api/categories — Create category

GET /api/categories — List categories

Comments
POST /api/posts/{postId}/comments — Add comment

DELETE /api/comments/{id} — Delete comment

Usage Examples
Call an endpoint using curl with Basic Auth:
bash
Copy
Edit
curl -u what@gmail.com:password \
  -X GET "http://localhost:9090/api/posts"
(If using the username what@gmail.com with password password — ensure password is the user's original plain value and DB holds the BCrypt hash.)

Using Postman (Basic Auth)
Open Postman → New Request

Authorization tab → Type: Basic Auth

Username: what@gmail.com
Password: password

Send requests to protected endpoints.

Authentication (Basic Auth)
The project uses Spring Security with UserDetailsService that loads users by email.

Passwords are validated with BCryptPasswordEncoder.

Make sure:

Users exist in DB with BCrypt-hashed password.

CustomUserDetailService returns UserDetails with proper authorities.

If you see 401 Unauthorized, check:

Credentials passed correctly

Password is BCrypt-hashed in DB

CustomUserDetailService.loadUserByUsername finds user by email

How to Add JWT (Short Guide)
If you decide to replace or add token-based authentication later, the steps are:

Add JJWT dependencies to pom.xml:

xml
Copy
Edit
io.jsonwebtoken:jjwt-api:0.11.5
io.jsonwebtoken:jjwt-impl:0.11.5 (runtime)
io.jsonwebtoken:jjwt-jackson:0.11.5 (runtime)
Add JWT properties to application.properties:

properties
Copy
Edit
app.jwt.secret=very_long_random_secret_32_chars_min
app.jwt.expiration-ms=3600000
app.jwt.issuer=blog-api
Add:

JwtService (generate/validate token)

JwtAuthenticationFilter (OncePerRequestFilter to read Authorization: Bearer <token> header)

AuthController login endpoint that returns token

Update SecurityConfig to add filter and use stateless sessions (keep Basic Auth if desired)

I can provide ready-to-paste JWT files if you want to integrate it later — I have provided those earlier in the conversation.

Common Troubleshooting
401 Unauthorized when credentials are correct
Verify the DB password is BCrypt-encoded.

Check log logging.level.org.springframework.security=DEBUG to see which user loads and why authentication failed.

Ensure CustomUserDetailService is returning the correct username (email) and getPassword() returns the hashed password.

Role / join table issues
Make sure Role.id is numeric (use int or long) when using @GeneratedValue. String with IDENTITY will prevent table creation.

Use @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id")) — avoid reserved words like user as column names.

WebSecurityConfigurerAdapter errors
For Spring Security 6+, do NOT extend WebSecurityConfigurerAdapter. Provide a @Bean SecurityFilterChain and AuthenticationManager instead.

Future Improvements (Roadmap)
Add JWT authentication + refresh tokens (stateless)

Add Swagger / OpenAPI docs (springdoc-openapi)

Add pagination & sorting for posts

Add file/image upload for posts & user avatars

Add unit + integration tests (JUnit + MockMvc)

Containerize with Docker + docker-compose for DB

Author / Contact
Gangadhar Pandit
Email: (add your email here)
LinkedIn: (add your LinkedIn profile link)
GitHub: (add GitHub link)

License
This project is released under the MIT License — feel free to copy, modify, and use as a learning/demo project.



