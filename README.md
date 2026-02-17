# Beauty API

API REST para la gestión de citas y servicios de un salón de belleza. Sistema completo con autenticación JWT, manejo de roles, y programación de citas.

## 📋 Descripción

Beauty API es un backend desarrollado con Spring Boot que permite la gestión integral de un salón de belleza, incluyendo:
- Gestión de usuarios (clientes y empleados)
- Autenticación y autorización con JWT
- Catálogo de servicios de belleza
- Sistema de citas con disponibilidad de horarios
- Control de roles y permisos

## ✨ Características

- **Autenticación JWT**: Sistema de autenticación seguro con JSON Web Tokens
- **Control de acceso basado en roles**: Diferenciación entre ADMIN, EMPLOYEE y CLIENT
- **Gestión de usuarios**: CRUD completo de usuarios con validaciones
- **Catálogo de servicios**: Administración de servicios de belleza disponibles
- **Sistema de citas**:
  - Consulta de slots disponibles por empleado y fecha
  - Creación de citas con validación de disponibilidad
  - Prevención de conflictos de horarios
- **API RESTful**: Diseño siguiendo las mejores prácticas REST
- **Documentación OpenAPI**: Documentación interactiva con Swagger UI
- **Validación de datos**: Validaciones robustas con Bean Validation
- **Manejo de errores**: Gestión centralizada de excepciones

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.5.3**
  - Spring Data JPA
  - Spring Security
  - Spring Web
  - Spring Validation
  - Spring Actuator
- **MySQL**: Base de datos relacional
- **JWT (JSON Web Tokens)**: Para autenticación (jjwt 0.11.5)
- **Lombok**: Para reducir código boilerplate
- **SpringDoc OpenAPI**: Documentación de API (v2.5.0)
- **Maven**: Gestión de dependencias y construcción

## 📋 Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- Git

## 🚀 Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/LugoDv/beauty-api.git
cd beauty-api
```

2. **Configurar la base de datos**

Crear una base de datos MySQL:
```sql
CREATE DATABASE beauty_db;
```

3. **Configurar las propiedades de la aplicación**

Crear un archivo `application.properties` en `src/main/resources/`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/beauty_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# JWT Configuration
jwt.secret=tu_clave_secreta_aqui_debe_ser_muy_larga_y_segura
jwt.expiration=86400000

# Server Configuration
server.port=8080

# Actuator
management.endpoints.web.exposure.include=health,info
```

4. **Compilar el proyecto**
```bash
./mvnw clean install
```

## ▶️ Ejecución

**Usando Maven:**
```bash
./mvnw spring-boot:run
```

**Usando el JAR compilado:**
```bash
java -jar target/beauty-api-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: `http://localhost:8080`

## 📚 Documentación de la API

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de Swagger UI en:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principales

#### Autenticación (`/api/auth`)
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/authenticate` - Autenticar usuario y obtener token JWT

#### Usuarios (`/api/users`)
- `GET /api/users` - Listar todos los usuarios
- `GET /api/users/{id}` - Obtener usuario por ID (ADMIN)
- `PUT /api/users/{id}` - Actualizar usuario (ADMIN)
- `DELETE /api/users/{id}` - Eliminar usuario (ADMIN)
- `GET /api/users/employees` - Listar empleados disponibles

#### Servicios de Belleza (`/api/services-beauty`)
- `GET /api/services-beauty` - Listar todos los servicios
- `GET /api/services-beauty/{id}` - Obtener servicio por ID
- `POST /api/services-beauty` - Crear nuevo servicio (ADMIN)
- `PUT /api/services-beauty/{id}` - Actualizar servicio (ADMIN)
- `DELETE /api/services-beauty/{id}` - Eliminar servicio (ADMIN)

#### Citas (`/api/appointments`)
- `POST /api/appointments/calendar/slots` - Obtener slots disponibles
- `POST /api/appointments/create` - Crear nueva cita

#### Roles (`/api/roles`)
- `GET /api/roles` - Listar roles disponibles

### Ejemplo de Petición

**Registrar usuario:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "password": "password123",
    "phone": "1234567890",
    "roleId": 3
  }'
```

