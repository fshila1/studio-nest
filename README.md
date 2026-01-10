# Studio Booking Application

A production-ready Studio Booking Web Application built with Angular and Spring Boot.

## Tech Stack

- **Frontend**: Angular 17, Angular Material, SCSS
- **Backend**: Java 17, Spring Boot 3.2, Spring Data JPA
- **Database**: MySQL

## Prerequisites

- Java 17+
- Node.js 18+ & npm
- MySQL 8.0+

## Setup Instructions

### 1. Database Setup

Create a MySQL database named `studio_booking`.

```sql
CREATE DATABASE studio_booking;
```

Update `backend/src/main/resources/application.properties` with your MySQL credentials if they differ from `root` / (empty password).

### 2. Backend Setup

Navigate to the `backend` directory and run:

```bash
cd backend
./mvnw spring-boot:run
```
(If `./mvnw` is not available, use `mvn spring-boot:run`)

The backend will start on `http://localhost:8080`.
It will automatically seed the database with sample studios.

### 3. Frontend Setup

Navigate to the `frontend` directory and run:

```bash
cd frontend
npm install
npm start
```

The frontend will start on `http://localhost:4200`.

## Features

- **Studio Management**: Browse studios, filter by area, view details.
- **Booking Management**: Book time slots, view booked slots, prevent double booking.
- **Conflict Resolution**: Backend enforces unique bookings per slot.

## API Endpoints

- `GET /api/studios`: List all studios
- `GET /api/studios/search?area=`: Search studios
- `POST /api/bookings`: Create a booking
- `GET /api/bookings`: List bookings
