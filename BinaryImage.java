package captchaCracker;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Binary Image
 */
public class BinaryImage {
	private int width, height;
	private int[][] data;
	/**
	 * Initialize BinaryImage
	 * @param w
	 * @param h
	 */
	public BinaryImage(int w, int h){
		width = w;
		height = h;
		data = new int[width][height]; 
	}
	/**
	 * Set data in it.
	 * 1 represents black
	 * 0 represents white
	 * @param im
	 */
	public void read(BufferedImage im){
		int width = im.getWidth();
		int height = im.getHeight();
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				Color c = new Color(im.getRGB(j, i));
				if(c.getRed()==0){
					data[j][i] = 1;
				}else{
					data[j][i] = 0;
				}
			}
		}
	}
	/**
	 * returns BufferedImage from binary
	 * @param bi
	 * @return
	 */
	public BufferedImage write(BufferedImage bi){
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				int temp;
				if(data[j][i]==0)
					temp = 255;
				else
					temp = 0;
				bi.setRGB(j, i, new Color(temp, temp, temp).getRGB());
			}
		}
		return bi;
	}
	/**
	 * Returns binary value at a point
	 * @param w
	 * @param h
	 * @return
	 */
	public int getData(int w, int h){
		return data[w][h];
	}
	/**
	 * Returns dimension of image
	 * @return
	 */
	public int[] getDimension(){
		int[] dim = {width, height};
		return dim;
	}
	/**
	 * Returns a cropped image of the original
	 * @param x1 upper left X
	 * @param y1 upper left Y
	 * @param x2 lower right X
	 * @param y2 lower right Y
	 * @return
	 */
	public BinaryImage getSubImage(int x1, int y1, int x2, int y2){
		BinaryImage cropped = new BinaryImage(x2-x1, y2-y1);
		int x = 0, y = 0;
		for(int i=x1;i<x2;i++){
			y=0;
			for(int j=y1;j<y2;j++){
				cropped.data[x][y] = data[i][j];
				y++;
			}
			x++;
		}
		return cropped;
	}
	/**
	 * Prints the image in a file
	 * @param file filename
	 */
	public void save(String file){
		try{
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			bi = this.write(bi);
			String f_out = file + ".jpg";
			File output = new File(f_out);
			ImageIO.write(bi, "jpg", output);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
