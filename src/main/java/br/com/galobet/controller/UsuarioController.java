package br.com.galobet.controller;

import br.com.galobet.dto.AtualizarNomeDTO;
import br.com.galobet.dto.RegisterDTO;
import br.com.galobet.dto.UsuarioDTO;
import br.com.galobet.model.Usuario;
import br.com.galobet.service.UsuarioService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody RegisterDTO dto) {
        return registerUsuario(dto);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return this.usuarioService.listarUsuarios()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping("/register")
    public UsuarioDTO register(@RequestBody RegisterDTO dto) {
        return registerUsuario(dto);
    }

    @PatchMapping("/{id}/nome")
    public UsuarioDTO atualizarNome(
            @PathVariable Long id,
            @RequestBody AtualizarNomeDTO dto
    ) {
        return toDto(usuarioService.atualizarNome(id, dto.getNome()));
    }

    private UsuarioDTO registerUsuario(RegisterDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return toDto(usuarioService.criarUsuario(usuario));
    }

    private UsuarioDTO toDto(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSaldo()
        );
    }

}
