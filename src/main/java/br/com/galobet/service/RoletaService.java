package br.com.galobet.service;

import br.com.galobet.dto.ApostaResponseDTO;
import br.com.galobet.enums.CorRoleta;
import br.com.galobet.model.Aposta;
import br.com.galobet.model.Usuario;
import br.com.galobet.repository.ApostaRepository;
import br.com.galobet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import java.util.Random;

@Service
public class RoletaService {

    private static final Map<Integer, CorRoleta> CORES_ROULETTE = criarMapaDeCores();

    private final UsuarioRepository usuarioRepository;
    private final ApostaRepository apostaRepository;

    public RoletaService(
            UsuarioRepository usuarioRepository,
            ApostaRepository apostaRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.apostaRepository = apostaRepository;
    }

    public ApostaResponseDTO jogar(String email, double valor, String escolha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente");
        }

        String escolhaNormalizada = escolha == null ? "" : escolha.trim();
        CorRoleta corEscolhida = normalizarCor(escolhaNormalizada);
        Integer numeroEscolhido = normalizarNumero(escolhaNormalizada);

        if (corEscolhida == null && numeroEscolhido == null) {
            throw new RuntimeException("Escolha inválida");
        }

        usuario.setSaldo(usuario.getSaldo() - valor);

        int numero = girar();
        CorRoleta corResultado = definirCor(numero);
        boolean ganhou = calcularGanho(numero, corResultado, corEscolhida, numeroEscolhido);
        double premio = 0;

        if (ganhou) {
            premio = calcularPremio(valor, corEscolhida, numeroEscolhido);
            usuario.setSaldo(usuario.getSaldo() + premio);
        }

        usuarioRepository.save(usuario);

        Aposta aposta = new Aposta();
        aposta.setUsuario(usuario);
        aposta.setValor(valor);
        aposta.setEscolha(escolhaNormalizada);
        aposta.setNumeroResultado(numero);
        aposta.setCorResultado(corResultado);
        aposta.setGanhou(ganhou);
        aposta.setPremio(premio);
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

    private CorRoleta definirCor(int numero) {
        return CORES_ROULETTE.getOrDefault(numero, CorRoleta.VERDE);
    }

    private boolean calcularGanho(
            int numero,
            CorRoleta corResultado,
            CorRoleta corEscolhida,
            Integer numeroEscolhido
    ) {
        if (corEscolhida != null) {
            return corResultado == corEscolhida;
        }

        return numeroEscolhido != null && numeroEscolhido == numero;
    }

    private double calcularPremio(
            double valor,
            CorRoleta corEscolhida,
            Integer numeroEscolhido
    ) {
        if (corEscolhida != null) {
            if (corEscolhida == CorRoleta.VERDE) {
                return valor * 14;
            }

            return valor * 2;
        }

        return numeroEscolhido != null ? valor * 10 : 0;
    }

    private CorRoleta normalizarCor(String escolha) {
        String valor = escolha.toUpperCase(Locale.ROOT);

        return switch (valor) {
            case "VERMELHO", "RED" -> CorRoleta.VERMELHO;
            case "PRETO", "BLACK" -> CorRoleta.PRETO;
            case "VERDE", "GREEN" -> CorRoleta.VERDE;
            default -> null;
        };
    }

    private Integer normalizarNumero(String escolha) {
        try {
            int apostaNumero = Integer.parseInt(escolha);

            if (apostaNumero < 0 || apostaNumero > 36) {
                return null;
            }

            return apostaNumero;
        } catch (Exception e) {
            return null;
        }
    }

    public int girar() {
        return new Random().nextInt(37);
    }

    private static Map<Integer, CorRoleta> criarMapaDeCores() {
        Map<Integer, CorRoleta> cores = new HashMap<>();

        cores.put(0, CorRoleta.VERDE);
        cores.put(1, CorRoleta.VERMELHO);
        cores.put(2, CorRoleta.PRETO);
        cores.put(3, CorRoleta.VERMELHO);
        cores.put(4, CorRoleta.PRETO);
        cores.put(5, CorRoleta.VERMELHO);
        cores.put(6, CorRoleta.PRETO);
        cores.put(7, CorRoleta.VERMELHO);
        cores.put(8, CorRoleta.PRETO);
        cores.put(9, CorRoleta.VERMELHO);
        cores.put(10, CorRoleta.PRETO);
        cores.put(11, CorRoleta.PRETO);
        cores.put(12, CorRoleta.VERMELHO);
        cores.put(13, CorRoleta.PRETO);
        cores.put(14, CorRoleta.VERMELHO);
        cores.put(15, CorRoleta.PRETO);
        cores.put(16, CorRoleta.VERMELHO);
        cores.put(17, CorRoleta.PRETO);
        cores.put(18, CorRoleta.VERMELHO);
        cores.put(19, CorRoleta.VERMELHO);
        cores.put(20, CorRoleta.PRETO);
        cores.put(21, CorRoleta.VERMELHO);
        cores.put(22, CorRoleta.PRETO);
        cores.put(23, CorRoleta.VERMELHO);
        cores.put(24, CorRoleta.PRETO);
        cores.put(25, CorRoleta.VERMELHO);
        cores.put(26, CorRoleta.PRETO);
        cores.put(27, CorRoleta.VERMELHO);
        cores.put(28, CorRoleta.PRETO);
        cores.put(29, CorRoleta.PRETO);
        cores.put(30, CorRoleta.VERMELHO);
        cores.put(31, CorRoleta.PRETO);
        cores.put(32, CorRoleta.VERMELHO);
        cores.put(33, CorRoleta.PRETO);
        cores.put(34, CorRoleta.VERMELHO);
        cores.put(35, CorRoleta.PRETO);
        cores.put(36, CorRoleta.VERMELHO);

        return cores;
    }
}
