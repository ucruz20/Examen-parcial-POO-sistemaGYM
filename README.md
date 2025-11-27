// -------------------- MIEMBROS --------------------

➤ Crear Miembro

POST /miembros

Descripción

Crea un nuevo miembro en el sistema.
Recibe los datos del miembro desde el cuerpo de la solicitud y devuelve el miembro creado.

Request Body
{
  "nombre": "Juan Pérez",
  "correo": "juan.perez@mail.com",
  "edad": 25,
  "telefono": "7890-1234"
}



 Respuesta (200 OK)
{
  "id": 10,
  "nombre": "Juan Pérez",
  "correo": "juan.perez@mail.com",
  "edad": 25,
  "telefono": "7890-1234"
}

 Ejemplo (curl)
curl -X POST http://localhost:8080/miembros \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Juan Pérez",
        "correo": "juan.perez@mail.com",
        "edad": 25,
        "telefono": "7890-1234"
      }'

Errores

400 Bad Request — Falta un campo obligatorio o el JSON es inválido

500 Internal Server Error — Error inesperado al intentar crear el miembro

</details>


➤ Editar Miembro

PUT /miembros/{id}

Descripción

Actualiza los datos de un miembro existente en el sistema.
Se debe enviar el identificador del miembro en la URL y la información a modificar en el cuerpo de la solicitud.

Request Body

{
  "nombre": "Juan Pérez",
  "correo": "juan.perez@mail.com",
  "edad": 26,
  "telefono": "7890-5678"
}


Respuesta (200 OK)
{
  "id": 10,
  "nombre": "Juan Pérez",
  "correo": "juan.perez@mail.com",
  "edad": 26,
  "telefono": "7890-5678"
}

Ejemplo (curl)
curl -X PUT http://localhost:8080/miembros/10 \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Juan Pérez",
        "correo": "juan.perez@mail.com",
        "edad": 26,
        "telefono": "7890-5678"
      }'

Errores

400 Bad Request – Datos inválidos o falta un campo obligatorio
404 Not Found – El miembro con el ID especificado no existe
500 Internal Server Error

</details>

➤ Listar Miembros

GET /miembros

Descripción

Obtiene la lista completa de miembros registrados en el sistema.
No requiere parámetros ni cuerpo en la solicitud.

Respuesta (200 OK)

[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "correo": "juan.perez@mail.com",
    "edad": 25,
    "telefono": "7890-1234"
  },
  {
    "id": 2,
    "nombre": "María Gómez",
    "correo": "maria.gomez@mail.com",
    "edad": 30,
    "telefono": "7654-9876"
  }
]

Ejemplo (curl)
curl -X GET http://localhost:8080/miembros

Errores

500 Internal Server Error

</details>

➤ Eliminar Miembro

DELETE /miembros/{id}

Descripción

Elimina un miembro existente del sistema usando su identificador.
No requiere cuerpo en la solicitud, únicamente el parámetro de la ruta.

Respuesta (200 OK)
{
  "message": "Miembro eliminado correctamente"
}

Ejemplo (curl)
curl -X DELETE http://localhost:8080/miembros/10

Errores

404 Not Found – El miembro con el ID especificado no existe
500 Internal Server Error

</details>

// -------------------- ENTRENADORES --------------------

➤ Crear Entrenador

POST /entrenadores

Descripción

Crea un nuevo entrenador en el sistema.
Recibe los datos del entrenador mediante el cuerpo de la solicitud y devuelve el entrenador creado.

Request Body
{
  "nombre": "Carlos Ruiz",
  "especialidad": "Yoga",
  "telefono": "7890-4567",
  "correo": "carlos.ruiz@mail.com"
}


Respuesta (200 OK)
{
  "id": 5,
  "nombre": "Carlos Ruiz",
  "especialidad": "Yoga",
  "telefono": "7890-4567",
  "correo": "carlos.ruiz@mail.com"
}

Ejemplo (curl)
curl -X POST http://localhost:8080/entrenadores \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Carlos Ruiz",
        "especialidad": "Yoga",
        "telefono": "7890-4567",
        "correo": "carlos.ruiz@mail.com"
      }'

Errores

400 Bad Request – Falta un campo obligatorio o el JSON es inválido
500 Internal Server Error

</details>

@PutMapping("/entrenadores/{id}")
    public ResponseEntity<Entrenador> editarEntrenador(@PathVariable Long id, @RequestBody EntrenadorRequestDTO dto) {
        return ResponseEntity.ok(adminService.actualizarEntrenador(id, dto));
    }

➤ Editar Entrenador

PUT /entrenadores/{id}

Descripción

Actualiza los datos de un entrenador existente en el sistema.
El identificador del entrenador se envía en la URL y los datos a modificar se envían en el cuerpo de la solicitud.


Request Body

{
  "nombre": "Carlos Ruiz",
  "especialidad": "Yoga Avanzado",
  "telefono": "7890-9999",
  "correo": "carlos.ruiz@mail.com"
}

Respuesta (200 OK)
{
  "id": 5,
  "nombre": "Carlos Ruiz",
  "especialidad": "Yoga Avanzado",
  "telefono": "7890-9999",
  "correo": "carlos.ruiz@mail.com"
}

Ejemplo (curl)
curl -X PUT http://localhost:8080/entrenadores/5 \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Carlos Ruiz",
        "especialidad": "Yoga Avanzado",
        "telefono": "7890-9999",
        "correo": "carlos.ruiz@mail.com"
      }'

