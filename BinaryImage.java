package captchaCracker;
import java.awt.Color;
import java.awt.image.BufferedImage;

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
}
