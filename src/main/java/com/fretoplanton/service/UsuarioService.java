package com.fretoplanton.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fretoplanton.model.Usuario;
import com.fretoplanton.repositori.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Acá podrías validar duplicados, encriptar contraseñas, etc.
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}
