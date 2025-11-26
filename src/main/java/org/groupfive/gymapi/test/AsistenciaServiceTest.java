package org.groupfive.gymapi.service;

import org.groupfive.gymapi.dto.AsistenciaResponseDTO;
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Miembro;
import org.groupfive.gymapi.repository.AsistenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsistenciaServiceTest {

    @Mock
    private AsistenciaRepository asistenciaRepository;

    @InjectMocks
    private AsistenciaService asistenciaService;

    private Asistencia asistencia1;
    private Asistencia asistencia2;
    private Miembro miembro;
    private Clase clase;

    @BeforeEach
    void setUp() {
        miembro = new Miembro();
        miembro.setId(1L);

        clase = new Clase();
        clase.setIdSesion(100L);

        asistencia1 = new Asistencia();
        asistencia1.setIdAsistencia(1L);
        asistencia1.setMiembro(miembro);
        asistencia1.setSesion(clase);
        asistencia1.setFechaHora(LocalDateTime.now());
        asistencia1.setPresente(true);

        asistencia2 = new Asistencia();
        asistencia2.setIdAsistencia(2L);
        asistencia2.setMiembro(miembro);
        asistencia2.setSesion(clase);
        asistencia2.setFechaHora(LocalDateTime.now().minusHours(1));
        asistencia2.setPresente(false);
    }

    @Test
    void obtenerAsistenciaPorId_Existe_RetornaDTO() {
        // Arrange
        when(asistenciaRepository.findById(1L)).thenReturn(Optional.of(asistencia1));

        // Act
        AsistenciaResponseDTO result = asistenciaService.obtenerAsistenciaPorId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdAsistencia());
        assertEquals(1L, result.getIdMiembro());
        assertEquals(100L, result.getIdClase());
        assertTrue(result.isPresente());
        verify(asistenciaRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerAsistenciaPorId_NoExiste_LanzaRuntimeException() {
        // Arrange
        when(asistenciaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaService.obtenerAsistenciaPorId(99L);
        });

        assertEquals("Registro de asistencia no encontrado.", exception.getMessage());
        verify(asistenciaRepository, times(1)).findById(99L);
    }

    @Test
    void obtenerTodasLasAsistencias_RetornaListaDTO() {
        // Arrange
        List<Asistencia> asistencias = Arrays.asList(asistencia1, asistencia2);
        when(asistenciaRepository.findAll()).thenReturn(asistencias);

        // Act
        List<AsistenciaResponseDTO> results = asistenciaService.obtenerTodasLasAsistencias();

        // Assert
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(1L, results.get(0).getIdAsistencia());
        assertEquals(2L, results.get(1).getIdAsistencia());
        verify(asistenciaRepository, times(1)).findAll();
    }
}