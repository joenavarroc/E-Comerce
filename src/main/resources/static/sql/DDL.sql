-- Active: 1742437067050@@127.0.0.1@3306@fretoplant
DROP DATABASE IF EXISTS fretoplanton;

CREATE DATABASE fretoplanton;

USE fretoplanton;

-- Tabla para almacenar los roles (esto es útil si estás utilizando roles como un Enum)
CREATE TABLE rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Insertar los roles, si es que son pocos y predeterminados
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('CLIENT');

-- Tabla para almacenar los usuarios
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES rol(id) ON DELETE SET NULL
);

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2),
    descripcion TEXT,
    imagenUrl VARCHAR(255),
    imagen LONGBLOB,
    habilitado BOOLEAN DEFAULT true,
    cantidad INT
);

CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL, 
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(255) NOT NULL, 
    fecha_pedido VARCHAR(255) NOT NULL, 
    metodo_pago VARCHAR(255) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES usuario(id)
);

CREATE TABLE detalle_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE carrito (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES usuario(id)
);

CREATE TABLE detalle_carrito (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    carrito_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (carrito_id) REFERENCES carrito(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

