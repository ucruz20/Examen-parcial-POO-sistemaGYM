package org.groupfive.gymapi.Repository;

import org.groupfive.gymapi.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    boolean existsByMiembro_IdAndClase_IdAndFechaHoraBetween(
            Long idMiembro, Long idClase, LocalDateTime inicio, LocalDateTime fin);
}
