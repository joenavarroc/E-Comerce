package com.fretoplanton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fretoplanton.enums.EstadoPedido;
import com.fretoplanton.model.DetallePedido;
import com.fretoplanton.model.Pedido;
import com.fretoplanton.repositori.DetallePedidoRepository;
import com.fretoplanton.repositori.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public Pedido realizarPedido(Long carritoId, Pedido pedido) {
        // Aquí se puede agregar lógica adicional, como la validación de pago, etc.
        pedidoRepository.save(pedido);
        for (DetallePedido detalle : pedido.getDetallesPedido()) {
            detalle.setPedido(pedido);
            detallePedidoRepository.save(detalle);
        }
        return pedido;
    }

    @Transactional
    public Pedido cambiarEstado(Long pedidoId, EstadoPedido estado) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }
}
