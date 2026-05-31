package br.com.galobet.controller;

import br.com.galobet.dto.LoginRequest;
import br.com.galobet.dto.LoginResponse;
import br.com.galobet.dto.RegisterDTO;
import br.com.galobet.dto.UsuarioDTO;
import br.com.galobet.model.Usuario;
import br.com.galobet.security.Jwtservice;
import br.com.galobet.service.UsuarioService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final Jwtservice jwtservice;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UsuarioService usuarioService,
            Jwtservice jwtservice,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioService = usuarioService;
        this.jwtservice = jwtservice;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public UsuarioDTO register(@RequestBody RegisterDTO dto) {
        return toDto(criarUsuario(dto));
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Usuario usuario =
                usuarioService.buscarPorEmail(request.getEmail());

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        boolean senhaCorreta =
                passwordEncoder.matches(
                        request.getSenha(),
                        usuario.getSenha()
                );

        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        String token =
                jwtservice.gerarToken(usuario.getEmail());

        return new LoginResponse(
                token,
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSaldo()
        );
    }

    @GetMapping("/me")
    public UsuarioDTO me(Authentication authentication) {
        Usuario usuario = usuarioService.buscarPorEmail(authentication.getName());

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return toDto(usuario);
    }

    @GetMapping("/teste")
    public String teste() {
        return "rota protegida";
    }

    private Usuario criarUsuario(RegisterDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuarioService.criarUsuario(usuario);
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
