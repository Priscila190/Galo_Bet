package br.com.galobet.service;

import br.com.galobet.exception.RegraNegocioException;
import br.com.galobet.model.Usuario;
import br.com.galobet.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // SALVAR USUÁRIO
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );
        return usuarioRepository.save(usuario);
    }

    // BUSCAR POR EMAIL
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElse(null);
    }

    // CRIAR USUÁRIO
    public Usuario criarUsuario(Usuario usuario) {
        if (buscarPorEmail(usuario.getEmail()) != null) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            usuario.setNome("");
        } else {
            usuario.setNome(usuario.getNome().trim());
        }

        usuario.setSaldo(1000);
        return salvar(usuario);
    }

    // LISTAR TODOS
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // BUSCAR POR ID
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    // ADICIONAR SALDO
    public Usuario adicionarSaldo(Long id, double valor) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (usuario != null) {
            usuario.setSaldo(usuario.getSaldo() + valor);
            return usuarioRepository.save(usuario);
        }

        return null;
    }

    // REMOVER SALDO
    public Usuario removerSaldo(Long id, double valor) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (usuario != null && usuario.getSaldo() >= valor) {
            usuario.setSaldo(usuario.getSaldo() - valor);
            return usuarioRepository.save(usuario);
        }

        return null;
    }

    // DELETAR
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // ATUALIZAR NOME
    public Usuario atualizarNome(Long id, String nome) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (usuario == null) {
            throw new RegraNegocioException("Usuário não encontrado");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new RegraNegocioException("Nome é obrigatório");
        }

        usuario.setNome(nome.trim());
        return usuarioRepository.save(usuario);
    }
}
