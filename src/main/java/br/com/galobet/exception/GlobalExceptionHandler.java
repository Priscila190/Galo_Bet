package br.com.galobet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex
    ) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("data", LocalDateTime.now());
        erro.put("erro", ex.getMessage());
        erro.put("status", 400);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception ex
    ) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("data", LocalDateTime.now());
        erro.put("erro", "Erro interno no servidor");
        erro.put("status", 500);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, Object>> handleRegraNegocio(
            RegraNegocioException ex
    ) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("data", LocalDateTime.now());
        erro.put("erro", ex.getMessage());
        erro.put("status", 400);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }
}

// capturar erros,evitar stacktrace gigante no frontend,retornar JSON bonito,padronizar respostas da API