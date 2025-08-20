package com.literalura.service;

import com.literalura.dto.GutendexResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GutendexClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public GutendexResponse buscarPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + java.net.URLEncoder.encode(titulo, java.nio.charset.StandardCharsets.UTF_8);
        ResponseEntity<GutendexResponse> resp = restTemplate.getForEntity(url, GutendexResponse.class);
        return resp.getBody();
    }
}
