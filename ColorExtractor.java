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
	String file, f_out;
	public ColorExtractor(String file){
		try{
			this.file = file;
			File input = new File(file);
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			dict = new PixelDictionary();
			for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					Pixel p = new Pixel(new Color(image.getRGB(j, i)));
					dict.putData(p);
				}
			}
			dict.print();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String removeNoise(){
		/**
		 * Brief of the method and some assumptions:
		 * Black is in the range 0 to 50
		 * White is in the range 235 to 255
		 * Rest is noise which will be converted into white
		 */
		try{
			for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					Pixel p = new Pixel(new Color(image.getRGB(j, i)));
					if(p.getValue()>-1&&p.getValue()<51){ //black
						p.setValue(0);
					}else{ //noise and background
						p.setValue(255);
					}
					image.setRGB(j, i, p.getColor().getRGB());
				}
			}
			f_out = file.substring(0, file.indexOf(".")) + "_filtered.jpg";
			File output = new File(f_out);
			ImageIO.write(image, "jpg", output);
		}catch(Exception e){
			e.printStackTrace();
		}
		return f_out;
	}
}