package br.com.galobet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenDTO {

    private String token;
    private String tipo;

    public TokenDTO() {
    }

    public TokenDTO(String token) {
        this.token = token;
        this.tipo = "Bearer";
    }

}
