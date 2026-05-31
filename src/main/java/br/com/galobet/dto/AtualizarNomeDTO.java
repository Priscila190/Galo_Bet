package br.com.galobet.dto;

public class AtualizarNomeDTO {

    private String nome;

    public AtualizarNomeDTO() {
    }

    public AtualizarNomeDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
