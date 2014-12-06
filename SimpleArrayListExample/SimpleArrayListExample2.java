
import acm.program.*;
import java.util.*;

public class SimpleArrayListExample2 extends ConsoleProgram{
	
	public void run(){
		setFont("Courier-24");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		readList(list);
		printArrayList(list);
		
		readList(list);
		printArrayList(list);
	}
	
	private void readList(ArrayList list){
		while(true){
			int item = readInt("?");
			if(item==0)break;
			list.add(item);
		}
	}
	
	private void printArrayList(ArrayList list){
		println("List contains " + list.size() + " elements.");
		for(int i=0; i<list.size(); i++){
			println(list.get(i));
		}
	}
	
	

}