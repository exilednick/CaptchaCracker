package captchaCracker;
/**
 * This class converts an image into gray-scale
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class GrayScaleConverter {
	private BufferedImage image;
	private int width, height;
	private String f_out;
	public GrayScaleConverter(String file){
		try{
			File input = new File(file);
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			for(int i=0;i<height;i++){
				for(int j=0;j<width; j++){
					Color c = new Color(image.getRGB(j, i));
					int red = (int)(c.getRed()*0.299);
					int green = (int)(c.getGreen()*0.587);
					int blue = (int)(c.getBlue()*0.114);
					int temp = red+green+blue;
					Color newColor = new Color(temp, temp, temp);
					image.setRGB(j, i, newColor.getRGB());
				}
			}
			f_out = file.substring(0, file.indexOf(".")) + "_gray.jpg";
			File output = new File(f_out);
			ImageIO.write(image, "jpg", output);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	public String getFileName(){
		return f_out;
	}
}