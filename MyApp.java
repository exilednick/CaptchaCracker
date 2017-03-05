package captchaCracker;
/**
 * This has the main class
 * It is responsible for the complete execution
 */
public class MyApp {
	final static String path = "C:\\Users\\DELL\\Documents\\VIT\\02 - Winter Semester 2016-2017\\ECE1004\\Project\\test\\";
	final static String file = "image";
	public static void main(String args[]){
		for(int i=1;i<5;i++){
			String temp = file + Integer.toString(i) + ".jpg";
			GrayScaleConverter gScaleObj = new GrayScaleConverter(path+temp); //Convert into gray-scale
			ImageFilterer imageFilterer = new ImageFilterer(gScaleObj.getFileName()); //Filter 
			//CharacterRecognizer characterRecognizer = 
			new CharacterRecognizer(imageFilterer.getBinary(), path, i); //Extract characters
		}
	}
}
