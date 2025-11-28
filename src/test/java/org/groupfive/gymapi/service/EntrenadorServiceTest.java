package org.groupfive.gymapi.service;

import org.groupfive.gymapi.Repository.ClaseRepository;
import org.groupfive.gymapi.Repository.EntrenadorRepository;
import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Entrenador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntrenadorServiceTest {

    @Mock
    private EntrenadorRepository entrenadorRepository;
    @Mock
    private ClaseRepository claseRepository;

    @InjectMocks
    private EntrenadorService entrenadorService;

    private Entrenador entrenador;
    private Clase clase;
    private ClaseRequest claseRequest;

    @BeforeEach
    void setUp() {
        entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Carlos");

        clase = new Clase();
        clase.setId(10L);
        clase.setNombre("Pilates");
        clase.setHorario(LocalTime.of(15, 30));
        clase.setCupoMaximo(15);
        clase.setEntrenador(entrenador);

        claseRequest = new ClaseRequest();
        claseRequest.setEntrenadorId(1L);
        claseRequest.setNombre("Pilates");
        claseRequest.setHorario(LocalTime.of(15, 30));
        claseRequest.setCupoMaximo(15);
    }

    @Test
    void crearClase_EntrenadorExiste_GuardaYRetornaClase() {
        when(entrenadorRepository.findById(1L)).thenReturn(Optional.of(entrenador));
        when(claseRepository.save(any(Clase.class))).thenReturn(clase);

        Clase result = entrenadorService.crearClase(claseRequest);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Pilates", result.getNombre());
        verify(entrenadorRepository, times(1)).findById(1L);
        verify(claseRepository, times(1)).save(any(Clase.class));
    }

    @Test
    void crearClase_EntrenadorNoExiste_LanzaRuntimeException() {
        when(entrenadorRepository.findById(99L)).thenReturn(Optional.empty());
        claseRequest.setEntrenadorId(99L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            entrenadorService.crearClase(claseRequest);
        });

        assertEquals("Error: Entrenador no encontrado para asignar la clase.", exception.getMessage());
        verify(entrenadorRepository, times(1)).findById(99L);
        verify(claseRepository, never()).save(any(Clase.class));
    }
}
