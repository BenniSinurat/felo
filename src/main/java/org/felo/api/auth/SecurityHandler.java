package org.felo.api.auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

public class SecurityHandler {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private final Cipher cipher;
	private final KeyFactory factory;
	private final PrivateKey priv;
	private final PublicKey pub;

	public SecurityHandler() throws Exception {
		this.factory = KeyFactory.getInstance("RSA", "BC");
		//this.priv = factory.generatePrivate(new PKCS8EncodedKeySpec(getPem("/opt/mule-3.8.1-agent/apps/felo/classes/private.pem").getContent()));
		//this.pub = factory.generatePublic(new X509EncodedKeySpec(getPem("/opt/mule-3.8.1-agent/apps/felo/classes/public.pem").getContent()));
		this.priv = factory.generatePrivate(new PKCS8EncodedKeySpec(getPem("private.pem").getContent()));
		this.pub = factory.generatePublic(new X509EncodedKeySpec(getPem("public.pem").getContent()));
		this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
	}

	private static PemObject getPem(String filename) throws IOException {
		PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
		return pemReader.readPemObject();
	}

	public String Action(String action, String text) throws Exception {
		String result = "";
		try {
			if (action.equalsIgnoreCase("ENCRYPT")) {
				result = encryptPassword(text);
			} else if (action.equalsIgnoreCase("DECRYPT")) {
				result = decryptPassword(text);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public String decryptPassword(String base64) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, this.priv);
		return new String(cipher.doFinal(Base64.getDecoder().decode(base64)));
	}

	public String encryptPassword(String message) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, pub);
		return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
	}

}
