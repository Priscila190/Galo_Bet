package br.com.galobet.service;

import br.com.galobet.model.Jogo;
import br.com.galobet.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public List<Jogo> listar() {
        return repository.findAll();
    }

    public List<Jogo> listarTodos() {
            return null;
    }

    @Autowired
    private JogoRepository jogoRepository;

    public Jogo buscarPorId(Long id) {

        return jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
    }
}