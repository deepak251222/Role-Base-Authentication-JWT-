package com.spring.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "deepak_kumar";
	
	private static final int TOKEN_VALIDITY =3600 *5;

	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {

		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

	}
	public Boolean validateToken(String token,UserDetails userDetails)
	{
		String userName=getUserNameFromToken(token); 
		return (userName.equals(userDetails.getUsername()) && !isTokenExpire(token));
	}
	private Boolean isTokenExpire(String token){
		   
		final Date expiringDate =getExpirationFromToken(token);
		return expiringDate.before(new Date()); 
		
	}
	private Date getExpirationFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	} 
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims= new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
}
