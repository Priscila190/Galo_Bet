package br.com.galobet.service;

import br.com.galobet.enums.SimboloRaspadinha;
import br.com.galobet.model.Raspadinha;
import br.com.galobet.repository.RaspadinhaRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RaspadinhaService {

    private final CarteiraService carteiraService;
    private final RaspadinhaRepository repository;

    public RaspadinhaService(
            CarteiraService carteiraService,
            RaspadinhaRepository repository
    ) {
        this.carteiraService = carteiraService;
        this.repository = repository;
    }

    public Raspadinha jogar(Long usuarioId, double valor) {

        // 1. debita aposta
        carteiraService.sacar(usuarioId, valor);

        // 2. gera símbolos
        SimboloRaspadinha[] resultado = gerar();

        // 3. verifica vitória
        boolean ganhou = verificar(resultado);

        double premio = 0;

        if (ganhou) {
            premio = valor * 5;
            carteiraService.depositar(usuarioId, premio);
        }

        // 4. salva aposta
        Raspadinha r = new Raspadinha();
        r.setUsuarioId(usuarioId);
        r.setValor(valor);
        r.setPos1(resultado[0]);
        r.setPos2(resultado[1]);
        r.setPos3(resultado[2]);
        r.setGanhou(ganhou);
        r.setPremio(premio);

        return repository.save(r);
    }

    private SimboloRaspadinha[] gerar() {

        SimboloRaspadinha[] valores = SimboloRaspadinha.values();
        Random random = new Random();

        return new SimboloRaspadinha[] {
                valores[random.nextInt(valores.length)],
                valores[random.nextInt(valores.length)],
                valores[random.nextInt(valores.length)]
        };
    }

    private boolean verificar(SimboloRaspadinha[] s) {
        return s[0] == s[1] && s[1] == s[2];
    }
}