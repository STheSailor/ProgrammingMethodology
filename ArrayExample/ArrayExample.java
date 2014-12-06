/*
 * ArrayExample.java
 * ------------------
 * Tis program show an example of using arrays.
 */
import acm.program.*;

public class ArrayExample extends ConsoleProgram {
	
	private static final int SENTINEL = -1;
	
	public void run(){
		setFont("Courier 24");
		
		int maxLength = readInt("Max size of array:");
		int[] midtermScores = new int[maxLength];
		int numActualScores = 0;
		
		for(int i=0; i<maxLength; i++){
			midtermScores[i] = readInt("Next score:");
			if(midtermScores[i]== SENTINEL ) break;
			numActualScores++;
		}
		
		println("Average score:" 
				+ computeAverage(midtermScores,numActualScores));
		
	}
	
	private double computeAverage(int[] arr, int numActualScores){
		double average = 0;
		for(int i=0; i<numActualScores; i++){
			average += arr[i];
		}
		average = average/numActualScores;
		return average;
	}

}
