package gr.athtech.backend;

import gr.athtech.backend.service.serviceImpl.FeedingSessionServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.security.Key;
import java.util.Date;

public class JWTGenerator {
    private static final Logger logger = (Logger) LogManager.getLogger(JWTGenerator.class);
    public static final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour

    public static String generateToken(String email, String role) {
        // Set the expiration time
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME_MS);

        // Build the JWT
        String token = Jwts.builder()
                .setSubject(email)
                .claim("role", role) // Set the role as a claim
                .setExpiration(expiration)
                .setIssuedAt(now)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
        logger.error(token);
        return token;
    }
}
