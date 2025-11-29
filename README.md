# Documentación API – Administración del Gimnasio

## MIEMBROS

### Crear Miembro

**POST** `/api/admin/miembros`

**Descripción**

Crea un nuevo miembro en el sistema.
Recibe los datos del miembro desde el cuerpo de la solicitud y devuelve el miembro creado.

#### Request Body

```json
{
  "nombre": "Juan Pérez",
  "apellido": "Ramírez",
  "correo": "juan.perez@mail.com",
  "telefono": "7890-1234",
  "direccion": "San Salvador, Col. Escalón",
  "tipoMembresia": "BASIC"
}
```

#### Respuesta (200 OK)

```json
{
  "id": 10,
  "nombre": "Juan Pérez",
  "apellido": "Ramírez",
  "correo": "juan.perez@mail.com",
  "telefono": "7890-1234",
  "direccion": "San Salvador, Col. Escalón",
  "tipoMembresia": "BASIC"
}
```

#### Ejemplo (curl)

```bash
curl -X POST http://localhost:8080/api/admin/miembros \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Juan Pérez",
        "apellido": "Ramírez",
        "correo": "juan.perez@mail.com",
        "telefono": "7890-1234",
        "direccion": "San Salvador, Col. Escalón",
        "tipoMembresia": "BASIC"
      }'
```

#### Errores

* `400 Bad Request` — Falta un campo obligatorio o el JSON es inválido.
* `500 Internal Server Error` — Error inesperado al intentar crear el miembro.

---

### Editar Miembro

**PUT** `/api/admin/miembros/{id}`

**Descripción**

Actualiza los datos de un miembro existente en el sistema.
Se debe enviar el identificador del miembro en la URL y la información a modificar en el cuerpo de la solicitud.

#### Request Body

```json
{
  "nombre": "Juan Pérez",
  "apellido": "Ramírez",
  "correo": "juan.perez.editado@mail.com",
  "telefono": "7890-5678",
  "direccion": "Santa Tecla, La Libertad",
  "tipoMembresia": "VIP"
}
```

#### Respuesta (200 OK)

```json
{
  "id": 10,
  "nombre": "Juan Pérez",
  "apellido": "Ramírez",
  "correo": "juan.perez.editado@mail.com",
  "telefono": "7890-5678",
  "direccion": "Santa Tecla, La Libertad",
  "tipoMembresia": "VIP"
}
```

#### Ejemplo (curl)

```bash
curl -X PUT http://localhost:8080/api/admin/miembros/10 \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Juan Pérez",
        "apellido": "Ramírez",
        "correo": "juan.perez.editado@mail.com",
        "telefono": "7890-5678",
        "direccion": "Santa Tecla, La Libertad",
        "tipoMembresia": "VIP"
      }'
```

#### Errores

* `400 Bad Request` – Datos inválidos o falta un campo obligatorio.
* `404 Not Found` – El miembro con el ID especificado no existe.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Listar Miembros

**GET** `/api/admin/miembros`

**Descripción**

Obtiene la lista completa de miembros registrados en el sistema.
No requiere parámetros ni cuerpo en la solicitud.

#### Respuesta (200 OK)

```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "apellido": "Ramírez",
    "correo": "juan.perez@mail.com",
    "telefono": "7890-1234",
    "direccion": "San Salvador, Col. Escalón",
    "tipoMembresia": "BASIC"
  },
  {
    "id": 2,
    "nombre": "María Gómez",
    "apellido": "López",
    "correo": "maria.gomez@mail.com",
    "telefono": "7654-9876",
    "direccion": "Santa Tecla, La Libertad",
    "tipoMembresia": "VIP"
  }
]
```

#### Ejemplo (curl)

```bash
curl -X GET http://localhost:8080/api/admin/miembros
```

#### Errores

* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Eliminar Miembro

**DELETE** `/api/admin/miembros/{id}`

**Descripción**

Elimina un miembro existente del sistema usando su identificador.
No requiere cuerpo en la solicitud, únicamente el parámetro de la ruta.

