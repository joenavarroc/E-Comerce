package com.fretoplanton.model;

import lombok.*;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "carrito")
@Data // Lombok genera getters, setters, toString, equals y hashcode
@NoArgsConstructor // Lombok genera el constructor sin argumentos
@AllArgsConstructor // Lombok genera el constructor con todos los parámetros
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCarrito> detallesCarrito; // Relación con los productos en el carrito

    @Column(name = "total", nullable = false)
    private Double total;

    public void agregarProducto(DetalleCarrito detalleCarrito) {
        detallesCarrito.add(detalleCarrito);
        this.total += detalleCarrito.getSubtotal();
    }

    public void eliminarProducto(DetalleCarrito detalleCarrito) {
        detallesCarrito.remove(detalleCarrito);
        this.total -= detalleCarrito.getSubtotal();
    }
}
