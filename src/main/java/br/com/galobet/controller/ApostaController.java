package br.com.galobet.controller;

import br.com.galobet.dto.ApostaRequestDTO;
import br.com.galobet.dto.ApostaResponseDTO;
import br.com.galobet.service.ApostaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apostas")
public class ApostaController {

    private final ApostaService apostaService;

    public ApostaController(ApostaService apostaService) {
        this.apostaService = apostaService;
    }

    @PostMapping
    public ApostaResponseDTO apostar(
            @RequestBody ApostaRequestDTO dto
    ) {
        return apostaService.apostar(dto);
    }
}