# 🚗 RENT CAR

Sistema de alquiler de vehículos basado en arquitectura de microservicios, desarrollado para ofrecer una solución moderna, escalable y observable en la gestión de rentas, clientes, inventario de vehículos y pagos.

## 🧱 Arquitectura General

El sistema está construido bajo el enfoque de microservicios desacoplados, orquestados mediante un **API Gateway** y **Eureka Server** para descubrimiento de servicios. Cada servicio tiene una responsabilidad única y se comunica de manera asíncrona utilizando **RabbitMQ**.

### 🧩 Microservicios incluidos:

- **API Gateway LR**: Enrutador de peticiones hacia los microservicios, con funciones de control de acceso.
- **Eureka Server**: Registro y descubrimiento de microservicios.
- **BookingService**: Gestiona reservas de vehículos.
- **CarInventoryService**: Administra la disponibilidad y el estado del inventario de vehículos.
- **CustomerService**: Maneja la información y autenticación de clientes.
- **PaymentService**: Procesa pagos y confirma transacciones.


## ⚙️ Tecnologías Utilizadas

- **Java + Spring Boot** para el backend de todos los servicios
- **Spring Cloud** (Eureka, Config, Gateway)
- **RabbitMQ** para mensajería asíncrona entre servicios
- **Docker Compose** para orquestación local de contenedores
- **Zipkin** para trazabilidad distribuida
- **Prometheus + Grafana** para monitoreo y visualización de métricas
- **PostgreSQL / MongoDB** como motores de almacenamiento por servicio


## 🔍 Observabilidad y Trazabilidad

- Cada microservicio incluye integración con **Zipkin** para seguimiento distribuido de peticiones.
- Las métricas de rendimiento y salud son recolectadas con **Prometheus** y visualizadas en **Grafana**.
- Se asegura así visibilidad completa de la operación y estado del sistema.


## 🐳 Orquestación con Docker Compose

Cada componente es desplegado como contenedor independiente. Se provee un archivo `docker-compose.yml` que permite levantar todo el sistema con un el comando: **docker-compose up --build**


# ✍️ Autor
Juan Pablo Ramírez Gutiérrez
