package com.api.av2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.av2.model.Usuario;
import com.api.av2.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Criar usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Listar usuario
    public Optional<Usuario> listarUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    // Listar todos os usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Atualizar usuario
    public Usuario updateUsuario(Long id, Usuario usuarioUpdate) {
        return usuarioRepository.findById(id).map(usuario -> {
        	usuario.setNome(usuarioUpdate.getNome());
        	usuario.setEmail(usuarioUpdate.getEmail());
        	usuario.setSenha(usuarioUpdate.getSenha());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    // Deletar usuario
    public void deleteUsuario(Long id) {
    	usuarioRepository.deleteById(id);
    }
}

