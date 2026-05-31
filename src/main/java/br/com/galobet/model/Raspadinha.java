package br.com.galobet.model;

import br.com.galobet.enums.SimboloRaspadinha;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Raspadinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private double valor;

    @Enumerated(EnumType.STRING)
    private SimboloRaspadinha pos1;

    @Enumerated(EnumType.STRING)
    private SimboloRaspadinha pos2;

    @Enumerated(EnumType.STRING)
    private SimboloRaspadinha pos3;

    private boolean ganhou;

    private double premio;

    private LocalDateTime data = LocalDateTime.now();

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setPos1(SimboloRaspadinha simboloRaspadinha) {
        this.pos1 = simboloRaspadinha;
    }

    public void setPos2(SimboloRaspadinha simboloRaspadinha) {
        this.pos2 = simboloRaspadinha;
    }

    public void setPos3(SimboloRaspadinha simboloRaspadinha) {
        this.pos3 = simboloRaspadinha;
    }

    public void setGanhou(boolean ganhou) {
        this.ganhou = ganhou;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }

    // getters e setters
    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public double getValor() {
        return valor;
    }

    public SimboloRaspadinha getPos1() {
        return pos1;
    }

    public SimboloRaspadinha getPos2() {
        return pos2;
    }

    public SimboloRaspadinha getPos3() {
        return pos3;
    }

    public boolean isGanhou() {
        return ganhou;
    }

    public double getPremio() {
        return premio;
    }

    public LocalDateTime getData() {
        return data;
    }
}
