package com.fretoplanton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fretoplanton.model.Carrito;
import com.fretoplanton.model.DetalleCarrito;
import com.fretoplanton.service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/{carritoId}/agregar")
    public ResponseEntity<Carrito> agregarProducto(@PathVariable Long carritoId, @RequestBody DetalleCarrito detalleCarrito) {
        Carrito carritoActualizado = carritoService.agregarProducto(carritoId, detalleCarrito);
        return ResponseEntity.ok(carritoActualizado);
    }

    @PostMapping("/{carritoId}/eliminar")
    public ResponseEntity<Carrito> eliminarProducto(@PathVariable Long carritoId, @RequestBody DetalleCarrito detalleCarrito) {
        Carrito carritoActualizado = carritoService.eliminarProducto(carritoId, detalleCarrito);
        return ResponseEntity.ok(carritoActualizado);
    }
}

