package app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Makes a gray scale equivalent of an image
 * Uses the luminosity algorithm
 * @author Shubham Sharma
 */
public class Grayscale {
	private BufferedImage image;
	private BinaryImage bi;
	private int[] dim; //dimension of image
	/**
	 * Converts the image into grayscale BinaryImage
	 * 0 to 50 -> Black
	 * 200-255 -> White
	 * @param filename
	 * @return BinaryImage object of image
	 */
	public BinaryImage convert(String filename){
		try{
			//read input file
			File input = new File(filename);
			image = ImageIO.read(input);
			dim = new int[2];
			dim[0] = image.getWidth(); //width
			dim[1] = image.getHeight(); //height
			//Iterate in pixels and change the RGB value
			for(int i=0;i<dim[1];i++)
				for(int j=0;j<dim[0];j++){
					//Set new value for each pixel
					Color c = new Color(image.getRGB(j, i));
					int new_r = (int)(c.getRed()*0.299);
					int new_g = (int)(c.getGreen()*0.587);
					int new_b = (int)(c.getBlue()*0.114);
					int temp = (new_r + new_g + new_b)/3;
					//Adjust
					if(temp<=50)
						temp=0;
					else if(temp>=200)
						temp = 255;
					Color new_c = new Color(temp, temp, temp);
					image.setRGB(j, i, new_c.getRGB());
				}
			//Create BinaryImage object
			bi = new BinaryImage(dim[0], dim[1]);
			bi.setImage(image);
			bi.save(filename.substring(0, filename.indexOf(".")) + "_gray");
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return bi;
	}
}
