# ClaseController: `/api/clases`  

Gestiona las operaciones CRUD (crear, leer, actualizar, eliminar) de clases en el sistema de gimnasio.

## Endpoints

### 1. GET `/api/clases`
Obtiene todas las clases registradas.

- **Parámetros:** Ninguno.
- **Respuesta:**  
  - **200 OK** → Devuelve una lista de objetos `ClaseResponse`
    ```json
    [
      {
        "id": 1,
        "nombre": "Yoga",
        "instructor": "Ana Pérez",
        "horario": "Lunes 8:00 AM"
      }
    ]
    ```

---

### 2. POST `/api/clases/crear`
Crea una nueva clase.  

- **Parámetros:**  
  - **Body (JSON):** Objeto `ClaseRequest` con los datos de la clase (Se aplican validaciones con `@Valid`).  
    ```json
    {
      "nombre": "Pilates",
      "instructor": "Carlos López",
      "horario": "Martes 6:00 PM"
    }
    ```  
- **Respuesta:**  
  - **200 OK** → Devuelve el objeto `ClaseResponse` creado.  
  - **400 Bad Request** → Datos inválidos.  

---

### 3. PUT `/api/clases/{idClase}`
Actualiza la información de una clase existente.  

- **Parámetros:**  
  - **PathVariable:** `idClase` (Long).  
  - **Body (JSON):** Objeto `ClaseRequest` con los datos actualizados.  
- **Respuesta:**  
  - **200 OK** → Devuelve el objeto `ClaseResponse` actualizado.  
  - **404 Not Found** → Clase no encontrada.  

---

### 4. DELETE `/api/clases/{idClase}`
Elimina una clase por su ID.  
- **Parámetros:**  
  - **PathVariable:** `idClase` (Long).  
- **Respuesta:**  
  - **200 OK** → Mensaje de confirmación con el ID eliminado.  
    ```json
    {
      "message": "Clase eliminada con exito",
      "idEliminado": 5
    }
    ```  
  - **404 Not Found** → Clase no encontrada.  

---

## Notas Técnicas
- Todas las solicitudes y respuestas utilizan formato **JSON**.  
- El controlador depende de `ClaseService` para la lógica de negocio.  
