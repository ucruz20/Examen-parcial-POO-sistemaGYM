package org.groupfive.gymapi.repository;

import org.groupfive.gymapi.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    boolean existsByMiembro_IdAndSesion_IdSesionAndFechaHoraBetween(Long idMiembro, Long idClase, LocalDateTime startOfDay, LocalDateTime endOfDay);
}