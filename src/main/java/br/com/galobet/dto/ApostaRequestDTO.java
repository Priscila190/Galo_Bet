package br.com.galobet.dto;

import br.com.galobet.enums.TipoApostaEnum;

public class ApostaRequestDTO {

    private Long usuarioId;

    private double valor;

    private TipoApostaEnum tipoAposta;
    private int numeroEscolhido;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoApostaEnum getTipoAposta() {
        return tipoAposta;
    }

    public void setTipoAposta(TipoApostaEnum tipoAposta) {
        this.tipoAposta = tipoAposta;
    }

    public boolean getNumeroEscolhido() {
        boolean getNumeroEscolhido = false;
        return getNumeroEscolhido;
    }
    public void setNumeroEscolhido(int numeroEscolhido) {
        this.numeroEscolhido = numeroEscolhido;
    }
}