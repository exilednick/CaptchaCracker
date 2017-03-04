package captchaCracker;
/**
 * This has the main class
 * It is responsible for the complete execution
 */
public class MyApp {
	final static String path = "C:\\Users\\DELL\\Documents\\VIT\\02 - Winter Semester 2016-2017\\ECE1004\\Project\\test\\";
	final static String file = "image.jpg";
	public static void main(String args[]){
		GrayScaleConverter gScaleObj = new GrayScaleConverter(path+file); //Convert into gray-scale
		ColorExtractor colorExtracter = new ColorExtractor(gScaleObj.getFileName()); //Extract colors
		colorExtracter.removeNoise();
	}
}
