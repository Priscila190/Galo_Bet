package br.com.galobet.model;

import br.com.galobet.enums.CorRoleta;
import br.com.galobet.enums.TipoApostaEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private double valor;

    private boolean ganhou;

    private double premio;

    private int numeroResultado;

    @Enumerated(EnumType.STRING)
    private CorRoleta corResultado;

    private String escolha;

    private LocalDateTime dataAposta;

    @Enumerated(EnumType.STRING)
    private TipoApostaEnum tipoAposta;

    public Aposta() {
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioId = usuario != null ? usuario.getId() : null;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isGanhou() {
        return ganhou;
    }

    public void setGanhou(boolean ganhou) {
        this.ganhou = ganhou;
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }

    public int getNumeroResultado() {
        return numeroResultado;
    }

    public void setNumeroResultado(int numeroResultado) {
        this.numeroResultado = numeroResultado;
    }

    public CorRoleta getCorResultado() {
        return corResultado;
    }

    public void setCorResultado(CorRoleta cor) {
        this.corResultado = cor;
    }

    public String getEscolha() {
        return escolha;
    }

    public void setEscolha(String escolha) {
        this.escolha = escolha;
    }

    public LocalDateTime getDataAposta() {
        return dataAposta;
    }

    public void setDataAposta(LocalDateTime dataAposta) {
        this.dataAposta = dataAposta;
    }

    public TipoApostaEnum getTipoAposta() {
        return tipoAposta;
    }

    public void setTipoAposta(TipoApostaEnum tipoAposta) {
        this.tipoAposta = tipoAposta;
    }
}
