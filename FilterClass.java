package app;


import app.BinaryImage;

/**
 * Removes noise from captcha
 * @author Shubham Sharma
 */
public class FilterClass {
	private BinaryImage bi;
	/**
	 * Initialize BinaryImage
	 * @param bi BinaryImage
	 */
	public FilterClass(BinaryImage bi){
		this.bi = bi;
	}
	/**
	 * Removes Noise
	 * @return BinaryImage
	 */
	public BinaryImage removeNoise(){
		this.removeNoiseByWidth();
		//this.removeSpecialNoise();
		this.removeNoiseByCrop();
		return bi;
	}
	/**
	 * Remove lines and noise from the background
	 * We assume a minimum boundary for text color scheme
	 * If any, lines are assumed to be one pixel wide
	 * If number of adjacent black pixels is less than or equal to two, it is removed
	 */
	private void removeNoiseByWidth(){
		try{
			for(int i=1;i<bi.getHeight()-1;i++){
				for(int j=1;j<bi.getWidth()-1;j++){
					if(getAdjacentPixels(i, j)<=2||!checkVertical(i, j)){
						bi.setData(j,i,BinaryImage.BINARY_ON);
					}					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Remove special noise of double width lines
	 */
	/**private void removeSpecialNoise(){
		try{
			for(int j=1;j<bi.getWidth()-1;j++){
				OUTER: //label
				for(int i=2;i<bi.getHeight()-2;i++){
					int p = (bi.getData(j, i));
					int upper = bi.getData(j, i+1);
					int upperU = bi.getData(j, i+2);
					int lower = bi.getData(j, i-1);
					int lowerL = bi.getData(j, i-2);
					if(upper==BinaryImage.BINARY_OFF&&lower==BinaryImage.BINARY_OFF&&p==BinaryImage.BINARY_ON&&upperU==BinaryImage.BINARY_OFF&&lowerL==BinaryImage.BINARY_OFF){
						for(int k=i+3;k<bi.getHeight()-2;k++){
							int temp = bi.getData(j, k);
							if(temp==BinaryImage.BINARY_OFF){
								break OUTER;
							}
						}
						bi.setData(j, i-1, BinaryImage.BINARY_ON);
						bi.setData(j, i-2, BinaryImage.BINARY_ON);
						bi.setData(j, i+1, BinaryImage.BINARY_ON);
						bi.setData(j, i+2, BinaryImage.BINARY_ON);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	/**
	 * Remove noise by cropping 
	 */
	private void removeNoiseByCrop(){
		bi = bi.subImage(bi.getWidth()/4-8, 2, 3*bi.getWidth()/4+15, bi.getHeight()-2);
	}
	/**
	 * Counts the number of adjacent black cells
	 * @param i
	 * @param j
	 * @return count
	 */	
	private int getAdjacentPixels(int i, int j){
		int count = 0;
		int p[] = new int[8];
		p[0] = (bi.getData(j-1, i));
		p[1] = (bi.getData(j+1, i));
		p[2] = (bi.getData(j, i-1));
		p[3] = (bi.getData(j, i+1));
		p[4] = (bi.getData(j+1, i+1));
		p[5] = (bi.getData(j-1, i-1));
		p[6] = (bi.getData(j+1, i-1));
		p[7] = (bi.getData(j-1, i+1));
		for(int k=0;k<8;k++){
			if(p[k]==BinaryImage.BINARY_OFF)
				count++;
		}
		//System.out.println(count);
		return count;
	}
	/**
	 * Checks if it has a black pixel above and a black pixel below
	 * @param i
	 * @param j
	 * @return boolean
	 */
	private boolean checkVertical(int i, int j){
		int upper = (bi.getData(j, i+1));
		int lower = bi.getData(j, i-1);
		if(upper==BinaryImage.BINARY_OFF||lower==BinaryImage.BINARY_OFF)
			return true;
		return false;
	}
}
