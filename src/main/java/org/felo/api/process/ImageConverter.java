package org.felo.api.process;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImageConverter {
	public static String extractBytes(String ImageName) throws IOException {
		String base64Image = "";

		// open image
		//File imgPath = new File(ImageName);
		// Reading a Image file from file system
		// byte imageData[] = new byte[(int) imgPath.length()];
		// imageInFile.read(imageData);
		//BufferedImage bufferedImage = ImageIO.read(imgPath);
		base64Image = Base64.getEncoder().encodeToString(ImageName.getBytes(StandardCharsets.UTF_8));

		System.out.println("IMAGE ENCODE: " + base64Image);

		return base64Image;
	}
	
	public static String decodeBytes(String ImageName) throws IOException {
		byte []	base64Image = Base64.getDecoder().decode(ImageName);
		
		System.out.println("IMAGE DECODE: "+base64Image.toString());
		
		return base64Image.toString();
	}

	
	/*public static String extractBytes(String ImageName) throws IOException {
	 // open image 
	File imgPath = new File(ImageName); 
	BufferedImage bufferedImage = ImageIO.read(imgPath);
	 
	 // get DataBufferBytes from Raster 
	WritableRaster raster = bufferedImage.getRaster(); 
	DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
	  
	  return (data.getData().toString()); 
	 }*/
	 
}
