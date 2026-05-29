package com.finance.academia.controller;

import com.finance.academia.config.TokenService;
import com.finance.academia.dto.request.AuthRequest;
import com.finance.academia.dto.request.UsuarioRequest;
import com.finance.academia.dto.response.AuthResponse;
import com.finance.academia.dto.response.UsuarioResponse;
import com.finance.academia.mapper.UsuarioMapper;
import com.finance.academia.model.usuario.Usuario;
import com.finance.academia.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioResponse> createUser(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        Usuario usuarioSave = usuarioService.saveUsuario(usuario, request.academyId());
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toResponse(usuarioSave));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest loginRequest) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuario user = (Usuario) authentication.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listUsers(){
        List<Usuario> usuarioList = usuarioService.findAllUsuarios();
        List<UsuarioResponse> usuarioConvert = usuarioList.stream().map(UsuarioMapper::toResponse).toList();

        return ResponseEntity.ok().body(usuarioConvert);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<UsuarioResponse> findByEmail( @RequestParam String email){
        Usuario usuario = usuarioService.findUsuarioByEmail(email);

        return ResponseEntity.ok().body(UsuarioMapper.toResponse(usuario));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> deleteByEmail(@RequestParam String email){
        usuarioService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
