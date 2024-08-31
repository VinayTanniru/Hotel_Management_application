package utils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Base64;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	private static final long EXPIRATION_TIME = 1000 * 60 * 24 * 7;

	private final SecretKey key;

	public JWTUtils() {
		String secretString = "test";
		byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
		this.key = new SecretKeySpec(keyBytes, "HmaCsha256");
	}

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key).compact();
	}


	public String extractUsername(String token) {
	    return extractClaims(token, Claims::getSubject);
	}

	
	private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
	    Claims claims = Jwts.parser()
	    		.verifyWith(key)
	    		.build()
	    		.parseSignedClaims(token)
	    		.getPayload();
	            
	    
	    return claimsTFunction.apply(claims);
	}

	public boolean isValidToken(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	 private boolean isTokenExpired(String token) {
	        Date expirationDate = extractClaims(token, Claims::getExpiration);
	        return expirationDate.before(new Date());
	    }

}
