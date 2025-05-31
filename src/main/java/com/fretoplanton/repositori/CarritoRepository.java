package com.fretoplanton.repositori;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fretoplanton.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}
