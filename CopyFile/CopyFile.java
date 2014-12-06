import acm.program.*;
import acm.util.*;
import java.io.*;

public class CopyFile extends ConsoleProgram {
	
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
			PrintWriter wr = new PrintWriter(new FileWriter("copy.txt"));
			while(true){
				String line = rd.readLine();
				if (line==null) break;
				println("Copying line:[" + line + "]");
				wr.println(line);
				
			}
			rd.close();
			wr.close();
			
		}catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
					
}
