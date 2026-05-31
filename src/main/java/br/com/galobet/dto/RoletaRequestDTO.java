package br.com.galobet.dto;

import br.com.galobet.enums.TipoApostaEnum;
import lombok.Getter;

@Getter
public class RoletaRequestDTO {

    private Long usuarioId;

    private double valor;

    private TipoApostaEnum tipoAposta;

    private int numeroEscolhido;

    public RoletaRequestDTO() {
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipoAposta(TipoApostaEnum tipoAposta) {
        this.tipoAposta = tipoAposta;
    }

    public void setNumeroEscolhido(int numeroEscolhido) {
        this.numeroEscolhido = numeroEscolhido;
    }
}