Errores

400 Bad Request – Los datos enviados son inválidos o incompletos
404 Not Found – El entrenador con el ID especificado no existe
500 Internal Server Error

</details>

➤ Listar Entrenadores

GET /entrenadores

Descripción

Obtiene la lista completa de entrenadores registrados en el sistema.
No requiere parámetros ni cuerpo en la solicitud.

Respuesta (200 OK)

[
  {
    "id": 1,
    "nombre": "Carlos Ruiz",
    "especialidad": "Yoga",
    "telefono": "7890-4567",
    "correo": "carlos.ruiz@mail.com"
  },
  {
    "id": 2,
    "nombre": "María López",
    "especialidad": "Crossfit",
    "telefono": "7854-9876",
    "correo": "maria.lopez@mail.com"
  }
]

Ejemplo (curl)
curl -X GET http://localhost:8080/entrenadores

Errores

500 Internal Server Error

</details>

➤ Eliminar Entrenador

DELETE /entrenadores/{id}

Descripción

Elimina un entrenador existente del sistema utilizando su identificador único.
No requiere cuerpo en la solicitud.


Respuesta (200 OK)
{
  "message": "Entrenador eliminado correctamente"
}

Ejemplo (curl)
curl -X DELETE http://localhost:8080/entrenadores/5

Errores

404 Not Found – El entrenador con el ID especificado no existe
500 Internal Server Error

</details>

// -------------------- CLASES --------------------

➤ Crear Clase

POST /api/admin/clases

Ver detalles
Descripción

Crea una nueva clase en el sistema y la asigna a un entrenador existente.

Request Body
{
  "nombre": "Yoga Avanzado",
  "descripcion": "Clase enfocada en flexibilidad y respiración",
  "cupoMaximo": 20,
  "entrenadorId": 3,
  "horario": "Lunes 7:00 AM"
}

Respuesta (200 OK)
{
  "id": 12,
  "nombre": "Yoga Avanzado",
  "descripcion": "Clase enfocada en flexibilidad y respiración",
  "cupoMaximo": 20,
  "horario": "Lunes 7:00 AM",
  "entrenador": {
    "id": 3,
    "nombre": "Carlos Ruiz",
    "especialidad": "Yoga"
  }
}

Ejemplo (curl)
curl -X POST http://localhost:8080/api/admin/clases \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Yoga Avanzado",
        "descripcion": "Clase enfocada en flexibilidad y respiración",
        "cupoMaximo": 20,
        "entrenadorId": 3,
        "horario": "Lunes 7:00 AM"
      }'

Errores

400 Bad Request – El entrenador no existe o falta un campo obligatorio

500 Internal Server Error

</details>

➤ Editar Clase

PUT /clases/{id}

Descripción

Actualiza los datos de una clase existente en el sistema.
El identificador de la clase se envía en la URL y la información a modificar se recibe en el cuerpo de la solicitud.

Request Body

{
  "nombre": "Yoga Intermedio",
  "descripcion": "Clase centrada en postura y respiración",
  "cupoMaximo": 18,
  "entrenadorId": 3,
  "horario": "Martes 6:30 PM"
}

Respuesta (200 OK)

{
  "id": 12,
  "nombre": "Yoga Intermedio",
  "descripcion": "Clase centrada en postura y respiración",
  "cupoMaximo": 18,
  "horario": "Martes 6:30 PM",
  "entrenador": {
    "id": 3,
    "nombre": "Carlos Ruiz",
    "especialidad": "Yoga"
  }
}

Ejemplo (curl)
curl -X PUT http://localhost:8080/clases/12 \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Yoga Intermedio",
        "descripcion": "Clase centrada en postura y respiración",
        "cupoMaximo": 18,
        "entrenadorId": 3,
        "horario": "Martes 6:30 PM"
      }'

Errores

400 Bad Request – Datos inválidos o falta un campo obligatorio
404 Not Found – La clase con el ID especificado no existe
500 Internal Server Error

</details>

➤ Listar Clases

GET /clases

Descripción

Obtiene la lista completa de clases registradas en el sistema.
No requiere parámetros ni cuerpo en la solicitud.

Respuesta (200 OK)

[
  {
    "id": 12,
    "nombre": "Yoga Avanzado",
    "descripcion": "Clase enfocada en flexibilidad y respiración",
    "cupoMaximo": 20,
    "horario": "Lunes 7:00 AM",
    "entrenador": {
      "id": 3,
      "nombre": "Carlos Ruiz",
      "especialidad": "Yoga"
    }
  },
  {
    "id": 15,
    "nombre": "Crossfit Intermedio",
    "descripcion": "Entrenamiento de alta intensidad",
    "cupoMaximo": 12,
    "horario": "Martes 6:00 PM",
    "entrenador": {
      "id": 7,
      "nombre": "María López",
      "especialidad": "Crossfit"
    }
  }
]

Ejemplo (curl)
curl -X GET http://localhost:8080/clases

Errores

500 Internal Server Error

</details>

➤ Eliminar Clase

DELETE /clases/{id}

Descripción

Elimina una clase existente del sistema utilizando su identificador único.
No requiere cuerpo en la solicitud.

Respuesta (200 OK)
{
  "message": "Clase eliminada correctamente"
}

Ejemplo (curl)
curl -X DELETE http://localhost:8080/clases/12

Errores

404 Not Found – La clase con el ID especificado no existe
500 Internal Server Error

</details>

// -------------------- INSCRIPCIONES --------------------

➤