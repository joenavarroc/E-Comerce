package com.fretoplanton.repositori;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fretoplanton.model.DetalleCarrito;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}
