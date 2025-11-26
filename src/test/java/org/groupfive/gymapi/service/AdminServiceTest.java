package org.groupfive.gymapi.service;

import org.groupfive.gymapi.Repository.EntrenadorRepository;
import org.groupfive.gymapi.dto.EntrenadorRequestDTO;
import org.groupfive.gymapi.model.Entrenador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private EntrenadorService entrenadorService;

    @Mock
    private EntrenadorRepository entrenadorRepository;

    @Mock
    private AsistenciaService asistenciaService;
    @Mock
    private ClaseService claseService;
    @Mock
    private InscripcionService inscripcionService;
    @Mock
    private MiembroService miembroService;

    @InjectMocks
    private AdminService adminService;

    // ----------------------------------------------------
    // crearEntrenador
    // ----------------------------------------------------
    @Test
    void crearEntrenador_DeberiaGuardarYRetornarEntrenador() {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre("Carlos");
        entrenador.setEspecialidad("Fuerza");

        Entrenador guardado = new Entrenador();
        guardado.setId(1L);
        guardado.setNombre("Carlos");
        guardado.setEspecialidad("Fuerza");

        when(entrenadorRepository.save(entrenador)).thenReturn(guardado);

        Entrenador result = adminService.crearEntrenador(entrenador);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNombre()).isEqualTo("Carlos");

        verify(entrenadorRepository).save(entrenador);
    }


    // actualizarEntrenador - caso éxito
    @Test
    void actualizarEntrenador_DeberiaActualizarCuandoExiste() {
        Long id = 1L;

        Entrenador existente = new Entrenador();
        existente.setId(id);
        existente.setNombre("Viejo Nombre");
        existente.setEspecialidad("Vieja Especialidad");
        existente.setSalario(BigDecimal.valueOf(500));

        EntrenadorRequestDTO dto = new EntrenadorRequestDTO();
        dto.setNombre("Nuevo Nombre");
        dto.setEspecialidad("Nueva Especialidad");
        dto.setSalario(BigDecimal.valueOf(1000));

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(existente));
        when(entrenadorRepository.save(any(Entrenador.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Entrenador result = adminService.actualizarEntrenador(id, dto);

        assertThat(result.getNombre()).isEqualTo("Nuevo Nombre");
        assertThat(result.getEspecialidad()).isEqualTo("Nueva Especialidad");
        assertThat(result.getSalario()).isEqualTo(BigDecimal.valueOf(1000));

        verify(entrenadorRepository).findById(id);
        verify(entrenadorRepository).save(existente);
    }

    // ----------------------------------------------------
    // actualizarEntrenador - caso no encontrado
    @Test
    void actualizarEntrenador_DeberiaFallarSiNoExiste() {
        Long id = 1L;

        EntrenadorRequestDTO dto = new EntrenadorRequestDTO();
        dto.setNombre("Nuevo Nombre");
        dto.setEspecialidad("Nueva Especialidad");
        dto.setSalario(BigDecimal.valueOf(1000));

        when(entrenadorRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminService.actualizarEntrenador(id, dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Entrenador no encontrado");

        verify(entrenadorRepository).findById(id);
        verify(entrenadorRepository, never()).save(any());
    }

    // ----------------------------------------------------
    // listarEntrenadores

    @Test
    void listarEntrenadores_DeberiaRetornarListaDeEntrenadores() {
        Entrenador e1 = new Entrenador();
        e1.setId(1L);
        Entrenador e2 = new Entrenador();
        e2.setId(2L);

        when(entrenadorRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Entrenador> result = adminService.listarEntrenadores();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("id").containsExactly(1L, 2L);

        verify(entrenadorRepository).findAll();
    }

    // ----------------------------------------------------
    // eliminarEntrenador
    @Test
void eliminarEntrenador_DeberiaLlamarDeleteById() {
    Long id = 1L;

    adminService.eliminarEntrenador(id);

    verify(entrenadorRepository).deleteById(id);
}
}