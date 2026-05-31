package br.com.galobet.controller;

import br.com.galobet.dto.UsuarioDTO;
import br.com.galobet.model.Usuario;
import br.com.galobet.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @PostMapping("/depositar")
    public UsuarioDTO depositar(
            @RequestParam Long usuarioId,
            @RequestParam double valor
    ) {

        return toDto(carteiraService.depositar(usuarioId, valor));
    }

    @PostMapping("/sacar")
    public UsuarioDTO sacar(
            @RequestParam Long usuarioId,
            @RequestParam double valor
    ) {

        return toDto(carteiraService.sacar(usuarioId, valor));
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
