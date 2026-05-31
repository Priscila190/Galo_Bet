package br.com.galobet.repository;

import br.com.galobet.model.Premio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PremioRepository extends JpaRepository<Premio, Long> {
}