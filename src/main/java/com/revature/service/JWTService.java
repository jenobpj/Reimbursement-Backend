package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {

    private static JWTService instance;

    private Key key;

    {
        byte[] secret = "my_secret_password_asd45458jfgfdgdfgdfg4345".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }


    public String createJWT(User user){

        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId",user.getId())
                .claim("userName",user.getFirstName())
                .claim("userRole",user.getUserRole())
                .signWith(key)
                .compact();
        return jwt;

    }

    public Jws<Claims> validateJwt(String jwt){
        try{
            Jws<Claims> token= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            return  token;

//            String username=token.getBody().getSubject();
//            Integer userId=token.getBody().get("userId",Integer.class);
//            String userRole=token.getBody().get("userRole",String.class);
//            String userName=token.getBody().get("userName",String.class);
        }catch(JwtException e){

            e.printStackTrace();//will help for debugging
            throw new UnauthorizedResponse("JWT was invalid");
        }

    }
}
