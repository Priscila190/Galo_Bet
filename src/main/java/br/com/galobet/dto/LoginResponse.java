package br.com.galobet.dto;

public class LoginResponse {

    private String token;
    private Long usuarioId;
    private String nome;
    private String email;
    private double saldo;

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(
            String token,
            Long usuarioId,
            String nome,
            String email,
            double saldo
    ) {
        this.token = token;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.saldo = saldo;
    }

    public String getToken() {
        return token;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldo() {
        return saldo;
    }
}