#### Respuesta (200 OK)

```json
{
  "message": "Miembro eliminado correctamente"
}
```

#### Ejemplo (curl)

```bash
curl -X DELETE http://localhost:8080/api/admin/miembros/10
```

#### Errores

* `404 Not Found` – El miembro con el ID especificado no existe.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

## ENTRENADORES

### Crear Entrenador

**POST** `/api/admin/entrenadores`

**Descripción**

Crea un nuevo entrenador en el sistema.
Recibe los datos del entrenador mediante el cuerpo de la solicitud y devuelve el entrenador creado.

#### Request Body

```json
{
  "nombre": "Carlos Ruiz",
  "especialidad": "Entrenamiento funcional",
  "horario": "Lunes a Viernes 5:30 - 11:30 AM",
  "salario": 850.0
}
```

#### Respuesta (200 OK)

```json
{
  "id": 5,
  "nombre": "Carlos Ruiz",
  "especialidad": "Entrenamiento funcional",
  "horario": "Lunes a Viernes 5:30 - 11:30 AM",
  "salario": 850.0
}
```

#### Ejemplo (curl)

```bash
curl -X POST http://localhost:8080/api/admin/entrenadores \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Carlos Ruiz",
        "especialidad": "Entrenamiento funcional",
        "horario": "Lunes a Viernes 5:30 - 11:30 AM",
        "salario": 850.0
      }'
```

#### Errores

* `400 Bad Request` – Falta un campo obligatorio o el JSON es inválido.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Editar Entrenador

**PUT** `/api/admin/entrenadores/{id}`

**Descripción**

Actualiza los datos de un entrenador existente en el sistema.
El identificador del entrenador se envía en la URL y los datos a modificar se envían en el cuerpo de la solicitud.

#### Request Body

```json
{
  "nombre": "Carlos Ruiz",
  "especialidad": "Entrenamiento funcional",
  "salario": 900.0
}
```

#### Respuesta (200 OK)

```json
{
  "id": 5,
  "nombre": "Carlos Ruiz",
  "especialidad": "Entrenamiento funcional",
  "salario": 900.0
}
```

#### Ejemplo (curl)

```bash
curl -X PUT http://localhost:8080/api/admin/entrenadores/5 \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Carlos Ruiz",
        "especialidad": "Entrenamiento funcional",
        "salario": 900.0
      }'
```

#### Errores

* `400 Bad Request` – Los datos enviados son inválidos o incompletos.
* `404 Not Found` – El entrenador con el ID especificado no existe.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Listar Entrenadores

**GET** `/api/admin/entrenadores`

**Descripción**

Obtiene la lista completa de entrenadores registrados en el sistema.
No requiere parámetros ni cuerpo en la solicitud.

#### Respuesta (200 OK)

```json
[
  {
    "id": 1,
    "nombre": "Carlos Ruiz",
    "especialidad": "Entrenamiento funcional",
    "salario": 850.0
  },
  {
    "id": 2,
    "nombre": "Laura M. Morales",
    "especialidad": "Entrenamiento funcional",
    "salario": 900.0
  }
]
```

#### Ejemplo (curl)

```bash
curl -X GET http://localhost:8080/api/admin/entrenadores
```

#### Errores

* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Eliminar Entrenador

**DELETE** `/api/admin/entrenadores/{id}`

**Descripción**

Elimina un entrenador existente del sistema utilizando su identificador único.
No requiere cuerpo en la solicitud.

#### Respuesta (200 OK)

```json
{
  "message": "Entrenador eliminado correctamente"
}
```

#### Ejemplo (curl)

```bash
curl -X DELETE http://localhost:8080/api/admin/entrenadores/5
```

#### Errores

* `404 Not Found` – El entrenador con el ID especificado no existe.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

## CLASES

### Crear Clase

**POST** `/api/admin/clases`

**Descripción**

Crea una nueva clase en el sistema y la asigna a un entrenador existente.

#### Request Body

