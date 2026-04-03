package com.reservas.api.repositories;

import com.reservas.api.models.TipoCancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoCanchaRepository extends JpaRepository<TipoCancha, Long> {
    List<TipoCancha> findByOrderByNombreAsc();
    boolean existsByNombre(String nombre);
}
