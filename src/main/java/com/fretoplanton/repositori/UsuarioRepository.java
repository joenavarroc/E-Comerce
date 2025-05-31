package com.fretoplanton.repositori;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fretoplanton.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
