package org.groupfive.gymapi.Repository;

import org.groupfive.gymapi.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByMiembro_IdAndClase_Id(Long idMiembro, Long idClase);
    long countByClase_Id(Long idClase);
}


