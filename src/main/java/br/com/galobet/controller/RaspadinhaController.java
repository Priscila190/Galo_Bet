package br.com.galobet.controller;

import br.com.galobet.model.Raspadinha;
import br.com.galobet.service.RaspadinhaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/raspadinha")
public class RaspadinhaController {

    private final RaspadinhaService service;

    public RaspadinhaController(RaspadinhaService service) {
        this.service = service;
    }

    @PostMapping("/jogar")
    public Raspadinha jogar(
            @RequestParam Long usuarioId,
            @RequestParam double valor
    ) {

        return service.jogar(usuarioId, valor);
    }
}