```json
{
  "nombre": "Aerobicos Avanzado",
  "cupoMaximo": 3,
  "horario": "Lunes y Viernes 6:00 - 7:00 PM",
  "entrenadorId": 4
}
```

#### Respuesta (200 OK)

```json
{
  "id": 12,
  "nombre": "Aerobicos Avanzado",
  "cupoMaximo": 3,
  "horario": "Lunes y Viernes 6:00 - 7:00 PM",
  "entrenador": {
    "id": 4,
    "nombre": "Carlos Ruiz",
    "especialidad": "Entrenamiento funcional"
  }
}
```

#### Ejemplo (curl)

```bash
curl -X POST http://localhost:8080/api/admin/clases \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Aerobicos Avanzado",
        "cupoMaximo": 3,
        "horario": "Lunes y Viernes 6:00 - 7:00 PM",
        "entrenadorId": 4
      }'
```

#### Errores

* `400 Bad Request` – El entrenador no existe o falta un campo obligatorio.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Editar Clase

**PUT** `/api/admin/clases/{id}`

**Descripción**

Actualiza los datos de una clase existente en el sistema.
El identificador de la clase se envía en la URL y la información a modificar se recibe en el cuerpo de la solicitud.

#### Request Body

```json
{
  "nombre": "Calistenia Básico",
  "cupoMaximo": 2,
  "horario": "Jueves y Viernes 6:00 - 7:00 PM",
  "entrenadorId": 2
}
```

#### Respuesta (200 OK)

```json
{
  "id": 12,
  "nombre": "Calistenia Básico",
  "cupoMaximo": 2,
  "horario": "Jueves y Viernes 6:00 - 7:00 PM",
  "entrenador": {
    "id": 2,
    "nombre": "Laura M. Morales",
    "especialidad": "Entrenamiento funcional"
  }
}
```

#### Ejemplo (curl)

```bash
curl -X PUT http://localhost:8080/api/admin/clases/12 \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Calistenia Básico",
        "cupoMaximo": 2,
        "horario": "Jueves y Viernes 6:00 - 7:00 PM",
        "entrenadorId": 2
      }'
```

#### Errores

* `400 Bad Request` – Datos inválidos o falta un campo obligatorio.
* `404 Not Found` – La clase con el ID especificado no existe.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Listar Clases

**GET** `/api/admin/clases`

**Descripción**

Obtiene la lista completa de clases registradas en el sistema.
No requiere parámetros ni cuerpo en la solicitud.

#### Respuesta (200 OK)

```json
[
  {
    "id": 12,
    "nombre": "Aerobicos Avanzado",
    "cupoMaximo": 3,
    "horario": "Lunes y Viernes 6:00 - 7:00 PM",
    "entrenador": {
      "id": 4,
      "nombre": "Carlos Ruiz",
      "especialidad": "Entrenamiento funcional"
    }
  },
  {
    "id": 15,
    "nombre": "Crossfit Intermedio",
    "cupoMaximo": 12,
    "horario": "Martes 6:00 PM",
    "entrenador": {
      "id": 7,
      "nombre": "María López",
      "especialidad": "Crossfit"
    }
  }
]
```

#### Ejemplo (curl)

```bash
curl -X GET http://localhost:8080/api/admin/clases
```

#### Errores

* `500 Internal Server Error` – Error inesperado en el servidor.

---

### Eliminar Clase

**DELETE** `/api/admin/clases/{id}`

**Descripción**

Elimina una clase existente del sistema utilizando su identificador único.
No requiere cuerpo en la solicitud.

#### Respuesta (200 OK)

```json
{
  "message": "Clase eliminada correctamente"
}
```

#### Ejemplo (curl)

```bash
curl -X DELETE http://localhost:8080/api/admin/clases/12
```

#### Errores

* `404 Not Found` – La clase con el ID especificado no existe.
* `500 Internal Server Error` – Error inesperado en el servidor.

---

## INSCRIPCIONES

### Inscribir Miembro en una Clase

**POST** `/api/admin/inscripciones?miembroId={miembroId}&claseId={claseId}`

**Descripción**

