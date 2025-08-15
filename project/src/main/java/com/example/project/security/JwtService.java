package com.example.project.security;

import com.example.project.model.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    //    اینارو بعد باید بزارم تو application.properties
    private final String SECRET = "uyQg7ZFEGn8lVpsfCzltNKAk3xJPnuFLaRcloGsfMsE7e84Q9MMPcOjDdkrTumYYhcS7Moig6oyZCFmJTI6VEoNyAlOpGkCSdV3Muyx9Hi9DmTVxIRcJpSIn32OZXstrcUz8qzOrJUvhTSHqR0Vlo4knva8CbMd4cqJUP2dK0Cof8mvb4HJHA0HlpdQ2eYWPeR3pyGMAYGhaxvoPKqxOFB74hG5fSWV0cnMSjvGo2f19GHhsCf6sCcpp9TmjoRQSDUhpVaNKCWYcXvwwZnR5Y94XoAnlYAHsOFwcCAwt6lnZe07aGpCO1hJPTm63yB5FQWAtvccTKjwPrSEPWAED8dLesZARsRsJuAX4wfv5Rydvq4mCnRvnFZVEGv5DIecumkH1A1E8wyEyyBH2oeJ91xPvUUzQvrJwFGIREvvkjpyWTPLVOtBqzKz4ECIcwTONg9iwC4IEZrfK9nzBg1KvI4TmMFnGVh7kNuOJHaGL0OwM2QH9IozoNlD4XBO9Tdhw";
    private final long EXPIRATION_TIME = 6000;

    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}