package com.fretoplanton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fretoplanton.model.Carrito;
import com.fretoplanton.model.DetalleCarrito;
import com.fretoplanton.repositori.CarritoRepository;
import com.fretoplanton.repositori.DetalleCarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Transactional
    public Carrito agregarProducto(Long carritoId, DetalleCarrito detalleCarrito) {
        Carrito carrito = carritoRepository.findById(carritoId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.agregarProducto(detalleCarrito);
        detalleCarritoRepository.save(detalleCarrito);
        return carritoRepository.save(carrito);
    }

    @Transactional
    public Carrito eliminarProducto(Long carritoId, DetalleCarrito detalleCarrito) {
        Carrito carrito = carritoRepository.findById(carritoId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.eliminarProducto(detalleCarrito);
        detalleCarritoRepository.delete(detalleCarrito);
        return carritoRepository.save(carrito);
    }
}

