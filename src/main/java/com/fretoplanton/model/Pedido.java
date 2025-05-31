package com.fretoplanton.model;

import lombok.*;

import java.util.List;

import com.fretoplanton.enums.EstadoPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
@Data // Lombok genera getters, setters, toString, equals y hashcode
@NoArgsConstructor // Lombok genera el constructor sin argumentos
@AllArgsConstructor // Lombok genera el constructor con todos los parámetros
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallesPedido; // Relación con los productos del pedido

    @Column(name = "total", nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado;

    @Column(name = "fecha_pedido", nullable = false)
    private String fechaPedido;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    public void agregarProducto(DetallePedido detallePedido) {
        detallesPedido.add(detallePedido);
        this.total += detallePedido.getSubtotal();
    }

    public void eliminarProducto(DetallePedido detallePedido) {
        detallesPedido.remove(detallePedido);
        this.total -= detallePedido.getSubtotal();
    }
}
