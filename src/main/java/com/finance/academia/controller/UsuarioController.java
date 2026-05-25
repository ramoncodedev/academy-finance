package com.finance.academia.controller;

import com.finance.academia.dto.request.UsuarioRequest;
import com.finance.academia.dto.response.UsuarioResponse;
import com.finance.academia.mapper.UsuarioMapper;
import com.finance.academia.model.Usuario;
import com.finance.academia.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> createUser( @RequestBody @Valid UsuarioRequest request){

        Usuario usuario = UsuarioMapper.toEntity(request);
        Usuario usuarioSave = usuarioService.saveUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toResponse(usuarioSave));

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listUsers(){
        List<Usuario> usuarioList = usuarioService.findAllUsuarios();
        List<UsuarioResponse> usuarioConvert = usuarioList.stream().map(UsuarioMapper::toResponse).toList();

        return ResponseEntity.ok().body(usuarioConvert);
    }

    @GetMapping("/search")
    public ResponseEntity<UsuarioResponse> findByEmail( @RequestParam String email){
        Usuario usuario = usuarioService.findUsuarioByEmail(email);

        return ResponseEntity.ok().body(UsuarioMapper.toResponse(usuario));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByEmail(@RequestParam String email){
        usuarioService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
