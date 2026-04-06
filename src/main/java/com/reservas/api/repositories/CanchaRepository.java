package com.reservas.api.repositories;

import com.reservas.api.models.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long> {

    List<Cancha> findByActivaTrueOrderByNombreAsc();

    List<Cancha> findBySedeIdAndActivaTrue(Long sedeId);

    List<Cancha> findByTipoCanchaIdAndActivaTrue(Long tipoCanchaId);

    List<Cancha> findBySedeIdAndTipoCanchaIdAndActivaTrue(Long sedeId, Long tipoCanchaId);

    @Query("SELECT c FROM Cancha c WHERE c.activa = true " +
           "AND (:sedeId IS NULL OR c.sede.id = :sedeId) " +
           "AND (:tipoCanchaId IS NULL OR c.tipoCancha.id = :tipoCanchaId)")
    List<Cancha> findCanchasDisponibles(@Param("sedeId") Long sedeId,
                                         @Param("tipoCanchaId") Long tipoCanchaId);

    @Query("SELECT c FROM Cancha c JOIN FETCH c.sede JOIN FETCH c.tipoCancha " +
           "WHERE c.id = :id AND c.activa = true")
    Cancha findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT c FROM Cancha c JOIN FETCH c.sede JOIN FETCH c.tipoCancha " +
           "WHERE (:sedeId IS NULL OR c.sede.id = :sedeId) " +
           "AND (:tipoCanchaId IS NULL OR c.tipoCancha.id = :tipoCanchaId)")
    List<Cancha> findAllWithDetails(@Param("sedeId") Long sedeId,
                                    @Param("tipoCanchaId") Long tipoCanchaId);
}
