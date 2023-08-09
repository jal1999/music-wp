package com.music.app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.music.app.SecretConstants;

import java.util.Date;

public class JwtUtil {
    public JwtUtil() {}
    public String generateToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecretConstants.SECRET);
            return JWT.create()
                    .withExpiresAt(new Date().toInstant().plusMillis(1000 * 60 /* one hour */))
                    .withIssuer("music")
                    .sign(algorithm);
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgumentException in JwtUtil.java - HMAC256");
        }
        return "";
    }

    public boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecretConstants.SECRET);
        JWTVerifier verifier = JWT
                .require(algorithm)
                .withIssuer("music")
                .build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }
}
