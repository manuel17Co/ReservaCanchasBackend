package com.reservas.api.repositories;

import com.reservas.api.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByCanchaIdOrderByHoraInicioAsc(Long canchaId);

    @Query("SELECT h FROM Horario h JOIN h.cancha c " +
           "WHERE h.cancha.id = :canchaId " +
           "ORDER BY h.horaInicio ASC")
    List<Horario> findByCanchaIdWithDetails(@Param("canchaId") Long canchaId);

    @Query("SELECT h FROM Horario h " +
           "WHERE h.cancha.id = :canchaId " +
           "AND h.disponible = true " +
           "AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.horario.id = h.id AND r.fecha = :fecha AND r.estado != 'CANCELADA')")
    List<Horario> findHorariosDisponibles(@Param("canchaId") Long canchaId,
                                           @Param("fecha") LocalDate fecha);

    @Query("SELECT h FROM Horario h JOIN h.cancha c " +
           "WHERE h.cancha.id = :canchaId " +
           "AND h.disponible = true " +
           "AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.horario.id = h.id AND r.fecha = :fecha AND r.estado != 'CANCELADA') " +
           "ORDER BY h.horaInicio ASC")
    List<Horario> findHorariosDisponiblesWithDetails(@Param("canchaId") Long canchaId,
                                                      @Param("fecha") LocalDate fecha);

    @Query("SELECT h FROM Horario h WHERE h.cancha.id = :canchaId ORDER BY h.horaInicio ASC")
    List<Horario> findSimpleByCanchaId(@Param("canchaId") Long canchaId);
}
