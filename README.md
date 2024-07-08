# Druzhba Hub Backend

## Used Technologies

- **Java 17**
- **Spring Framework**
  - **Spring Boot**
  - **Spring Security**
  - **Spring Data JPA**
  - **Spring AOP**
- **PostgreSQL**
- **Redis**: Caching.
- **MongoDB**: Storage for user audits and exceptions logs.
- **Google Cloud Storage**: Storage for images.
- **JavaMailSender**: Email sending, including registration confirmation.
- **Liquibase**: Database migrations management.
- **Lombok**

## Description

Druzhba Hub is a platform for socializing and organizing events where users can create profiles, find friends or companions for joint participation in various events or simply for walks. Key features include:

- **Profile Management**: CRUD.
- **Event Management**: CRUD.
- **Post Management**: Users can create posts related to events or general topics.
- **Interactions**: Users can like posts and follow other users for updates and notifications.
- **Authentication and Authorization**: Secure registration and login using JWT tokens.
- **Image Management**: Uploading and storage of profile and event images in Google Cloud Storage.

## Requirements

Before getting started, ensure you have the following software installed:

- Java Development Kit (JDK) version 17 or higher
- Apache Maven
- PostgreSQL
- Redis
- MongoDB
- Google Cloud Storage (credentials required for connection)

##Thanks for attention

   ```bash
   git clone <repository_url>
   cd druzhba-hub-backend
