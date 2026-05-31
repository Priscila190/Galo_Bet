package br.com.galobet.dto;

public class ApostaResponseDTO {

    private boolean ganhou;

    private int numero;

    private double saldoAtual;

    private double premio;

    private String mensagem;

    public ApostaResponseDTO(
            boolean ganhou,
            int numero,
            double saldoAtual,
            double premio,
            String mensagem
    ) {
        this.ganhou = ganhou;
        this.numero = numero;
        this.saldoAtual = saldoAtual;
        this.premio = premio;
        this.mensagem = mensagem;
    }

    public boolean isGanhou() {
        return ganhou;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public double getPremio() {
        return premio;
    }

    public String getMensagem() {
        return mensagem;
    }
}