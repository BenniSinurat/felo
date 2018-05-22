package org.felo.api.auth;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class HashValidation implements Callable {

	private Logger logger = Logger.getLogger(HashValidation.class);

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		String hashContent = (String) eventContext.getMessage().getInvocationProperty("requestAuthContent");
		String secret = "58244b23e65c1fe8935a19f737c8ad50";
		
		logger.info("[RequestAuth : secret=" + secret + hashContent + "]");

		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex("secret=" + secret + hashContent);
		logger.info("[Hash Calculation : " + sha256hex);
		
		return sha256hex;
	}
	
	public void hash (String secret) {
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex("secret=58244b23e65c1fe8935a19f737c8ad50/msisdn=081310626462/token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXRob3JpemF0aW9uIiwiaXNzIjoiSlBBIiwiZXhwIjoxNTE5NDY5NzQxLCJqdGkiOiIxMTEyMDA0In0.rsCrU_JSBb4P5yzYVF5aJeaXl7CudSXK3USzUMACJR4");
		
		System.out.println("SHA256hex= "+sha256hex);
	}

}
