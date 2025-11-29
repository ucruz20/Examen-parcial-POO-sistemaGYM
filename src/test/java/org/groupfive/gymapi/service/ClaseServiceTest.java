package org.groupfive.gymapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.groupfive.gymapi.Repository.ClaseRepository;
import org.groupfive.gymapi.Repository.EntrenadorRepository;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Entrenador;
import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.dto.ClaseResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class ClaseServiceTest {

    @Mock
    private ClaseRepository claseRepository;

    @Mock
    private EntrenadorRepository entrenadorRepository;

    @InjectMocks
    private ClaseService claseService;

    // createClass

    @Test
    void testCreateClase_RegistroExitoso() {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Carlos");
        entrenador.setEspecialidad("Yoga");

        ClaseRequest request = new ClaseRequest();
        request.setNombre("Clase de Yoga");
        request.setHorario("Lunes 8am");
        request.setCupoMaximo(7);
        request.setEntrenadorId(1L);

        when(entrenadorRepository.findById(1L)).thenReturn(Optional.of(entrenador));

        ClaseResponse response = claseService.createClass(request);

        assertEquals("Clase de Yoga", response.getNombre());
        assertEquals("Lunes 8am", response.getHorario());
        assertEquals(7, response.getCupoMaximo());

        verify(claseRepository).save(any(Clase.class));
    }

    @Test
    void testCreateClase_EntrenadorNoEncontrado() {
        ClaseRequest request = new ClaseRequest();
        request.setEntrenadorId(99L);

        when(entrenadorRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> claseService.createClass(request));
        assertEquals("ENtrenador no encontrado", ex.getMessage());
    }

    // editorInfo

    @Test
    void testEditorInfo_Exitoso() {
        Entrenador entrenadorViejo = new Entrenador();
        entrenadorViejo.setId(1L);
        entrenadorViejo.setNombre("Carlos");
        entrenadorViejo.setEspecialidad("Yoga");

        Entrenador entrenadorNuevo = new Entrenador();
        entrenadorNuevo.setId(2L);
        entrenadorNuevo.setNombre("Ana");
        entrenadorNuevo.setEspecialidad("Pilates");

        Clase clase = new Clase();
        clase.setId(10L);
        clase.setNombre("Clase Vieja");
        clase.setHorario("Martes 9am");
        clase.setCupoMaximo(15);
        clase.setEntrenador(entrenadorViejo);

        ClaseRequest request = new ClaseRequest();
        request.setNombre("Clase Actualizada");
        request.setHorario("Miércoles 10am");
        request.setCupoMaximo(25);
        request.setEntrenadorId(2L);

        when(claseRepository.findById(10L)).thenReturn(Optional.of(clase));
        when(entrenadorRepository.findById(2L)).thenReturn(Optional.of(entrenadorNuevo));

        ClaseResponse response = claseService.editorInfo(10L, request);

        assertEquals("Clase Actualizada", response.getNombre());
        assertEquals("Miércoles 10am", response.getHorario());
        assertEquals(25, response.getCupoMaximo());
        assertEquals("Ana", response.getEntrenador().getNombre());
        verify(claseRepository).save(clase);
    }

    @Test
    void testEditorInfo_ClaseNoEncontrada() {
        ClaseRequest request = new ClaseRequest();
        request.setEntrenadorId(1L);

        when(claseRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> claseService.editorInfo(99L, request));
        assertEquals("Clase no encontrado", ex.getMessage());
    }

    // getClasses

    @Test
    void testGetClasses_Exitoso() {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Carlos");
        entrenador.setEspecialidad("Yoga");

        Clase clase1 = new Clase();
        clase1.setId(10L);
        clase1.setNombre("Yoga Básico");
        clase1.setHorario("Lunes 8am");
        clase1.setCupoMaximo(20);
        clase1.setEntrenador(entrenador);

        Clase clase2 = new Clase();
        clase2.setId(11L);
        clase2.setNombre("Yoga Avanzado");
        clase2.setHorario("Martes 9am");
        clase2.setCupoMaximo(15);
        clase2.setEntrenador(entrenador);

        when(claseRepository.findAll()).thenReturn(List.of(clase1, clase2));

        var responses = claseService.getClasses();

        assertEquals(2, responses.size());
        assertEquals("Yoga Básico", responses.get(0).getNombre());
        assertEquals("Yoga Avanzado", responses.get(1).getNombre());
        assertEquals("Carlos", responses.get(0).getEntrenador().getNombre());
    }

    @Test
    void testGetClasses_ListaVacia() {
        when(claseRepository.findAll()).thenReturn(List.of());

        var responses = claseService.getClasses();

        assertTrue(responses.isEmpty());
    }

    // eliminate

    @Test
    void testEliminate_Exitoso() {
        when(claseRepository.existsById(10L)).thenReturn(true);

        claseService.eliminate(10L);

        verify(claseRepository, times(1)).deleteById(10L);
}

    @Test
    void testEliminate_ClaseNoEncontrada() {
        when(claseRepository.existsById(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> claseService.eliminate(99L));
        assertEquals("Clase no encontrado", ex.getMessage());
    }
    
}
