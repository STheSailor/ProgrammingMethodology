import acm.program.*;
import acm.util.*;
import java.io.*;

public class AnFileReaderExample extends ConsoleProgram {
	
	private BufferedReader openFile(String prompt){
		BufferedReader rd = null; 
		
		while (rd==null){
			try {
				String name = readLine(prompt);
				rd = new BufferedReader(new FileReader(name));
								
			}catch(IOException ex){
				println("The file doesn't exist. Please try angain.");
			}
		}
		return rd;
		
	}
	
		
	public void run(){
		BufferedReader rd = openFile("Please enter filename:");
		try{
			while(true){
				String line = rd.readLine();
				if (line==null) break;
				println("Read line:["+ line + "]");
			}
			rd.close();
		} catch (IOException ex){
			throw new ErrorException(ex);
			}
		
	}

}
