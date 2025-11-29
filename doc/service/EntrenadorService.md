## 1\, `EntrenadorService.md`

## Descripción General

El servicio `EntrenadorService` es el componente principal para la gestión de Entrenadores y la creación de Clases en el sistema. Se encarga de la lógica de negocio para:

* Registrar nuevas clases, asignándolas a un entrenador específico.
* Asegurar la existencia del entrenador antes de asociarle una clase.

## Ubicación del Archivo

`src/main/java/org/groupfive/gymapi/service/EntrenadorService.java`

## Dependencias Clave

* `EntrenadorRepository`: Para interactuar con la persistencia de Entrenadores.
* `ClaseRepository`: Para interactuar con la persistencia de Clases.
* `MiembroRepository`, `AsistenciaRepository`, `InscripcionRepository`: (Se asume que estas dependencias son parte de la inyección, pero no directamente usadas en `crearClase` según las pruebas).

## Métodos

---

### `Clase crearClase(ClaseRequest req)`

**Propósito:** Crea y persiste una nueva clase en el sistema, asignándola al entrenador especificado.

**Parámetros:**

* `req`: Objeto `ClaseRequest`
    * **Tipo:** `org.groupfive.gymapi.dto.ClaseRequest`
    * **Descripción:** Contiene los datos necesarios para la nueva clase, incluyendo el `entrenadorId`, `nombre`, `horario` y `cupoMaximo`.

**Retorno:**

* **Tipo:** `org.groupfive.gymapi.model.Clase`
* **Descripción:** La entidad `Clase` que ha sido guardada en la base de datos.

**Comportamiento/Excepciones:**

* **Éxito:** Si el `entrenadorId` proporcionado existe, la clase se guarda y se retorna la entidad.
* **Fallo:** Lanza una `RuntimeException` si el `entrenadorId` no corresponde a un Entrenador existente en la base de datos. El mensaje de error será "Error: Entrenador no encontrado para asignar la clase."

**Transaccionalidad:**

* Este método es transaccional (`@Transactional`), lo que significa que todas las operaciones dentro de él se ejecutan como una única unidad atómica. Si ocurre una excepción, todos los cambios se revertirán.

---

## Pruebas Unitarias Asociadas

* `EntrenadorServiceTest.java` (métodos `crearClase_EntrenadorExiste_GuardaYRetornaClase` y `crearClase_EntrenadorNoExiste_LanzaRuntimeException`).