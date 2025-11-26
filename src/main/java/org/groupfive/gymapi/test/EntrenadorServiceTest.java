package org.groupfive.gymapi.service;

import org.groupfive.gymapi.dto.CreateClaseRequest; // Asumiendo que existe este DTO
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Entrenador;
import org.groupfive.gymapi.model.Miembro;
import org.groupfive.gymapi.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntrenadorServiceTest {

    // Repositorios Mock
    @Mock private EntrenadorRepository entrenadorRepository;
    @Mock private ClaseRepository claseRepository;
    @Mock private MiembroRepository miembroRepository;
    @Mock private AsistenciaRepository asistenciaRepository;
    @Mock private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private EntrenadorService entrenadorService;

    private Entrenador entrenador;
    private Miembro miembro;
    private Clase clase;
    private CreateClaseRequest claseRequest;

    @BeforeEach
    void setUp() {
        entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Carlos");

        miembro = new Miembro();
        miembro.setId(2L);

        clase = new Clase();
        clase.setIdSesion(3L);
        clase.setNombre("Yoga");
        clase.setHorario(LocalTime.of(10, 0));
        clase.setCupoMax(20);
        clase.setEntrenador(entrenador);

        claseRequest = new CreateClaseRequest();
        claseRequest.setEntrenadorId(1L);
        claseRequest.setNombre("Pilates");
        claseRequest.setHorario(LocalTime.of(15, 30));
        claseRequest.setCupoMax(15);
    }

    // --- Pruebas para crearClase ---

    @Test
    void crearClase_EntrenadorExiste_GuardaYRetornaClase() {
        // Arrange
        when(entrenadorRepository.findById(1L)).thenReturn(Optional.of(entrenador));
        when(claseRepository.save(any(Clase.class))).thenReturn(clase);

        // Act
        Clase result = entrenadorService.crearClase(claseRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Yoga", result.getNombre()); // Asumiendo que clase es el objeto mock de retorno
        verify(entrenadorRepository, times(1)).findById(1L);
        verify(claseRepository, times(1)).save(any(Clase.class));
    }

    @Test
    void crearClase_EntrenadorNoExiste_LanzaRuntimeException() {
        // Arrange
        when(entrenadorRepository.findById(99L)).thenReturn(Optional.empty());
        claseRequest.setEntrenadorId(99L);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            entrenadorService.crearClase(claseRequest);
        });

        assertEquals("Error: Entrenador no encontrado para asignar la clase.", exception.getMessage());
        verify(entrenadorRepository, times(1)).findById(99L);
        verify(claseRepository, never()).save(any(Clase.class));
    }

    // --- Pruebas para registrarAsistencia ---

    @Test
    void registrarAsistencia_TodoCorrecto_GuardaAsistencia() {
        // Arrange
        Long claseId = 3L;
        Long miembroId = 2L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.of(clase));
        when(inscripcionRepository.existsByMiembro_IdAndClase_IdSesion(miembroId, claseId)).thenReturn(true);
        when(asistenciaRepository.existsByMiembro_IdAndSesion_IdSesionAndFechaHoraBetween(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);

        // Act
        entrenadorService.registrarAsistencia(claseId, miembroId);

        // Assert
        ArgumentCaptor<Asistencia> asistenciaCaptor = ArgumentCaptor.forClass(Asistencia.class);
        verify(asistenciaRepository, times(1)).save(asistenciaCaptor.capture());

        Asistencia savedAsistencia = asistenciaCaptor.getValue();
        assertEquals(miembro, savedAsistencia.getMiembro());
        assertEquals(clase, savedAsistencia.getSesion());
        assertTrue(savedAsistencia.isPresente());
    }

    @Test
    void registrarAsistencia_MiembroNoExiste_LanzaRuntimeException() {
        // Arrange
        Long claseId = 3L;
        Long miembroId = 99L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            entrenadorService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Miembro no encontrado.", exception.getMessage());
        verify(claseRepository, never()).findById(anyLong());
        verify(asistenciaRepository, never()).save(any(Asistencia.class));
    }

    @Test
    void registrarAsistencia_ClaseNoExiste_LanzaRuntimeException() {
        // Arrange
        Long claseId = 99L;
        Long miembroId = 2L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            entrenadorService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Clase no encontrada.", exception.getMessage());
        verify(inscripcionRepository, never()).existsByMiembro_IdAndClase_IdSesion(anyLong(), anyLong());
    }

    @Test
    void registrarAsistencia_MiembroNoInscrito_LanzaRuntimeException() {
        // Arrange
        Long claseId = 3L;
        Long miembroId = 2L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.of(clase));
        when(inscripcionRepository.existsByMiembro_IdAndClase_IdSesion(miembroId, claseId)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            entrenadorService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Error: El miembro NO está inscrito en esta clase.", exception.getMessage());
        verify(asistenciaRepository, never()).existsByMiembro_IdAndSesion_IdSesionAndFechaHoraBetween(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void registrarAsistencia_AsistenciaYaRegistradaHoy_LanzaRuntimeException() {
        // Arrange
        Long claseId = 3L;
        Long miembroId = 2L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.of(clase));
        when(inscripcionRepository.existsByMiembro_IdAndClase_IdSesion(miembroId, claseId)).thenReturn(true);
        when(asistenciaRepository.existsByMiembro_IdAndSesion_IdSesionAndFechaHoraBetween(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            entrenadorService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Error: La asistencia del miembro ya fue registrada para esta clase hoy.", exception.getMessage());
        verify(asistenciaRepository, never()).save(any(Asistencia.class));
    }
}