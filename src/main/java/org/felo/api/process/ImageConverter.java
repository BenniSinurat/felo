package org.felo.api.process;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class ImageConverter implements Callable {
	public Object onCall(MuleEventContext eventContext) throws IOException {
		HashMap payload = (HashMap) eventContext.getMessage().getPayload();

		File serverFile = new File((String) payload.get("fileOri"));
		File blurFile = new File((String) payload.get("fileBlur"));

		BufferedImage originalImage = ImageIO.read(serverFile);
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		System.out.println("ORI: "+serverFile+" BLUR: "+blurFile+" TYPE: "+type);
		BufferedImage resizedImage = new BufferedImage(500, 500, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 500, 500, null);
		g.dispose();
		ImageIO.write(resizedImage, "png", blurFile);

		return null;
	}
}
