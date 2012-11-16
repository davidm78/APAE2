import java.util.LinkedList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.*;
import java.util.concurrent.*;

public class fileCrawler {
	
	//comment
	//this code is shit
	public static void main(String[] args){
		
		if (args.length < 2){
			System.out.println("Not enough arguments");
			System.exit(-1);
		}
		
		System.out.println(cvtPattern(args[0]));
		
		DirectoryTree dt = new DirectoryTree();
		
		if(args.length == 1){
			dt.processDirectory(".");
		} else {
			dt.processDirectory(args[1]);
		}
	}
		
		/***
		 * routine to convert bash pattern to regex pattern
		 * 
		 * e.g. if bashpat is "*.c", pattern generated is "^.*\.c$"
		 *      if bashpat is "a.*", pattern generated is "^a\..*$"
		 *
		 * i.e. '*' is converted to ".*"
		 *      '.' is converted to "\."
		 *      '?' is converted to "."
		 *      '^' is put at the beginning of the regex pattern
		 *      '$' is put at the end of the regex pattern
		 ***/
	public static String cvtPattern(String str){
			
			StringBuilder pat = new StringBuilder();
			int start, length;
			
			pat.append('^');
			if (str.charAt(0) == '\''){
				start = 1;
				length = str.length();
			} else {
				start = 0;
				length = str.length();
			}
			for (int i = start; i < length; i++){
				switch(str.charAt(i)){
				case '*': pat.append('.'); pat.append('*'); break;
				case '.': pat.append('\\'); pat.append('.'); break;
				case '?': pat.append('.'); break;
				default: pat.append(str.charAt(i)); break;
				}
			}
			pat.append('$');
			return new String(pat);
	}
	
}
