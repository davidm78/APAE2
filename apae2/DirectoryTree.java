import java.io.File;

public class DirectoryTree {
		
	public void processDirectory(String name){
		try {
			File file = new File(name);
			if (file.isDirectory()){
				String entries[] = file.list();
					if (entries != null){
						System.out.println(name);
						for (String entry: entries){
								if(entry.compareTo(".") == 0)
									continue;
								if (entry.compareTo("..") == 0)
									continue;
								processDirectory(name+"/"+entry);
						}
					}
			}
	} catch(Exception e){
		System.err.println("Error processing "+name+": "+e);
	}
	
	}
		
}

