package br.com.galobet.controller;

import br.com.galobet.dto.ApostaResponseDTO;
import br.com.galobet.service.RoletaService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roleta")
public class RoletaController {

    private final RoletaService roletaService;

    public RoletaController(RoletaService roletaService) {
        this.roletaService = roletaService;
    }

    @PostMapping("/jogar")
    public ApostaResponseDTO jogar(
            @RequestParam double valor,
            @RequestParam String escolha,
            Authentication authentication
    ) {

        return roletaService.jogar(authentication.getName(), valor, escolha);
    }
}
