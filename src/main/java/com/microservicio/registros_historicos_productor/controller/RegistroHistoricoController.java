package com.microservicio.registros_historicos_productor.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.microservicio.registros_historicos_productor.model.SignosVitales;
import com.microservicio.registros_historicos_productor.service.RabbitMQProductorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registro-historico")
public class RegistroHistoricoController {
    private final RabbitMQProductorService rabbitMQService;

    public RegistroHistoricoController(RabbitMQProductorService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarAlerta(@RequestBody @Valid SignosVitales senales) {
        rabbitMQService.enviarAlertaCritica(senales);
        return ResponseEntity.ok("Registro histórico enviado: " + senales);
    }
    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "/api/registro-historico => funcionando correctamente");

        return ResponseEntity.ok(response);
    }
}