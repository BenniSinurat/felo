package org.felo.api.auth;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class LoginCredentialHandler implements Callable {

	public Object onCall(MuleEventContext eventContext) throws Exception {
		try {
			String pass = (String) eventContext.getMessage().getInvocationProperty("password");
			String action = (String) eventContext.getMessage().getInvocationProperty("action");
			System.out.println("PASSWORD: "+pass.trim());
			
			String passDec = new SecurityHandler().Action(action, pass.trim());
			//System.out.println("[PASSWORD: "+pass+" / " + "ACTION: " + action + " / " + "DECRYPTED PASSWORD: " + passDec + "]");
			
			if(passDec.isEmpty()){
				String status = "PIN_ERROR";
				return status;
			}else{
				return passDec;
			}
			
		} catch (NullPointerException ex) {
			System.out.println(ex);
			String status = "PIN_ERROR";
			
			return status;
		} catch (IllegalBlockSizeException ex) {
			System.out.println("[IllegalBlockSizeException PIN Decrypt Failure ! : " + ex.getMessage() + " "
					+ (String) eventContext.getMessage().getInvocationProperty("pin") + "]");
			String status = "PIN_ERROR";

			return status;
		} catch (BadPaddingException ex) {
			System.out.println("[BadPaddingException PIN Decrypt Failure ! : " + ex.getMessage() + " "
					+ (String) eventContext.getMessage().getInvocationProperty("pin") + "]");
			String status = "PIN_ERROR";
			return status;
		}

	}
}