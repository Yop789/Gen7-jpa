package com.lopez.app.jpa.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;




@Component
public class JwtUtil {
  
    @Autowired
	SecretKey key;
	
	
	public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
   }
   public Date extractExpiration(String token) {
       return extractClaim(token, Claims::getExpiration);
   }
   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
       final Claims claims = extractAllClaims(token);
       return claimsResolver.apply(claims);
   }
   private Claims extractAllClaims(String token) {
       //return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		//return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
   	 Jws<Claims> jws = Jwts.parser().verifyWith(key)
               
                .build()
                .parseSignedClaims(token);
        return jws.getPayload();
   }
   private Boolean isTokenExpired(String token) {
       return extractExpiration(token).before(new Date());
   }
   public String generateToken(String username, Map<String, Object> claims2) {
       Map<String, Object> claims = claims2;
       return createToken(claims, username);
   }
   private String createToken(Map<String, Object> claims, String subject) {
       return Jwts.builder()
       		.claims()
       			.issuedAt(new Date(System.currentTimeMillis()))
       			.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
       			.subject(subject)
       			.add(claims)
       			.and()
       		.signWith(key)
       		.compact();
   }
   public Boolean validateToken(String token, String username) {
       final String usernameFromToken = extractUsername(token);
       return (usernameFromToken.equals(username) && !isTokenExpired(token));
   }

}
