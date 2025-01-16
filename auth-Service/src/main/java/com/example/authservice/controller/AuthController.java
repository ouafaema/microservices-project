package com.example.authservice.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody Map<String, Object> payload) {
        if (payload == null || !payload.containsKey("username")) {
            return ResponseEntity.badRequest().body("Le champ 'username' est obligatoire.");
        }

        String username = (String) payload.get("username");

        // Générer le token JWT
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expire dans 1 heure
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // Utilisation de la clé partagée
                .compact();

        return ResponseEntity.ok(token);
    }
}