**Autenticar usuario:**
```bash
curl -X POST http://localhost:8080/api/auth/authenticate \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "password123"
  }'
```

**Listar servicios (con token):**
```bash
curl -X GET http://localhost:8080/api/services-beauty \
  -H "Authorization: Bearer tu_token_jwt_aqui"
```

## 📁 Estructura del Proyecto

```
beauty-api/
├── src/
│   ├── main/
│   │   ├── java/com/lugo/beauty_api/
│   │   │   ├── auth/              # Controladores y DTOs de autenticación
│   │   │   ├── config/            # Configuración de seguridad
│   │   │   ├── controller/        # Controladores REST
│   │   │   ├── exception/         # Manejo de excepciones
│   │   │   ├── mapper/            # Mappers entre entidades y DTOs
│   │   │   ├── model/             # Entidades JPA
│   │   │   │   └── dto/           # Data Transfer Objects
│   │   │   ├── repository/        # Repositorios JPA
│   │   │   ├── service/           # Lógica de negocio
│   │   │   └── BeautyApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                      # Tests unitarios e integración
├── docs/
│   └── diagrama_relacional.svg    # Diagrama de base de datos
├── pom.xml                        # Configuración Maven
└── README.md
```

## 🗄️ Modelo de Datos

El sistema utiliza las siguientes entidades principales:

- **User**: Información de usuarios (clientes y empleados)
- **Rol**: Roles del sistema (ADMIN, EMPLOYEE, CLIENT)
- **ServicesBeauty**: Catálogo de servicios disponibles
- **Appointments**: Citas agendadas
- **AppointmentsServices**: Relación entre citas y servicios
- **Availability**: Disponibilidad de empleados
- **StatusAppointments**: Estados de las citas
- **StatusEmployee**: Estados de los empleados

Ver el diagrama completo en: `docs/diagrama_relacional.svg`

## 🔐 Seguridad

- Autenticación mediante JWT (JSON Web Tokens)
- Contraseñas encriptadas con BCrypt
- Control de acceso basado en roles
- Endpoints protegidos según permisos
- Validación de tokens en cada petición

### Roles del Sistema

1. **ADMIN**: Acceso completo a todas las funcionalidades
2. **EMPLOYEE**: Gestión de sus propias citas y disponibilidad
3. **CLIENT**: Creación y consulta de sus propias citas

## 🧪 Testing

Ejecutar los tests:
```bash
./mvnw test
```

## 📝 Variables de Entorno

Para producción, se recomienda usar variables de entorno:

```bash
export DB_URL=jdbc:mysql://host:port/database
export DB_USERNAME=usuario
export DB_PASSWORD=contraseña
export JWT_SECRET=clave_secreta_segura
export JWT_EXPIRATION=86400000
```

## 🐛 Troubleshooting

**Error de conexión a MySQL:**
- Verificar que MySQL esté ejecutándose
- Comprobar credenciales en `application.properties`
- Verificar que la base de datos exista

**Error 401 Unauthorized:**
- Verificar que el token JWT sea válido
- Comprobar que el token no haya expirado
- Incluir el header: `Authorization: Bearer {token}`

**Error 403 Forbidden:**
- Verificar que el usuario tenga los permisos necesarios
- Algunos endpoints requieren rol ADMIN

## 👥 Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está desarrollado como parte de un sistema de gestión para salones de belleza.

## 👤 Autor

**LugoDv**
- GitHub: [@LugoDv](https://github.com/LugoDv)

## 📧 Contacto

Para preguntas o sugerencias, por favor abre un issue en el repositorio.

---

⭐️ Si este proyecto te fue útil, considera darle una estrella en GitHub!
