package com.fretoplanton.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    private Double precio;

    private String descripcion;

    private String imagenUrl;  // URL de la imagen (opcional si se sube desde el dispositivo)

    @Lob
    private byte[] imagen; // Para almacenar la imagen si se sube desde el dispositivo

    private boolean habilitado;

    private Integer cantidad; // Cantidad disponible
}
