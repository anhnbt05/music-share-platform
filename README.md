# 🎵 Music Share Platform

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

**A modern, full-featured music sharing and streaming platform built with Spring Boot**

[Features](#-features) • [Tech Stack](#-tech-stack) • [API Documentation](#-api-documentation) • [Getting Started](#-getting-started) • [Project Structure](#-project-structure) • [Author](#-author)

</div>

---

## 📋 Overview

Music Share Platform is a RESTful API backend service that enables users to discover, stream, and share music. The platform supports multiple user roles including regular users, artists, and administrators, providing a comprehensive ecosystem for music creators and listeners.

### Key Highlights

- 🎤 **Artist Management** – Artists can upload, manage tracks and albums with detailed analytics
- 🎧 **Music Streaming** – High-quality audio streaming with Supabase Storage integration
- 📝 **Playlist Management** – Users can create and curate personalized playlists
- 🔒 **Secure Authentication** – JWT-based authentication with OTP email verification
- 📊 **Analytics Dashboard** – Comprehensive analytics for artists
- 🛡️ **Admin Panel** – Full administrative control over users, content, and reports

---

## ✨ Features

### Authentication & Authorization
- User registration with email OTP verification
- JWT-based authentication with access/refresh token mechanism
- Password reset via email OTP
- Role-based access control (User, Artist, Admin)

### User Features
- Create and manage personal playlists
- Add/remove tracks from playlists
- Vote (like) tracks
- Follow/unfollow artists
- Apply to become an artist
- Report inappropriate content

### Artist Features
- Upload music tracks with audio file storage
- Create and manage albums
- Add tracks to albums
- View detailed analytics (plays, votes, followers)
- Manage artist profile

### Music Features
- Search music with pagination
- Filter music by genre
- Get music details and streaming URL
- Share music with generated links

### Admin Features
- User account management (search, view, delete)
- Role assignment
- Artist application review and approval
- Report management and resolution
- Content moderation

---

## 🛠 Tech Stack

### Backend Framework
| Technology | Version | Description |
|------------|---------|-------------|
| Java | 21 | Latest LTS version with modern features |
| Spring Boot | 3.2.4 | Production-ready framework |
| Spring Security | 6.x | Authentication & authorization |
| Spring Data JPA | 3.x | Database ORM |
| Spring Cloud OpenFeign | 2023.0.0 | Declarative HTTP client |

### Database & Caching
| Technology | Version | Description |
|------------|---------|-------------|
| PostgreSQL | 15 | Primary relational database |
| Redis | 7 | Caching & OTP token storage |

### External Services
| Service | Purpose |
|---------|---------|
| Supabase Storage | Audio file storage and streaming |
| SMTP Mail | Email OTP verification |

### DevOps & Documentation
| Technology | Description |
|------------|-------------|
| Docker | Containerization |
| Docker Compose | Multi-container orchestration |
| Swagger/OpenAPI | API documentation |

---

## 📚 API Documentation

The API documentation is available via Swagger UI after starting the application:

```
http://localhost:8080/swagger-ui/index.html
```

### API Endpoints Overview

| Module | Base Path | Description |
|--------|-----------|-------------|
| Auth | `/api/auth` | Authentication & registration |
| Users | `/api/users` | User operations & playlists |
| Music | `/api/music` | Music discovery & streaming |
| Artists | `/api/artists` | Artist content management |
| Admin | `/api/admin` | Administrative operations |

### Sample Endpoints

<details>
<summary><strong>Authentication</strong></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/verify-otp` | Verify email OTP |
| POST | `/api/auth/login` | User login |
| POST | `/api/auth/refresh-token` | Refresh access token |
| POST | `/api/auth/forgot-password` | Request password reset |
| POST | `/api/auth/reset-password` | Reset password |

</details>

<details>
<summary><strong>Music Operations</strong></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/music` | Search music |
| GET | `/api/music/{trackId}` | Get track details |
| GET | `/api/music/{trackId}/stream` | Stream music |
| GET | `/api/music/genre/{genre}` | Get music by genre |
| POST | `/api/music/{trackId}/share` | Generate share link |

</details>

<details>
<summary><strong>Artist Operations</strong></summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/artists/music` | Upload new track |
| GET | `/api/artists/music` | Get artist's tracks |
| PUT | `/api/artists/music/{trackId}` | Update track |
| DELETE | `/api/artists/music/{trackId}` | Delete track |
| POST | `/api/artists/albums` | Create album |
| GET | `/api/artists/analytics` | Get analytics |

</details>

---

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.8+ or Gradle 8.7+
- Docker & Docker Compose
- PostgreSQL 15 (or use Docker)
- Redis 7 (or use Docker)

### Quick Start with Docker

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/music-share-platform.git
cd music-share-platform
```

2. **Start infrastructure services**
```bash
docker-compose up -d
```

3. **Configure environment variables**
```bash
cp .env.example .env
# Edit .env with your configuration
```

4. **Run the application**
```bash
./mvnw spring-boot:run
```

### Environment Variables

| Variable | Description |
|----------|-------------|
| `DB_HOST` | PostgreSQL host |
| `DB_PORT` | PostgreSQL port |
| `DB_NAME` | Database name |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `REDIS_HOST` | Redis host |
| `REDIS_PORT` | Redis port |
| `JWT_SECRET` | JWT signing secret |
| `MAIL_HOST` | SMTP host |
| `MAIL_PORT` | SMTP port |
| `MAIL_USERNAME` | SMTP username |
| `MAIL_PASSWORD` | SMTP password |
| `SUPABASE_URL` | Supabase storage URL |
| `SUPABASE_KEY` | Supabase API key |

---

## 📁 Project Structure

```
music-share-platform/
├── src/main/java/com/example/musicshareserver/
│   ├── client/                  # External service clients
│   │   └── SupabaseStorageClient.java
│   ├── common/                  # Shared utilities
│   │   ├── JwtService.java
│   │   └── dto/
│   ├── config/                  # Configuration classes
│   │   ├── MailConfig.java
│   │   ├── SecurityConfig.java
│   │   └── SwaggerConfig.java
│   ├── controller/              # REST controllers
│   │   ├── AdminController.java
│   │   ├── ArtistController.java
│   │   ├── AuthController.java
│   │   ├── MusicController.java
│   │   └── UserController.java
│   ├── dto/                     # Data Transfer Objects
│   │   ├── request/
│   │   └── response/
│   ├── entity/                  # JPA entities
│   │   ├── User.java
│   │   ├── Music.java
│   │   ├── Album.java
│   │   ├── Playlist.java
│   │   ├── ArtistProfile.java
│   │   ├── Vote.java
│   │   ├── Follow.java
│   │   └── Report.java
│   ├── exception/               # Exception handling
│   ├── repository/              # Data repositories
│   ├── security/                # Security components
│   ├── service/                 # Business logic
│   └── util/                    # Utility classes
├── Dockerfile                   # Multi-stage Docker build
├── docker-compose.yml           # Development infrastructure
├── pom.xml                      # Maven configuration
└── README.md
```

---

## 🏗 Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        Client Applications                       │
│                  (Mobile App, Web App, etc.)                     │
└─────────────────────────────┬───────────────────────────────────┘
                              │ HTTPS
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                     Spring Boot Application                      │
├─────────────────────────────────────────────────────────────────┤
│  ┌───────────┐  ┌───────────┐  ┌───────────┐  ┌───────────┐    │
│  │   Auth    │  │   User    │  │   Music   │  │   Admin   │    │
│  │Controller │  │Controller │  │Controller │  │Controller │    │
│  └─────┬─────┘  └─────┬─────┘  └─────┬─────┘  └─────┬─────┘    │
│        │              │              │              │           │
│  ┌─────▼─────────────▼──────────────▼──────────────▼─────┐     │
│  │                    Service Layer                       │     │
│  │    (Business Logic, Validation, Transaction Mgmt)      │     │
│  └─────┬─────────────────────────────────────────────────┘     │
│        │                                                        │
│  ┌─────▼─────────────────────────────────────────────────┐     │
│  │                  Repository Layer                      │     │
│  │              (Spring Data JPA Repositories)            │     │
│  └───────────────────────────────────────────────────────┘     │
└─────────────────────┬───────────────────┬───────────────────────┘
                      │                   │
          ┌───────────▼─────┐   ┌─────────▼────────┐
          │   PostgreSQL    │   │      Redis       │
          │   (Primary DB)  │   │   (Cache/OTP)    │
          └─────────────────┘   └──────────────────┘
                      │
          ┌───────────▼─────────────────────┐
          │       Supabase Storage          │
          │    (Audio File Storage)         │
          └─────────────────────────────────┘
```

---

## 🔐 Security Features

- **JWT Authentication** – Stateless authentication with access & refresh tokens
- **Password Encryption** – BCrypt hashing for secure password storage  
- **OTP Verification** – Email-based OTP for registration and password reset
- **Role-Based Access Control** – Fine-grained permissions for User/Artist/Admin
- **Input Validation** – Jakarta Bean Validation for request DTOs
- **Global Exception Handling** – Centralized error handling with meaningful responses

---

## 📈 Future Enhancements

- [ ] Real-time notifications with WebSocket
- [ ] Music recommendation engine
- [ ] Social features (comments, likes on comments)
- [ ] Premium subscription system
- [ ] Advanced search with Elasticsearch
- [ ] Audio waveform visualization
- [ ] Collaborative playlists

---

## 👤 Author

**Tuan Anh**

- GitHub: [@anhnbt05](https://github.com/anhnbt05)
- Email: nguyenbatuananh2k5@gmail.com

---

<div align="center">

**⭐ If you find this project useful, please give it a star! ⭐**

Built with ❤️ using Spring Boot

</div>
