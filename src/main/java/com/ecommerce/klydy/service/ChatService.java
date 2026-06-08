package com.ecommerce.klydy.service;

import com.ecommerce.klydy.config.ChatConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {
    @Autowired
    private ChatConfig chatConfig;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // — modelo estable sin razonamiento
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.1-flash-lite:generateContent?key=";

    private static final String BACKEND_PRODUCTOS_URL =
            "https://ecommerceklydy.onrender.com/productos";

    // ─── OBTENER CATÁLOGO ────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> obtenerProductos() {
        try {
            ResponseEntity<List> response = restTemplate.getForEntity(BACKEND_PRODUCTOS_URL, List.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> productos = (List<Map<String, Object>>) response.getBody();

                // ← AGREGA ESTE LOG TEMPORAL
                if (!productos.isEmpty()) {
                    System.out.println("PRIMER PRODUCTO: " + productos.get(0));
                }

                return productos;
            }
        } catch (Exception e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    // ─── ARMAR SYSTEM PROMPT ────────────────────────────────────────────────

    private String buildSystemPrompt(List<Map<String, Object>> productos) {
        StringBuilder catalogo = new StringBuilder();
        catalogo.append("CATÁLOGO ACTUAL (solo estos productos existen):\n");

        for (Map<String, Object> p : productos) {
            Object stock = p.get("stock");
            int stockInt = stock instanceof Number ? ((Number) stock).intValue() : 0;
            if (stockInt <= 0) continue;

            //  — precio formateado en COP
            Object precioObj = p.get("precio");
            long precio = precioObj instanceof Number ? ((Number) precioObj).longValue() : 0;
            String precioFormateado = String.format("%,.0f", (double) precio).replace(",", ".");

            // formato id=X igual al que Gemini interpreta correctamente
            catalogo.append("- id=").append(p.get("id"))
                    .append(" | ").append(p.get("nombre"))
                    .append(" | ").append(p.get("marca"))
                    .append(" | ").append(p.get("categoria"))
                    .append(" | uso=").append(p.get("uso"))
                    .append(" | $").append(precioFormateado)
                    .append(" | stock=").append(stockInt)
                    .append("\n");
        }

        return "Eres Klydy, asistente de compras de Klydy Tech, tienda colombiana de tecnología. " +
                "Eres amable, carismático y directo. Responde siempre en español.\n\n" +

                "REGLAS GENERALES:\n" +
                "- Recomienda solo productos del catálogo con stock > 0\n" +
                "- Filtra por presupuesto, uso y categoría según lo que pida el usuario\n" +
                "- Muestra los precios en formato colombiano (ej. $2.000.000)\n" +
                "- Máximo 2 emojis por respuesta\n" +
                "- NUNCA uses *, **, #, listas con guiones ni ningún formato markdown\n" +
                "- Escribe texto plano únicamente, como si fuera un mensaje de WhatsApp\n" +
                "- Respuestas máximo 5 frases\n" +
                "- Si ya hay historial previo, no te vuelvas a presentar\n" +
                "- No inventes productos, precios ni stock: usa SOLO el catálogo\n\n" +

                "PRESUPUESTO:\n" +
                "- Pregunta el presupuesto si el usuario no lo menciona\n" +
                "- Si menciona solo un número como 2000, interpreta como $2.000 exactos, no millones\n" +
                "- Suma precios al recomendar varios y avisa si se pasa del presupuesto\n\n" +

                "PERFILES DE USO:\n" +
                "- GAMER: TARJETAS_GRAFICAS, LAPTOPS, TECLADOS, MOUSES\n" +
                "- TRABAJO: LAPTOPS potentes, AUDIO, ACCESORIOS\n" +
                "- ESTUDIO: LAPTOPS económicas, MOUSES, TECLADOS, ACCESORIOS\n" +
                "- GENERAL: cualquier categoría, precio medio\n\n" +

                "AGREGAR AL CARRITO (MUY IMPORTANTE):\n" +
                "- NUNCA agregues un producto sin confirmación explícita del usuario\n" +
                "- Confirmaciones válidas: sí, dale, agrégalo, listo, confirmo\n" +
                "- Cuando el usuario confirme, responde ÚNICAMENTE con la(s) línea(s), sin texto adicional:\n" +
                "  [[CART_ADD:{\"id\":\"X\",\"qty\":1}]]\n" +
                "- Usa el id exacto del catálogo (el número después de id=)\n" +
                "- Si son varios productos, una línea por cada uno, nada más\n" +
                "- Para recomendar o conversar, NUNCA uses [[CART_ADD]]\n\n" +

                catalogo;
    }

    // ─── LLAMADA A GEMINI ────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    public String chat(List<Map<String, Object>> messages) {
        List<Map<String, Object>> productos = obtenerProductos();
        String systemPrompt = buildSystemPrompt(productos);

        // Limitar historial a últimos 10 mensajes
        List<Map<String, Object>> historial = messages;
        if (historial.size() > 10) {
            historial = historial.subList(historial.size() - 10, historial.size());
        }

        // Convertir historial al formato de Gemini
        List<Map<String, Object>> contents = new ArrayList<>();

        for (Map<String, Object> msg : historial) {
            String role = (String) msg.get("role");
            String text = (String) msg.get("content");

            String geminiRole = "assistant".equals(role) ? "model" : "user";

            Map<String, Object> turn = new HashMap<>();
            turn.put("role", geminiRole);
            turn.put("parts", List.of(Map.of("text", text != null ? text : "")));
            contents.add(turn);
        }

        // systemInstruction separado como lo hace Gemini correctamente
        Map<String, Object> systemInstruction = new HashMap<>();
        systemInstruction.put("parts", List.of(Map.of("text", systemPrompt)));

        // Configuración de generación
        Map<String, Object> generationConfig = new HashMap<>();
        generationConfig.put("maxOutputTokens", 512);
        generationConfig.put("temperature", 0.35);

        // Body de la petición
        Map<String, Object> body = new HashMap<>();
        body.put("systemInstruction", systemInstruction);
        body.put("contents", contents);
        body.put("generationConfig", generationConfig);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            String url = GEMINI_URL + chatConfig.getGeminiApiKey();
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                List<Map<String, Object>> candidates =
                        (List<Map<String, Object>>) responseBody.get("candidates");

                if (candidates != null && !candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> content = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> parts =
                            (List<Map<String, Object>>) content.get("parts");

                    if (parts != null && !parts.isEmpty()) {
                        return (String) parts.get(0).get("text");
                    }
                }
            }

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            if (e.getStatusCode().value() == 429) {
                return "En este momento tengo mucha demanda, intenta de nuevo en un momento 😊";
            }
            System.err.println("Error Gemini HTTP: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error llamando a Gemini: " + e.getMessage());
        }

        return "En este momento tengo mucha demanda, intenta de nuevo en un momento 😊";
    }
}
