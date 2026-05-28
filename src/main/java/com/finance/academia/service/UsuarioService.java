package com.finance.academia.service;

import com.finance.academia.exception.ResourceNotFoundException;
import com.finance.academia.model.academia.Academy;
import com.finance.academia.model.usuario.Usuario;
import com.finance.academia.repository.AcademyRepository;
import com.finance.academia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AcademyRepository academyRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario saveUsuario(Usuario usuario, Long academyId) {
        Academy academy = academyRepository.findById(academyId)
                .orElseThrow(() -> new ResourceNotFoundException("Academia não encontrada com id: " + academyId));

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com esse email");
        }

        usuario.setAcademy(academy);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
    }

    public void deleteByEmail(String email) {
        Usuario usuario = findUsuarioByEmail(email);
        usuarioRepository.delete(usuario);
    }
}