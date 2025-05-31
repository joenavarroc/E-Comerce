package com.fretoplanton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fretoplanton.enums.EstadoPedido;
import com.fretoplanton.model.Pedido;
import com.fretoplanton.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/realizar/{carritoId}")
    public ResponseEntity<Pedido> realizarPedido(@PathVariable Long carritoId, @RequestBody Pedido pedido) {
        Pedido pedidoRealizado = pedidoService.realizarPedido(carritoId, pedido);
        return ResponseEntity.ok(pedidoRealizado);
    }

    @PostMapping("/cambiarEstado/{pedidoId}")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long pedidoId, @RequestParam EstadoPedido estado) {
        Pedido pedidoActualizado = pedidoService.cambiarEstado(pedidoId, estado);
        return ResponseEntity.ok(pedidoActualizado);
    }
}

