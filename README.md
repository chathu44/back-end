# Spring Boot Template (Student Edition)

REST API starter for Angular + Spring Boot projects. Includes JWT login, privilege groups (RBAC), sample CRUD modules, login logging, and audit fields.

Use this as a **starting point** — students rename entities and screens for their own domain (hotel, property, pets, HR, etc.).

## Requirements

- Java 17+
- Maven 3.8+ (or use `mvnw`)
- MySQL 8+

## First-time setup

```sql
CREATE DATABASE IF NOT EXISTS ems;
USE ems;
SOURCE src/main/resources/schema.sql;
```

Or paste `schema.sql` into MySQL Workbench and run it.

Default login after seed:

| Field | Value |
|-------|-------|
| login | `admin` |
| password | `password` |

Set database credentials in `application.properties` (or `DB_USERNAME` / `DB_PASSWORD` env vars).

## Run

```bash
./mvnw spring-boot:run
```

API base: `http://localhost:8010`

## What students should copy

| Layer | Example in template | Student changes to… |
|-------|---------------------|---------------------|
| Entity | `StudentEntity` | `BookingEntity`, `PetEntity`, etc. |
| DTO | `StudentDto` | matching DTO |
| Repository | `StudentRepository` | matching repository |
| Service | `StudentServiceImpl` | business logic |
| Controller | `StudentController` | REST endpoints |
| Angular page | `student/` component | their module UI |

Keep **auth**, **privilege**, **login log**, and **audit** patterns as-is unless the project needs changes.

## Main endpoints

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| POST | `/login` | No | Login → userEntity + JWT |
| POST | `/logout` | Yes | Records logout time |
| POST | `/register` | No | Register → userEntity + JWT |
| GET | `/get-auth-ids/{id}` | Yes | Privilege IDs for your own userEntity |
| GET | `/login-logs` | Yes | Login history |
| GET/POST/PUT/DELETE | `/students`, `/courses`, `/teachers`, … | Yes | Sample CRUD |
| GET/PUT | `/system-privileges` | Yes | Privilege dual-list |
| GET/POST/PUT/DELETE | `/privilege-groups` | Yes | Privilege groups |

## Auth header

```
Authorization: Bearer <token>
```

JWT includes claim `id` (userEntity primary key).

## Project structure

```
controllers → services (*ServiceI) → services/impl → repositories → entities
dtos + mappers (MapStruct)
config (Security, JWT, CORS, JPA auditing)
```

CORS allows `http://localhost:4200`.

## Database files

Only **`schema.sql`** is needed. Hibernate `ddl-auto=update` can add missing columns on startup, but run `schema.sql` once for seed data (admin userEntity, privileges, sample statuses).
