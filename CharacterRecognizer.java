package captchaCracker;
/**
 * This class recognizes characters from a filtered captcha
 */
public class CharacterRecognizer {
	private BinaryImage binaryImage;
	private int width, height, index;
	private String path;
	public CharacterRecognizer(BinaryImage bi, String path, int index){
		binaryImage = bi;
		this.path = path;
		this.index = index;
		int temp[] = bi.getDimension(); 
		width = temp[0];
		height = temp[1];
		extractLetters();
	}
	/*
	 * Extracts characters from the captcha
	 */
	private void extractLetters(){
		int count = 0, pos = 0;
		BinaryImage[] letters = new BinaryImage[10];
		int j = 0;
		while(countPixelsVertical(binaryImage, j)<=2){
			j++;
		}
		pos = j; //First character starts
		while(j<width){
			while(countPixelsVertical(binaryImage, j)>0){
				j++;
			}
			if(j-pos<=18&&j-pos>5){
				//proper
				letters[count++] = binaryImage.getSubImage(pos, 0, j, height);	
				pos = j;
			}else if(j-pos>18&&j-pos<30){
				//double characters
				int temp = pos;
				while(countPixelsVertical(binaryImage, temp)<2){
					temp++;
				}
				while(countPixelsVertical(binaryImage, temp)>2){
					temp++;
				}
				if(temp-pos>4)
					letters[count++] = binaryImage.getSubImage(pos, 0, temp, height);
				if(j-temp>4)
					letters[count++] = binaryImage.getSubImage(temp+1, 0, j, height);	
				pos = j;
			}else if(j-pos<5){
				//ignore
				pos = j;
			}
			while(countPixelsVertical(binaryImage, j)==0){
				j++;
				if(j>width-1){
					break;
				}
			}
		}
		for(int i=0;i<count;i++){
			letters[i].save(path+Integer.toString(index)+Integer.toString(i));
		}
	}
	/**
	 * Counts black pixels in a column
	 * @param bi
	 * @param column
	 * @return
	 */
	private int countPixelsVertical(BinaryImage bi, int column){
		int vcount = 0;
		for(int i=0;i<height;i++){
			if(binaryImage.getData(column, i)==1)
				vcount++;					//Number of black pixels
		}
		return vcount;
	}
}
