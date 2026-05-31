package br.com.galobet.controller;

import br.com.galobet.model.Jogo;
import br.com.galobet.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public List<Jogo> listar() {
        return jogoService.listarTodos();
    }

    @PostMapping
    public Jogo criar(@RequestBody Jogo jogo) {
        return jogoService.salvar(jogo);
    }

    @GetMapping("/{id}")
    public Jogo buscarPorId(@PathVariable Long id) {
        return jogoService.buscarPorId(id);
    }
}
