# 🏠 Nexo Vivienda - Backend API

API RESTful desarrollada para el sistema de gestión de vivienda universitaria, parte de la asignatura Fundamentos de Ingeniería de Software (FIS). Este backend proporciona una plataforma segura para conectar estudiantes que buscan alojamiento con arrendadores locales.

## 🚀 Tecnologías Principales

El proyecto está construido con una arquitectura moderna y escalable utilizando el siguiente stack tecnológico:

* **Core:** Java 25 y Spring Boot 3.x
* **Seguridad:** Spring Security con JSON Web Tokens (JWT)
* **Base de Datos Relacional:** PostgreSQL (Usuarios, Viviendas, Reservas)
* **Base de Datos NoSQL:** MongoDB (Logs, futuro módulo de mensajería)
* **ORM & Acceso a Datos:** Spring Data JPA e Hibernate
* **Infraestructura:** Docker y Docker Compose

## 📁 Estructura del Proyecto

La aplicación sigue un patrón de diseño en capas para asegurar la separación de responsabilidades y facilitar la mantenibilidad:

```text
src/main/java/co/edu/distrital/fis/vivienda_backend/
├── config/       # Filtros de seguridad, CORS y configuración de beans
├── controllers/  # Endpoints de la API REST (Rutas web)
├── dto/          # Data Transfer Objects (Peticiones y Respuestas)
├── entities/     # Modelos de dominio mapeados a PostgreSQL (JPA)
├── repositories/ # Interfaces para consultas a bases de datos
└── services/     # Lógica de negocio de la aplicación
```

## 📖 Documentación y Guías

Para mantener este README limpio, toda la documentación detallada de configuración y arquitectura se encuentra en la **Wiki** del repositorio. Por favor revisa los siguientes enlaces antes de empezar a programar:

* [🛠️ Guía de Configuración Local y Docker](Enlace-a-tu-wiki-aqui)
* [🧠 Arquitectura y Flujo de Datos](Enlace-a-tu-wiki-aqui)

## ⚡ Inicio Rápido

1.  Asegúrate de tener Docker Desktop ejecutándose.
2.  Clona el repositorio e ingresa a la carpeta.
3.  Levanta la infraestructura de bases de datos:
    ```bash
    docker-compose up -d
    ```
4.  Ejecuta la aplicación desde tu IDE o con el comando de Maven. El servidor iniciará por defecto en `http://localhost:8081`.
5.  Usa la colección de Postman configurada con tu `jwt_token` para probar los endpoints protegidos.

## 👥 Equipo de Desarrollo

Proyecto desarrollado colaborativamente por:
* Santiago Vargas Mayorga
* Jose Cucanchon 

Universidad Distrital Francisco José de Caldas.
