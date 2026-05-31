package br.com.galobet.dto;

import br.com.galobet.enums.TipoJogo;

public class JogoDTO {

    private Long id;
    private String nome;
    private TipoJogo tipoJogo;
    private boolean ativo;

    public JogoDTO() {
    }

    public JogoDTO(Long id, String nome, TipoJogo tipoJogo, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tipoJogo = tipoJogo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoJogo getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(TipoJogo tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}