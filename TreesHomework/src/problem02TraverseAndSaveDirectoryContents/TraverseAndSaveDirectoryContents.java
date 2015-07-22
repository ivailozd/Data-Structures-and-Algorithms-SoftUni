package problem02TraverseAndSaveDirectoryContents;

import java.io.File;

/**
 * Tested on Linux, not tested on Windows.
 */

public class TraverseAndSaveDirectoryContents {
	
	private static final String ROOT_DIR = "/home/ivailo/test/";

	public static void main(String[] args) {
		MyFolder root = new MyFolder(ROOT_DIR);
		saveDirectoryContents(root);
		
		double bytes = root.getSize();
		double kilobytes = (bytes / 1024);
		System.out.println("Root folder size: " + kilobytes + " KB");

		for (MyFolder folder : root.getFolders()) {
			System.out.println(folder.getName());
			
			for (MyFile file : folder.getFiles()) {
				System.out.println(file.getName());
			}
		}
	}

	public static void saveDirectoryContents(MyFolder dir) {
		
		File currentFile = new File(dir.getName());
		
		File[] files = currentFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				MyFolder newFolder = new MyFolder(file.getName());
				dir.getFolders().add(newFolder);
				saveDirectoryContents(newFolder);
			} else {
				MyFile newFile = new MyFile(file.getName(), file.length());
				dir.getFiles().add(newFile);
			}
		}
	}

}
