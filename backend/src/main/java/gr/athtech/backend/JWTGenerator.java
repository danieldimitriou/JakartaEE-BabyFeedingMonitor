package gr.athtech.backend;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JWTGenerator {
    private static final Logger logger = (Logger) LogManager.getLogger(JWTGenerator.class);
    public static final String signingKey = "FKurEP2oFoMTF0euviqBWDTw2OffO9Qf8JAOcAaay4w=";
    private static final long EXPIRATION_TIME_MS = 3600000; // 1 hour

    public static String generateToken(String email, String role) {
        // Decode the signing key from Base64
        byte[] decodedKey = Base64.getDecoder().decode(signingKey);
        Key secretKey = Keys.hmacShaKeyFor(decodedKey);

        // Set the expiration time
        logger.info(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME_MS);

        // Build the JWT
        String token = Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        logger.error(token);
        return token;
    }

    public static void main(String[] args) {
        generateToken("example@example.com", "user");
    }
}
