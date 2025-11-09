package org.groupfive.gymapi.Repository;

import org.groupfive.gymapi.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByMiembro_IdAndClase_Id(Long miembroId, Long claseId);
    long countByClase_Id(Long claseId);
}


