# Inventory

# Inventory Management System

Este es un proyecto de backend elaborado con Java (Spring Boot) y PostgreSQL. Utiliza Docker y Docker Compose para facilitar el entorno de desarrollo y despliegue.

## 🚀 Tecnologías Principales

- **Java 21**
- **Spring Boot** (Context Path: `/api/v1`)
- **PostgreSQL 15**
- **Docker & Docker Compose**
- **Maven**

Arquitectura: Hexagonal (Ports & Adapters)
Este proyecto implementa Arquitectura Hexagonal, lo que permite desacoplar la lógica de negocio de las tecnologías externas (Base de Datos, APIs, Frameworks).

Estructura de Carpetas
Cada módulo (ej. products) se divide en tres capas principales:

domain: El núcleo. Contiene los modelos de negocio y las Interfaces (Ports). No depende de Spring ni de Hibernate.

application: Contiene los Casos de Uso (Services). Orquestan la lógica de negocio llamando a los puertos.

infrastructure: Los Adaptadores. Aquí es donde vive la implementación real:

Adapters In: Controladores REST (entrada de datos).

Adapters Out: Persistencia con Spring Data JPA y Postgres (salida de datos).
## ⚙️ Configuración del Entorno (.env)

El proyecto depende de un archivo `.env` ubicado en la raíz del proyecto (`/`) para establecer las variables de conexión a la base de datos. 

Ejemplo del archivo `.env`:

```env
# Configuración de Base de Datos
DB_NAME=db
DB_USER=postgres
DB_PASSWORD=db123
DB_PORT=5433
```

Estas variables son utilizadas:
1. En el `docker-compose.yml` para aprovisionar el contenedor de la base de datos y la aplicación.
2. En el archivo `application.properties` (que las lee al arrancar localmente).

---

## 💻 Desarrollo Local en VSCode

El proyecto está preparado para ser ejecutado cómodamente en **Visual Studio Code**, la configuración se encuentra en la carpeta `.vscode`.

### Uso de `launch.json`

Se ha configurado un perfil de depuración (`Launch Backend`) que carga automáticamente las variables del archivo `.env`.

Para arrancar el proyecto desde VSCode:
1. Levanta primero la base de datos de PostgreSQL con Docker.
   ```bash
   docker compose up inventory-db -d
   ```
2. Ve a la pestaña **Run and Debug** (`Ctrl+Shift+D`) en VSCode.
3. Selecciona **"Launch Backend"** y dale a reproducir (Play).

Esto te permitirá correr el proyecto con la base de datos en Docker, e interactuar con tu código de Java de manera local permitiendo utilizar Breakpoints, Hot Reloading, etc. 

*(Nota: Asegúrate de tener instalada la extensión "Extension Pack for Java").*

---

## 🐳 Ejecución Completa con Docker Compose

Si prefieres correr todo utilizando los contenedores de Docker (tanto la Base de Datos como la Aplicación en Java):

1. **Construir y levantar los contenedores**:

   Ejecuta el siguiente comando en la raíz del proyecto:
   ```bash
   docker compose up --build
   ```

2. **Acceder a la aplicación**:

   Una vez que los contenedores estén listos, la aplicación estará disponible en modo API bajo el puerto 8080.
   ```
   http://localhost:8080/api/v1/...
   ```

### Notas adicionales sobre Docker
- El contenedor de Base de datos almacena los datos de forma persistente utilizando un volumen de Docker (`inventory_pg_data`).
- El servicio `backend` en Docker está configurado para esperar a que la base de datos inicie (`depends_on: inventory-db`), y el archivo `.env` configurará el entorno adecuado para que se comuniquen internamente.

---

## 🛠️ Notas Adicionales y Buenas Prácticas

- **application.properties**: El framework Spring usa la instrucción `spring.config.import=optional:file:.env[.properties]`, lo cual significa que Spring Boot lee directamente tu `.env` si decides correr el comando `mvn spring-boot:run` a través de la terminal o utilizas tu IDE.
- **Base de Datos Local**: La base de datos está mapeando el puerto `5433` de tu máquina principal al puerto `5432` del contenedor para no interferir con otras posibles instancias de PostgreSQL que tengas instaladas localmente en tu sistema operativo bajo el puerto por defecto (5432).
- **Spring Data JPA**: Se está utilizando el parámetro `update` en el `ddl-auto`. Es muy útil y ágil para entornos de desarrollo en donde vayas modificando las entidades; sin embargo, considera usar migraciones (ej. Flyway o Liquibase) o el modo `validate` antes de pasar este proyecto a producción.
