import java.util.regex.*;
import java.io.*;


public class ExampleOfRegex {
	
	public void init(){
		String str = "@������_";
		 
		String regEx = "@|_"; // ��ʾ@��_
		 
		Pattern p = Pattern.compile(regEx);
		 
		Matcher m = p.matcher(str);
		 
		boolean rs = m.find();
		 
		if(rs) {
			System.out.println("ture");
		}
	}

	
}
