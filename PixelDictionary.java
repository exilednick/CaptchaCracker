package captchaCracker;
import java.util.HashMap;
import java.util.Map;
/**
 * Pixel dictionary data type
 */
public class PixelDictionary extends HashMap<Pixel, Integer>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor
	 */
	public PixelDictionary(){
		super();
	}
	/**
	 * Custom put method
	 * @param key
	 */
	public void putData(Pixel key){
		if(this.containsKey(key)){
			this.put(key, this.get(key)+1);
		}else
			this.put(key, 1);
	}
	/**
	 * Custom print method
	 */
	public void print(){
		for(Map.Entry<Pixel, Integer> obj:this.entrySet()){
			obj.getKey().print();
			System.out.println(obj.getValue());
		}
	}
}
