package br.com.galobet.model;

import br.com.galobet.enums.CorRoleta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Roleta {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private int numeroSorteado;

    @Setter
    @Getter
    private double valorAposta;

    @Getter
    private CorRoleta cor;

    private final LocalDateTime data = LocalDateTime.now();

    public Roleta(int numero, CorRoleta cor, boolean ganhou) {

    }

    @Setter
    @Getter
    private double premio;

    @Setter
    @Getter
    private boolean venceu;

    @Setter
    @Getter
    @ManyToOne
    private Usuario usuario;

    public Roleta() {
    }

}