Inscribe un miembro en una clase.
Los IDs se envían como parámetros de query (`miembroId` y `claseId`).
El sistema valida que el miembro exista, que la clase exista, que no esté ya inscrito y que haya cupos disponibles.

#### Parámetros (query)

* `miembroId` (Long) – ID del miembro a inscribir
* `claseId` (Long) – ID de la clase destino

#### Ejemplo (curl)

```bash
curl -X POST "http://localhost:8080/api/admin/inscripciones?miembroId=7&claseId=4"
```

#### Respuesta (200 OK)

```json
{
  "id": 2,
  "fechaInscripcion": "2025-03-15",
  "estado": "ACTIVA",
  "miembro": {
    "id": 7,
    "nombre": "Juan Pérez"
  },
  "clase": {
    "id": 4,
    "nombre": "Aerobicos Avanzado"
  }
}
```

#### Errores

* `400 Bad Request` –

    * El miembro ya está inscrito en la clase
    * La clase no tiene cupos disponibles
    * IDs inválidos
* `404 Not Found` – Miembro o clase no existen
* `500 Internal Server Error` – Error inesperado en el servidor

---

### Listar Inscripciones

**GET** `/api/admin/inscripciones`

**Descripción**

Obtiene la lista completa de inscripciones registradas en el sistema.

#### Respuesta (200 OK)

```json
[
  {
    "id": 2,
    "fechaInscripcion": "2025-03-15",
    "estado": "ACTIVA",
    "miembro": {
      "id": 7,
      "nombre": "Juan Pérez"
    },
    "clase": {
      "id": 4,
      "nombre": "Aerobicos Avanzado"
    }
  }
]
```

#### Ejemplo (curl)

```bash
curl -X GET "http://localhost:8080/api/admin/inscripciones"
```

#### Errores

* `500 Internal Server Error` – Error inesperado en el servidor

---

### Eliminar Inscripción

**DELETE** `/api/admin/inscripciones/{id}`

**Descripción**

Elimina una inscripción existente utilizando su identificador único.

#### Respuesta (200 OK)

```json
{
  "message": "Inscripción eliminada correctamente"
}
```

#### Ejemplo (curl)

```bash
curl -X DELETE "http://localhost:8080/api/admin/inscripciones/2"
```

#### Errores

* `404 Not Found` – La inscripción con el ID especificado no existe
* `500 Internal Server Error` – Error inesperado en el servidor

---

## ASISTENCIAS

### Registrar Asistencia

**POST** `/api/admin/asistencias?claseId={claseId}&miembroId={miembroId}`

**Descripción**

Registra la asistencia de un miembro a una clase.
El sistema valida que el miembro esté inscrito en esa clase.

#### Parámetros (query)

* `claseId` (Long) – ID de la clase
* `miembroId` (Long) – ID del miembro

#### Ejemplo (curl)

```bash
curl -X POST "http://localhost:8080/api/admin/asistencias?claseId=4&miembroId=7"
```

#### Respuesta (200 OK)

```json
{
  "message": "Asistencia registrada correctamente"
}
```

#### Errores

* `400 Bad Request` – El miembro no está inscrito en la clase o parámetros inválidos
* `404 Not Found` – Miembro o clase no existen
* `500 Internal Server Error` – Error inesperado en el servidor

---

### Listar Asistencias

**GET** `/api/admin/asistencias`

**Descripción**

Obtiene la lista completa de asistencias registradas en el sistema.

#### Respuesta (200 OK)

```json
[
  {
    "id": 1,
    "fecha": "2025-03-15",
    "miembroNombre": "Juan Pérez",
    "claseNombre": "Aerobicos Avanzado"
  },
  {
    "id": 2,
    "fecha": "2025-03-16",
    "miembroNombre": "María Gómez",
    "claseNombre": "Calistenia Básico"
  }
]
```

#### Ejemplo (curl)

```bash
curl -X GET "http://localhost:8080/api/admin/asistencias"
```

#### Errores

* `500 Internal Server Error` – Error inesperado en el servidor

