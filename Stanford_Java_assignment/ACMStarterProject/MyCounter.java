
public class MyCounter {
	
	public MyCounter(int starValue){
		counter=starValue;
	}
	
	public MyCounter(){
		counter=1;
	}
	
	public int nextValue(){
		int temp = counter;
		counter++;
		return(temp);
		
	}
	
	private int counter;

}
