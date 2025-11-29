package org.groupfive.gymapi.service;

import org.groupfive.gymapi.Repository.AsistenciaRepository;
import org.groupfive.gymapi.Repository.ClaseRepository;
import org.groupfive.gymapi.Repository.MiembroRepository;
import org.groupfive.gymapi.dto.AsistenciaResponseDTO;
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Miembro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsistenciaServiceTest {

    @Mock
    private AsistenciaRepository asistenciaRepository;
    @Mock
    private MiembroRepository miembroRepository;
    @Mock
    private ClaseRepository claseRepository;

    @InjectMocks
    private AsistenciaService asistenciaService;

    private Asistencia asistencia1;
    private Miembro miembro;
    private Clase clase;

    @BeforeEach
    void setUp() {
        miembro = new Miembro();
        miembro.setId(1L);

        clase = new Clase();
        clase.setId(10L);

        asistencia1 = new Asistencia();
        asistencia1.setIdAsistencia(1L);
        asistencia1.setMiembro(miembro);
        asistencia1.setClase(clase);
        asistencia1.setFechaHora(LocalDateTime.now());
        asistencia1.setPresente(true);
    }

    @Test
    void registrarAsistencia_TodoCorrecto_GuardaAsistencia() {
        Long claseId = 10L;
        Long miembroId = 1L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.of(clase));
        when(asistenciaRepository.existsByMiembro_IdAndClase_IdAndFechaHoraBetween(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);

        asistenciaService.registrarAsistencia(claseId, miembroId);

        ArgumentCaptor<Asistencia> asistenciaCaptor = ArgumentCaptor.forClass(Asistencia.class);
        verify(asistenciaRepository, times(1)).save(asistenciaCaptor.capture());

        Asistencia savedAsistencia = asistenciaCaptor.getValue();
        assertEquals(miembro, savedAsistencia.getMiembro());
        assertEquals(clase, savedAsistencia.getClase());
        assertTrue(savedAsistencia.isPresente());
    }

    @Test
    void registrarAsistencia_MiembroNoExiste_LanzaRuntimeException() {
        Long claseId = 10L;
        Long miembroId = 99L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Miembro no encontrado.", exception.getMessage());
        verify(claseRepository, never()).findById(anyLong());
        verify(asistenciaRepository, never()).save(any(Asistencia.class));
    }

    @Test
    void registrarAsistencia_ClaseNoExiste_LanzaRuntimeException() {
        Long claseId = 99L;
        Long miembroId = 1L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Clase no encontrada.", exception.getMessage());
        verify(asistenciaRepository, never()).save(any(Asistencia.class));
    }

    @Test
    void registrarAsistencia_AsistenciaYaRegistradaHoy_LanzaRuntimeException() {
        Long claseId = 10L;
        Long miembroId = 1L;

        when(miembroRepository.findById(miembroId)).thenReturn(Optional.of(miembro));
        when(claseRepository.findById(claseId)).thenReturn(Optional.of(clase));
        when(asistenciaRepository.existsByMiembro_IdAndClase_IdAndFechaHoraBetween(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaService.registrarAsistencia(claseId, miembroId);
        });

        assertEquals("Error: La asistencia del miembro ya fue registrada para esta clase hoy.", exception.getMessage());
        verify(asistenciaRepository, never()).save(any(Asistencia.class));
    }

    @Test
    void obtenerAsistenciaPorId_Existe_RetornaDTO() {
        when(asistenciaRepository.findById(1L)).thenReturn(Optional.of(asistencia1));

        AsistenciaResponseDTO result = asistenciaService.obtenerAsistenciaPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdAsistencia());
        assertEquals(1L, result.getIdMiembro());
        assertEquals(10L, result.getIdClase());
        assertTrue(result.isPresente());
        verify(asistenciaRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerAsistenciaPorId_NoExiste_LanzaRuntimeException() {
        when(asistenciaRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaService.obtenerAsistenciaPorId(99L);
        });

        assertEquals("Registro de asistencia no encontrado.", exception.getMessage());
        verify(asistenciaRepository, times(1)).findById(99L);
    }

    @Test
    void obtenerTodasLasAsistencias_RetornaListaDTO() {
        Asistencia asistencia2 = new Asistencia();
        asistencia2.setIdAsistencia(2L);
        asistencia2.setMiembro(miembro);
        asistencia2.setClase(clase);
        asistencia2.setPresente(false);

        List<Asistencia> asistencias = Arrays.asList(asistencia1, asistencia2);
        when(asistenciaRepository.findAll()).thenReturn(asistencias);

        List<AsistenciaResponseDTO> results = asistenciaService.obtenerTodasLasAsistencias();

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(1L, results.get(0).getIdAsistencia());
        assertEquals(2L, results.get(1).getIdAsistencia());
        verify(asistenciaRepository, times(1)).findAll();
    }
}