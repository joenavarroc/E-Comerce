package com.fretoplanton.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fretoplanton.model.Producto;
import com.fretoplanton.repositori.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Agregar un nuevo producto
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Buscar un producto por su id
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto por id
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
