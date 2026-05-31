package br.com.galobet.service;

import br.com.galobet.dto.ApostaRequestDTO;
import br.com.galobet.dto.ApostaResponseDTO;
import br.com.galobet.enums.TipoApostaEnum;
import br.com.galobet.model.Aposta;
import br.com.galobet.model.Usuario;
import br.com.galobet.repository.ApostaRepository;
import br.com.galobet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApostaService {

    private final UsuarioRepository usuarioRepository;
    private final ApostaRepository apostaRepository;
    private final RoletaService roletaService;

    public ApostaService(
            UsuarioRepository usuarioRepository,
            ApostaRepository apostaRepository,
            RoletaService roletaService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.apostaRepository = apostaRepository;
        this.roletaService = roletaService;
    }

    public ApostaResponseDTO apostar(ApostaRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getSaldo() < dto.getValor()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        usuario.setSaldo(usuario.getSaldo() - dto.getValor());

        int numero = roletaService.girar();

        boolean ganhou = false;
        double premio = 0;

        if (dto.getTipoAposta() == TipoApostaEnum.PAR && numero != 0 && numero % 2 == 0) {
            ganhou = true;
        }

        if (dto.getTipoAposta() == TipoApostaEnum.IMPAR && numero % 2 != 0) {
            ganhou = true;
        }

        if (ganhou) {

            premio = dto.getValor() * 2;

            usuario.setSaldo(usuario.getSaldo() + premio);
        }

        usuarioRepository.save(usuario);

        Aposta aposta = new Aposta();

        aposta.setUsuario(usuario);
        aposta.setValor(dto.getValor());
        aposta.setPremio(premio);
        aposta.setNumeroResultado(numero);
        aposta.setGanhou(ganhou);
        aposta.setTipoAposta(dto.getTipoAposta());
        aposta.setDataAposta(LocalDateTime.now());

        apostaRepository.save(aposta);

        return new ApostaResponseDTO(
                ganhou,
                numero,
                usuario.getSaldo(),
                premio,
                ganhou ? "VOCÊ GANHOU!" : "VOCÊ PERDEU!"
        );
    }
}
