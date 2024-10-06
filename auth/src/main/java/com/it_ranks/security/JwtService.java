package com.it_ranks.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import com.it_ranks.entity.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String key = "E3i9Wp+Rwj5zk64k7IlKqh1p1OVMyr1X3kvBqYxthu0=";
    private final int EXPIRATION_DATE= 30*60*1000;

    public String generateToken( User user){
        Map<String,Object> claims = new HashMap<> ();
        System.out.println ("user role" +user.getRole () );
        claims.put ("role",user.getRole());
        return createToken(claims,user.getUsername ());
    }
    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public String createToken (Map<String, Object> claims, String userName) {
        return Jwts.builder ()
                .setClaims (claims)
                .setSubject (userName)
                .setIssuedAt (new Date (System.currentTimeMillis ()))
                .setExpiration (new Date (System.currentTimeMillis ()+EXPIRATION_DATE))
                .signWith (getSignInKey(), SignatureAlgorithm.HS256).compact ();
    }

    private Key getSignInKey ( ) {
        byte[] keyBytes = Decoders.BASE64.decode (key );
        return Keys.hmacShaKeyFor (keyBytes);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims (String token) {
        return Jwts.parserBuilder ().setSigningKey (getSignInKey ())
                .build ().parseClaimsJws (token).getBody ();
    }

    private Date extractExpirationDate(String token){
        return extractClaims (token,Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token){
        return extractExpirationDate(token).before (new Date ());
    }
    public Boolean isValidToken(String token , UserDetails userDetails){
        String userName = extractUserName (token);
        return userName.equals (userDetails.getUsername ())&& !isTokenExpired (token);
    }
}
