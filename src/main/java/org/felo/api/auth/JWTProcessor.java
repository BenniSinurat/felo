package org.felo.api.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JWTProcessor {
	private static Logger logger = Logger.getLogger(JWTProcessor.class);

	public static String createJWTHMAC256(String username, String secret) {
		try {
			String token = JWT.create().withIssuer("Felo").withSubject("auth").withJWTId(username)
					.sign(Algorithm.HMAC256(secret));
			return token;
		} catch (Exception exception) {
			exception.printStackTrace();
			return "0";
		}
	}

	public static String verifyJWTHMAC256(String token, String secret) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer("Felo").build();
			DecodedJWT jwt = verifier.verify(token);
			logger.info("Verify DecodeJWT = " + jwt.toString());
			
			return jwt.getId();
		} catch (Exception e) {
			e.printStackTrace();
			
			return "false";
		}
	}

	public static String decodeJWTHMAC256(String token) throws Exception {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getId();
	}

}
