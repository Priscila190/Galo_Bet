package br.com.galobet.repository;


import br.com.galobet.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

}