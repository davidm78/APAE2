import java.io.File;
import java.util.concurrent.*;
import java.util.regex.*;

public class fileCrawler {
	
	public static void main(String[] args){
		
		if (args.length < 1){
			System.out.println("Not enough arguments");
			System.exit(-1);
		}
		
		String pattern = Regex.cvtPattern(args[0]);
		DirectoryTree dt = new DirectoryTree();
		LinkedBlockingQueue<File> workQueue = new LinkedBlockingQueue<File>();
		ConcurrentSkipListSet<File> anotherStructure = new ConcurrentSkipListSet<File>();
		
		if(args.length == 1){
			dt.processDirectory(".", workQueue);
		} else {
			for (int i = 1; i < args.length; i++){
				dt.processDirectory(args[i], workQueue);
			}
		}
		
		Pattern p = Pattern.compile(pattern);

		for (File file: workQueue){
				File[] entries = file.listFiles();
				for (File entry: entries){
					if (entry.isFile()){
						Matcher m = p.matcher(entry.getName());
						if (m.matches()){
							anotherStructure.add(entry);
					}
				}
			}
		}
		
		
		
		for (File file: anotherStructure){
			System.out.println(file.toString());
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
				length = str.length() - 1;
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
