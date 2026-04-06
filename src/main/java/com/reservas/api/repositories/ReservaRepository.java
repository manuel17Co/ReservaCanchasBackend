package com.reservas.api.repositories;

import com.reservas.api.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuarioIdOrderByCreatedAtDesc(Long usuarioId);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.horario JOIN FETCH r.usuario " +
           "WHERE r.usuario.id = :usuarioId ORDER BY r.createdAt DESC")
    List<Reserva> findByUsuarioIdWithDetails(@Param("usuarioId") Long usuarioId);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.horario JOIN FETCH r.usuario " +
           "WHERE r.horario.cancha.id = :canchaId AND r.fecha = :fecha")
    List<Reserva> findByCanchaIdAndFecha(@Param("canchaId") Long canchaId,
                                          @Param("fecha") LocalDate fecha);

    @Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.horario.id = :horarioId AND r.fecha = :fecha AND r.estado != 'CANCELADA'")
    boolean existsByHorarioIdAndFecha(@Param("horarioId") Long horarioId, @Param("fecha") LocalDate fecha);
}
