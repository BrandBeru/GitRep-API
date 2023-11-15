/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 *
 * @author brand
 */
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "BrandBeru140206@secure.org";
    private static Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    
    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("BeruGit")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+TimeUnit.DAYS.toMillis(7)))
                .sign(algorithm);
    }
    public boolean isValid(String jwt){
        try{
            JWT.require(algorithm)
                    .build()
                    .verify(jwt);
            return true;
        }catch (JWTVerificationException ex){
            return false;
        }
    }
    public String getUsername(String jwt){
        return JWT.require(algorithm)
                .build()
                .verify(jwt)
                .getSubject();
    }
    
}
