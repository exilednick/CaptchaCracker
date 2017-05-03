package app;

public class CharacterExtractor {
	private BinaryImage binaryImage;
	private int width, height, index;
	private String path;
	public CharacterExtractor(BinaryImage bi, String path, int index){
		binaryImage = bi;
		this.path = path;
		this.index = index;
		int temp[] = {bi.getWidth(), bi.getHeight()}; 
		width = temp[0];
		height = temp[1];
		extractLetters();
	}
	/*
	 * Extracts characters from the captcha
	 */
	public void extractLetters(){
		int count = 0, pos = 0;
		BinaryImage[] letters = new BinaryImage[10];
		int j = 0;
		while(countPixelsVertical(binaryImage, j)<=2){
			j++;
		}
		pos = j; //First character starts
		while(j<width){
			try{ //ensure last character goes well
				while(countPixelsVertical(binaryImage, j)>0){
					j++;
				}
			}catch(Exception e){
				if(j-pos>8)
					letters[count++] = binaryImage.subImage(pos, 0, j, height); 
				break;
			}
			if(j-pos<=18&&j-pos>5){
				//proper
				letters[count++] = binaryImage.subImage(pos, 0, j, height);	
				pos = j;
			}else if(j-pos>13&&j-pos<30){
				//double characters
				int temp = pos;
				int t_count = count; //store the count temporalily
				while(countPixelsVertical(binaryImage, temp)<2){
					temp++;
				}
				while(countPixelsVertical(binaryImage, temp)>2){
					temp++;
				}
				if(temp-pos>4)
					letters[count++] = binaryImage.subImage(pos, 0, temp, height);
				if(j-temp>15){
					letters[count++] = binaryImage.subImage(temp, 0, (j+temp)/2, height);
					letters[count++] = binaryImage.subImage((j+temp)/2, 0, j, height);
				}	
				else if(j-temp>4)
					letters[count++] = binaryImage.subImage(temp+1, 0, j, height);
				if(t_count==count){ //nothing happened 
					letters[count++] = binaryImage.subImage(pos, 0, (pos-j)/2, height);
					letters[count++] = binaryImage.subImage((pos-j)/2, 0, j, height);
				}
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
			if(i==5)
				break;
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
		for(int i=0;i<height-1;i++){
			if(binaryImage.getData(column, i)==BinaryImage.BINARY_OFF){
				vcount++;
			}
		}
		return vcount;
	}
}
