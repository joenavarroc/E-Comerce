package com.fretoplanton.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fretoplanton.model.Producto;
import com.fretoplanton.service.ProductoService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/agregar")
    public Producto agregarProducto(@RequestParam("nombre") String nombre,
                                    @RequestParam("precio") Double precio,
                                    @RequestParam("descripcion") String descripcion,
                                    @RequestParam("habilitado") boolean habilitado,
                                    @RequestParam("cantidad") Integer cantidad,
                                    @RequestParam(value = "imagenUrl", required = false) String imagenUrl,
                                    @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setHabilitado(habilitado);
        producto.setCantidad(cantidad);

        if (imagen != null && !imagen.isEmpty()) {
            producto.setImagen(imagen.getBytes()); // Guardar imagen como archivo binario
        } else if (imagenUrl != null && !imagenUrl.isEmpty()) {
            producto.setImagenUrl(imagenUrl); // Guardar la URL de la imagen
        }

        return productoService.agregarProducto(producto);
    }

    @PutMapping("/editar")
    public Producto editarProducto(@RequestParam("id") Long id,
                                   @RequestParam("nombre") String nombre,
                                   @RequestParam("precio") Double precio,
                                   @RequestParam("descripcion") String descripcion,
                                   @RequestParam("habilitado") boolean habilitado,
                                   @RequestParam("cantidad") Integer cantidad,
                                   @RequestParam(value = "imagenUrl", required = false) String imagenUrl,
                                   @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        Optional<Producto> existingProducto = productoService.buscarPorId(id);
        if (existingProducto.isPresent()) {
            Producto producto = existingProducto.get();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);
            producto.setHabilitado(habilitado);
            producto.setCantidad(cantidad);

            if (imagen != null && !imagen.isEmpty()) {
                producto.setImagen(imagen.getBytes()); // Actualizar la imagen
            } else if (imagenUrl != null && !imagenUrl.isEmpty()) {
                producto.setImagenUrl(imagenUrl); // Actualizar la URL de la imagen
            }

            return productoService.actualizarProducto(producto);
        }
        return null;
    }
}
