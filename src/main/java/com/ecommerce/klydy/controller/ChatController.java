package com.ecommerce.klydy.controller;

import com.ecommerce.klydy.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, Object> body) {
        try {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> messages = (List<Map<String, Object>>) body.get("messages");

            if (messages == null || messages.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("reply", "No se recibieron mensajes."));
            }

            String reply = chatService.chat(messages);
            return ResponseEntity.ok(Map.of("reply", reply));

        } catch (Exception e) {
            System.err.println("Error en /api/chat: " + e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(Map.of("reply", "En este momento tengo mucha demanda, intenta de nuevo en un momento 😊"));
        }
    }
}
