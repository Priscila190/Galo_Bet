package br.com.galobet.service;

import br.com.galobet.model.Usuario;
import br.com.galobet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    private final UsuarioRepository usuarioRepository;

    public CarteiraService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario sacar(Long usuarioId, double valor) {

        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        if (usuario.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente");
        }

        usuario.setSaldo(
                usuario.getSaldo() - valor
        );

        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario depositar(Long usuarioId, double valor) {

        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        usuario.setSaldo(
                usuario.getSaldo() + valor
        );

        usuarioRepository.save(usuario);
        return usuario;
    }
}
