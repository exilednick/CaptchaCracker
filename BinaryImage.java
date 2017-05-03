package app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * BinaryImage data type
 * Each pixel is either ON(white) or OFF(black)
 * @author Shubham Sharma
 */
public class BinaryImage {
	private int width, height;
	private int[][] data;
	public final static int BINARY_ON = 1; //White
	public final static int BINARY_OFF = 0; //Black
	/**
	 * Initialize BinaryImage
	 * @param w width
	 * @param h height
	 */
	public BinaryImage(int w, int h){
		width = w;
		height = h;
		data = new int[width][height];
		//Default pixels
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				data[i][j] = BINARY_OFF;
	}
	/**
	 * Sets the value for a particular pixel
	 * @param w width index
	 * @param h height index
	 * @param state BINARY_OFF or BINARY_ON
	 */
	public void setData(int w, int h, int state){
		data[w][h] = state;
	}
	/**
	 * Returns state of the image at any pixel
	 * @param w width index
	 * @param h height index
	 * @return state
	 */
	public int getData(int w, int h){
		return data[w][h];
	}
	/**
	 * Get width of image
	 * @return width
	 */
	public int getWidth(){
		return width;
	}
	/**
	 * Get height of image
	 * @return height
	 */
	public int getHeight(){
		return height;
	}
	/**
	 * Gets the sub image of an image
	 */
	public BinaryImage subImage(int x1, int y1, int x2, int y2){
		BinaryImage b = new BinaryImage(x2-x1, y2-y1);
		int x, y = 0;
		for(int i=y1; i<y2; i++){
			x = 0;
			for(int j=x1;j<x2; j++){
				b.setData(x, y, this.getData(j, i));
				x++;
			}
			y++;
		}
		return b;
	}
	/**
	 * Converts BufferedImage into BinaryImage
	 * @param im BufferedImage object
	 */
	public void setImage(BufferedImage im){
		int w = im.getWidth();
		int h = im.getHeight();
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				Color c = new Color(im.getRGB(j,i));
				//Sets pixel to ON or OFF state
				if(c.getRed()==0){
					data[j][i] = BINARY_OFF;
				}else{
					data[j][i] = BINARY_ON;
				}
			}
		}
	}
	/**
	 * Saves the current object as a jpg file
	 * @param file filename
	 */
	public void save(String file){
		try{
			//Create BufferedImage instance
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for(int i=0;i<height;i++)
				for(int j=0;j<width;j++){
					int temp = 0; 
					if(data[j][i]==BINARY_ON)
						temp = 255; //white
					else
						temp = 0;   //black
					Color new_c = new Color(temp, temp, temp);
					bi.setRGB(j, i, new_c.getRGB());
				}
			String out = file + ".jpg";
			File output = new File(out);
			ImageIO.write(bi, "jpg", output);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
