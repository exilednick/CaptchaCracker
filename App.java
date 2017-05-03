package app;
/**
 * Application class with main function
 * @author Shubham Sharma
 */
public class App {
	public final static String PATH = "C:\\Users\\DELL\\Documents\\VIT\\02 - Winter Semester 2016-2017\\ECE1004\\Project\\test\\";
	private final static String FILE = "image";
	private final static int COUNT = 1;
	public static void main(String[] args){
		for(int i=1;i<=COUNT;i++){
			Grayscale gs = new Grayscale();
			BinaryImage b = gs.convert(PATH + FILE + i + ".jpg");
			FilterClass fc = new FilterClass(b);
			b = fc.removeNoise();
			b.save(PATH + FILE + i + "_filtered");
			CharacterExtractor c = new CharacterExtractor(b, PATH, i);
			c.extractLetters();
		}
	}
}