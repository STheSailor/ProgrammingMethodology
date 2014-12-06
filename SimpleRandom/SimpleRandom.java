
import acm.program.*;
import acm.util.*;


public class SimpleRandom extends ConsoleProgram{
	
	public void run(){
		
		int dieRoll = rgen.nextInt(1,6);
		print("You rolled " + dieRoll);
	}
	
	/* Private instance variables */
	private RandomGenerator rgen =
		RandomGenerator.getInstance();

}
