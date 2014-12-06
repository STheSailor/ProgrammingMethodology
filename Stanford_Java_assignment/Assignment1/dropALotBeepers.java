import stanford.karel.*;

public class dropALotBeepers extends SuperKarel{
	
	public void run() {
		while (frontIsClear()){
			putBeeper();
			move();
			
		}
		
		putBeeper();
			
		
		}

}
