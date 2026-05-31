package br.com.galobet.dto;

import br.com.galobet.enums.TipoApostaEnum;
import lombok.Getter;

@Getter
public class ApostaDTO {

    private Long usuarioId;

    private double valor;

    private TipoApostaEnum tipoAposta;

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipoAposta(TipoApostaEnum tipoAposta) {
        this.tipoAposta = tipoAposta;
    }
}