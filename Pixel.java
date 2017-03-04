package captchaCracker;

import java.awt.Color;

/**
 * Pixel data type for gray scale
 */
public class Pixel {
	//integer array to store Pixel information
	private int value;
	/**
	 * Initialize pixel with a brightness value
	 * @param c
	 */
	public Pixel(Color c){
		value = c.getRed();
	}
	/**
	 * GETTER
	 * @return value
	 */
	public int getValue(){
		return value;
	}
	/**
	 * SETTER
	 * @param value
	 */
	public void setValue(int value){
		this.value = value;
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
        if(value==rhs.value)
        	return true;
        return false;

	}
	@Override
	public int hashCode(){
		return Integer.toString(value).hashCode();
	}
	/**
	 * converts into Color for writing
	 * @return c
	 */
	public Color getColor(){
		Color c = new Color(value, value, value);
		return c;
	}
	/**
	 * Print method
	 */
	public void print(){
		System.out.println("("+value+")");
	}
}
