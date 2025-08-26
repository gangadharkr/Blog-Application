# 📝 Blog Application (Spring Boot)

A blogging platform built using **Spring Boot 3**, **Spring Security 6**, **JPA/Hibernate**, and **MySQL**.  
Supports users, posts, categories, and comments with **role-based authentication** (Basic Auth for now, JWT optional).

---

## 🚀 Features
- User registration & authentication
- Role-based access control (`ADMIN` / `USER`)
- Create, update, delete, and view blog posts
- Categories for organizing posts
- Commenting system
- Spring Security with `UserDetailsService`
- Passwords stored securely with **BCrypt**
- Configurable database & file upload support
- Extensible to **JWT authentication** (future-ready)

---

## 📂 Project Structure
```text
src/main/java/com/project/blog
├── controller/       # REST controllers (Users, Posts, Auth, etc.)
├── payloads/         # DTOs (UserDto, etc.)
├── exceptions/       # Custom exception classes & handler
├── model/            # Entities: User, Role, Post, Comment, Category
├── repository/       # JPA repositories
├── service/          # Service interfaces
├── service/impl/     # Service implementations
├── config/           # Security & app config
└── security/         # CustomUserDetailService, (JWT classes if added later)
⚙️ Configuration
src/main/resources/application.properties
properties
Copy
Edit
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

# Logging
logging.level.org.springframework.security=DEBUG

# (Optional, for JWT later)
# app.jwt.secret=replace_with_a_very_long_random_secret_at_least_32_chars
# app.jwt.expiration-ms=3600000
# app.jwt.issuer=blog-api
🗄️ Database Setup
1️⃣ Create the database
sql
Copy
Edit
CREATE DATABASE blog_app_apis;
2️⃣ Auto-create tables
Hibernate will generate tables for:

users

roles

user_role

posts

comments

categories

3️⃣ Insert roles & a test user
⚠️ Passwords must be stored as BCrypt hashes.

Generate a hash:

java
Copy
Edit
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }
}
Example SQL:

sql
Copy
Edit
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

-- Insert test user
INSERT INTO users (id, name, email, password, about)
VALUES (1, 'Test User', 'what@gmail.com', '<BCryptHashHere>', 'Test user for API');

-- Link user to roles
INSERT INTO user_role (user_id, role_id) VALUES (1, 1); -- ROLE_USER
INSERT INTO user_role (user_id, role_id) VALUES (1, 2); -- ROLE_ADMIN
▶️ Run the Project
bash
Copy
Edit
git clone https://github.com/your-username/blog-application.git
cd blog-application

# Build
mvn clean install

# Run
mvn spring-boot:run
Runs on 👉 http://localhost:9090

📌 API Endpoints
🔐 Authentication
Basic Auth: Provide email + password in request headers

👤 Users
http
Copy
Edit
POST   /api/users        → Register new user
GET    /api/users        → List all users
GET    /api/users/{id}   → Get user by ID
PUT    /api/users/{id}   → Update user
DELETE /api/users/{id}   → Delete user
📝 Posts
http
Copy
Edit
POST   /api/posts        → Create post
GET    /api/posts        → List posts
GET    /api/posts/{id}   → Get post by ID
PUT    /api/posts/{id}   → Update post
DELETE /api/posts/{id}   → Delete post
📂 Categories
http
Copy
Edit
POST   /api/categories   → Create category
GET    /api/categories   → List categories
💬 Comments
http
Copy
Edit
POST   /api/posts/{postId}/comments  → Add comment
DELETE /api/comments/{id}            → Delete comment
🛠️ Usage Examples
Using curl
bash
Copy
Edit
curl -u what@gmail.com:password \
  -X GET "http://localhost:9090/api/posts"
Using Postman
Go to Authorization → Basic Auth

Username: what@gmail.com

Password: password

🔒 Authentication Details
Custom UserDetailsService loads users by email

Passwords stored with BCryptPasswordEncoder

Ensure DB users have BCrypt hashed passwords

🔮 Future Improvements
✅ Add JWT authentication + refresh tokens

✅ Add Swagger / OpenAPI docs

✅ Pagination & sorting for posts

✅ File/image upload for posts & avatars

✅ Unit & integration tests

✅ Dockerize with docker-compose

👤 Author
Gangadhar Pandit

📧 Email: your.email@example.com
💼 LinkedIn: linkedin.com/in/your-profile
🐙 GitHub: github.com/your-username

📜 License
Released under the MIT License — free to use, modify, and share.
