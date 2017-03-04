package captchaCracker;

import java.awt.Color;

/**
 * Pixel data type
 */
public class Pixel {
	//integer array to store RGB Pixel information
	private int[] pixels;
	/**
	 * Initialize pixel with a Color
	 * @param c
	 */
	public Pixel(Color c){
		pixels = new int[3];
		pixels[0] = c.getRed();
		pixels[1] = c.getBlue();
		pixels[2] = c.getGreen();
	}
	/**
	 * Override equals function
	 */
	@Override
	public boolean equals(Object obj){
		//Check Object type
		if (!(obj instanceof Pixel))
            return false;
        if (obj == this)
            return true;
        
        Pixel rhs = (Pixel) obj;
        if(pixels[0]==rhs.pixels[0]&&pixels[1]==rhs.pixels[1]&&pixels[2]==rhs.pixels[2])
        	return true;
        return false;

	}
	@Override
	public int hashCode(){
		return Integer.toString(pixels[0]).hashCode()
				+ Integer.toString(pixels[1]).hashCode()
				+ Integer.toString(pixels[2]).hashCode();
	}
	/**
	 * Print method
	 */
	public void print(){
		System.out.println("("+pixels[0]+","+pixels[1]+","+pixels[2]+")");
	}
}
