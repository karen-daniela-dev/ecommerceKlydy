# 🛒 Klydy E-commerce Backend

Backend de una plataforma e-commerce desarrollado con **Spring Boot**, que implementa autenticación segura con **JWT**, control de acceso por roles (**ADMIN / CLIENTE**), gestión completa de productos y compras, e integración de un **asistente inteligente con IA** conectado al catálogo real.

---

## 🌐 Demo en vivo

Frontend (interfaz de usuario):
https://karen-daniela-dev.github.io/ecommerce-ia/

Backend (API):
https://ecommerceklydy.onrender.com

---

## 📄 Documentación API (Swagger)

Accede a la documentación interactiva:

https://ecommerceklydy.onrender.com/swagger-ui/index.html#/

⚠️ Nota:
El backend está desplegado en Render (free tier), por lo que puede tardar **40–60 segundos en responder** si está en reposo (cold start).

---

## 🚀 Características principales

* 🔐 Autenticación con JWT (stateless)
* 👥 Control de acceso por roles (ADMIN / CLIENTE)
* 🛍️ CRUD completo de productos
* 📦 Flujo de compras con validación de stock
* ☁️ Gestión de imágenes con Cloudinary
* 🤖 Asistente IA para recomendación de productos
* 📄 Documentación con Swagger
* 🐳 Dockerizado
* 🌍 Deploy completo (frontend + backend + BD)

---

## 🧠 Arquitectura

```text
controller → service → repository → model
```

* **Controller**: expone endpoints REST
* **Service**: lógica de negocio
* **Repository**: acceso a datos (JPA)
* **Model**: entidades del sistema

---

## 🔐 Seguridad (JWT)

* Autenticación sin sesiones (stateless)
* Uso de `JwtFilter` para interceptar cada request
* Validación de:

  * Firma del token
  * Expiración
  * Usuario

Uso del token:

```http
Authorization: Bearer TU_TOKEN
```

---

## 👥 Roles y permisos

| Endpoint          | CLIENTE | ADMIN |
| ----------------- | ------- | ----- |
| GET /productos    | ✅       | ✅     |
| POST /productos   | ❌       | ✅     |
| PUT /productos    | ❌       | ✅     |
| DELETE /productos | ❌       | ✅     |
| POST /compras     | ✅       | ✅     |

---

## 🛍️ Gestión de productos

* Validación de stock (no negativos)
* Eliminación de imágenes antiguas en Cloudinary
* Uso de enums para consistencia de datos

---

## 📦 Flujo de compra

Proceso:

1. Usuario autenticado realiza compra
2. Se obtiene cliente asociado
3. Se recorren productos solicitados
4. Se valida stock disponible
5. Se descuenta stock
6. Se crean detalles de compra
7. Se guarda la transacción completa

### 🔥 Validaciones

* ❌ No permite stock insuficiente
* ❌ No permite productos inexistentes
* ✅ Uso de transacciones (@Transactional)

---

## 🤖 Asistente IA

### 🔍 Funcionalidades

* Consulta el catálogo real
* Filtra por:

  * Presupuesto
  * Categoría
  * Uso
* Solo recomienda productos con stock
* Responde en lenguaje natural

---

### 💡 Ejemplo

Usuario:

```
quiero un laptop para estudio barato
```

IA:

```
Te recomiendo este portátil ASUS ideal para estudio, cuesta $1.200.000 y tiene buen rendimiento 😊
```

---

### 🛒 Integración con carrito

Confirmación:

```
sí, agrégalo
```

Respuesta IA:

```
[[CART_ADD:{"id":"3","qty":1}]]
```

👉 Esto permite integración directa con el frontend.

---

## 📌 Ejemplos de API

### 🛍️ Crear producto (ADMIN)

```http
POST /productos
Authorization: Bearer TOKEN
```

```json
{
  "nombre": "Laptop prueba",
  "stock": 10,
  "precio": 2000000,
  "urlImagen": "https://i.pinimg.com/736x/70/a2/8e/70a28ed06053ff2edcbb12c152341222.jpg",
  "descripcion": "Test de carga",
  "categoria": "LAPTOPS",
  "marca": "DELL",
  "uso": "TRABAJO"
}
```

---

### ⚠️ Restricciones (Enums)

Valores válidos (ejemplo):

**categoria:**

* LAPTOPS
* MOUSES
* TECLADOS

**marca:**

* DELL
* HP
* ASUS

**uso:**

* TRABAJO
* GAMER
* ESTUDIO

❌ No se aceptan valores fuera de estos

---

### 🔑 Login

```http
POST /auth/login
```

```json
{
  "email": "cami@gmail.com",
  "password": "123456"
}
```

---

## 🧪 Cómo probar el sistema

### 1. Accede al frontend

https://karen-daniela-dev.github.io/ecommerce-ia/

---

### 2. Usuario normal

* Ver productos
* Usar IA
* Agregar al carrito


---

### 3. Usuario ADMIN

Credenciales de prueba:

```
Email: dani@gmail.com
Password: 123456
```

🔒 Usuario solo para demostración (definido en base de datos)

Permite:

* Crear productos
* Editar productos
* Eliminar productos

---

### 4. Swagger

1. Ir a Swagger
2. Ejecutar `/auth/login`
3. Copiar token
4. Click en "Authorize"
5. Pegar:

```
TU_TOKEN
```

---

## 🗄️ Base de datos

* PostgreSQL
* Hosting: Supabase
* Gestión de roles desde base de datos

---

## ⚙️ Variables de entorno

```
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=

JWT_SECRET=
JWT_EXPIRATION=

CLOUDINARY_CLOUD_NAME=
CLOUDINARY_API_KEY=
CLOUDINARY_API_SECRET=

GEMINI_API_KEY=
```

---

## ▶️ Ejecución local

```bash
./mvnw spring-boot:run
```

---

## 🐳 Docker

```bash
docker build -t klydy .
docker run -p 8080:8080 klydy
```

---

## 🧪 Tecnologías

* Java
* Spring Boot
* Spring Security
* JWT (jjwt)
* PostgreSQL
* JPA / Hibernate
* Swagger
* Cloudinary
* Gemini API (IA)

---

## 💡 Valor diferencial

Este proyecto destaca por:

* Seguridad real con JWT
* Control de roles robusto
* Flujo completo de compras
* Integración de IA con backend real
* Diseño de protocolo IA → sistema (`CART_ADD`)
* Deploy completo funcional

---

## 👩‍💻 Gracias por pasarte a mirar, abrazos.

Karen Daniela Díaz  
Backend Developer | Java | APIs REST | IA aplicada  

Contacto:  
https://karen-daniela-dev.github.io/portafolio-software/

---
