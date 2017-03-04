package captchaCracker;
/**
 * This class extracts the background and text color(RBG) for a captcha
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ColorExtractor {
	private BufferedImage image; 
	private int width, height;
	private	PixelDictionary dict;
	int[] temp;
	public ColorExtractor(String file){
		try{
			File input = new File(file);
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			dict = new PixelDictionary();
			for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					Color c = new Color(image.getRGB(j, i));
					Pixel p = new Pixel(c);
					dict.putData(p);
				}
			}
			dict.print();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}