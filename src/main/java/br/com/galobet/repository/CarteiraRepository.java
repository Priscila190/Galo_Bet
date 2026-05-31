package br.com.galobet.repository;

import br.com.galobet.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Carteira findByUsuarioId(Long usuarioId);
}