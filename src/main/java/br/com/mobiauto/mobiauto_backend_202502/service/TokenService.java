package br.com.mobiauto.mobiauto_backend_202502.service;

import br.com.mobiauto.mobiauto_backend_202502.domain.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class TokenService {

    @Value("${jjwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication){

        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        return Jwts.builder()
                .claim(Claims.SUBJECT, usuario.getId())
                .signWith(key)
                .compact();

    }

    public boolean isTokenValido(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        try{
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long extrairId(String token){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }
}
