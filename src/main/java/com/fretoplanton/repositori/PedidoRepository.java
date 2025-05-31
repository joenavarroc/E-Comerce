package com.fretoplanton.repositori;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fretoplanton.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}
