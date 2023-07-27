package br.com.doctorplus.gerenciador.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtProvider {

    @Value("${security.doctor-plus.auth.jwtSecret}")
    private String jwtSecret;

    @Value("${security.doctor-plus.auth.jwtExpiration}")
    private Long jwtExpiration;

    @Value("${security.doctor-plus.auth.jwtExpirationEmail}")
    private Long jwtExpirationEmail;


    public String geneareteJwt(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return getToken(userDetails);
    }

    public String criarJwtEmail(String email, Long id) {
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationEmail))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private String getToken(UserDetailsImpl userDetails) {
        List<String> roles = Objects.nonNull(userDetails.getAuthorities()) ? userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()) : new ArrayList<>();
        return Jwts.builder()
                .setSubject(userDetails.getId().toString())
                .claim("email", userDetails.getEmail())
                .claim("nome", userDetails.getNome())
                .claim("idOrganizacao", userDetails.getIdOrganizacao())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getSubjectJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserName(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("email"));
    }
    public boolean isValidateJwt(String authToken) {
        try {
            if (Strings.isNotBlank(authToken)) {
                Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
                return true;
            }
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}