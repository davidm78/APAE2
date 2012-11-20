import java.io.File;
import java.util.concurrent.*;

public class DirectoryTree {
		
	public void processDirectory(String name, LinkedBlockingQueue<File> workQueue){
		try {
			File file = new File(name);
			if (file.isDirectory()){
				String entries[] = file.list();
					if (entries != null){
			             workQueue.add(file);
						for (String entry : entries){
								if(entry.compareTo(".") == 0)
									continue;
								if (entry.compareTo("..") == 0)
									continue;
								processDirectory(name+"/"+entry, workQueue);
						}
					}
		}
	} catch(Exception e){
		System.err.println("Error processing "+name+": "+e);
	}
	
	}
		
}

