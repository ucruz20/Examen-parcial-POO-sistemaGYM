# Documentación del Servicio: `AsistenciaService`

## Descripción General

El servicio `AsistenciaService` es responsable de la gestión de los registros de asistencia de los Miembros a las Clases. Sus funcionalidades incluyen:

* Registrar la asistencia de un miembro a una clase, con validaciones para evitar duplicados y asegurar la existencia de los miembros/clases.
* Consultar registros de asistencia individuales por ID.
* Obtener una lista completa de todos los registros de asistencia.

## Ubicación del Archivo

`src/main/java/org/groupfive/gymapi/service/AsistenciaService.java`

## Dependencias Clave

* `AsistenciaRepository`: Para interactuar con la persistencia de Asistencias.
* `MiembroRepository`: Para interactuar con la persistencia de Miembros.
* `ClaseRepository`: Para interactuar con la persistencia de Clases.

## Métodos

---

### `void registrarAsistencia(Long claseId, Long miembroId)`

**Propósito:** Registra la asistencia de un miembro específico a una clase específica en el día actual.

**Parámetros:**

* `claseId`:
    * **Tipo:** `Long`
    * **Descripción:** El identificador único de la clase a la que el miembro está asistiendo.
* `miembroId`:
    * **Tipo:** `Long`
    * **Descripción:** El identificador único del miembro que está registrando su asistencia.

**Retorno:**

* **Tipo:** `void`
* **Descripción:** No retorna ningún valor. Si la operación es exitosa, se crea y guarda un nuevo registro de `Asistencia`.

**Comportamiento/Excepciones:**

* **Éxito:** Crea un nuevo registro de `Asistencia` con `presente` como `true` si todas las validaciones son exitosas.
* **Fallo:** Lanza una `RuntimeException` en los siguientes casos:
    * "Miembro no encontrado.": Si el `miembroId` no existe.
    * "Clase no encontrada.": Si el `claseId` no existe.
    * "Error: La asistencia del miembro ya fue registrada para esta clase hoy.": Si ya existe un registro de asistencia para este miembro en esta clase durante el día actual.

**Transaccionalidad:**

* Este método es transaccional (`@Transactional`).

---

### `AsistenciaResponseDTO obtenerAsistenciaPorId(Long id)`

**Propósito:** Recupera un registro de asistencia por su identificador único y lo convierte a un DTO de respuesta.

**Parámetros:**

* `id`:
    * **Tipo:** `Long`
    * **Descripción:** El identificador único del registro de asistencia a buscar.

**Retorno:**

* **Tipo:** `org.groupfive.gymapi.dto.AsistenciaResponseDTO`
* **Descripción:** Un DTO que representa el registro de asistencia encontrado, conteniendo el ID de la asistencia, ID del miembro, ID de la clase y el estado de presencia.

**Comportamiento/Excepciones:**

* **Éxito:** Retorna el `AsistenciaResponseDTO` correspondiente al `id` proporcionado.
* **Fallo:** Lanza una `RuntimeException` si el registro de asistencia con el `id` especificado no es encontrado. El mensaje de error será "Registro de asistencia no encontrado."

**Transaccionalidad:**

* Este método es de solo lectura transaccional (`@Transactional(readOnly = true)`), optimizado para operaciones de consulta.

---

### `List<AsistenciaResponseDTO> obtenerTodasLasAsistencias()`

**Propósito:** Recupera todos los registros de asistencia presentes en la base de datos.

**Parámetros:**

* **Ninguno.**

**Retorno:**

* **Tipo:** `java.util.List<org.groupfive.gymapi.dto.AsistenciaResponseDTO>`
* **Descripción:** Una lista de DTOs, donde cada DTO representa un registro de asistencia.

**Comportamiento/Excepciones:**

* **Éxito:** Retorna una lista (posiblemente vacía) de todos los `AsistenciaResponseDTO`.

**Transaccionalidad:**

* Este método es de solo lectura transaccional (`@Transactional(readOnly = true)`).

---

## Pruebas Unitarias Asociadas

* `AsistenciaServiceTest.java` (incluye pruebas para `registrarAsistencia_...`, `obtenerAsistenciaPorId_...` y `obtenerTodasLasAsistencias`).