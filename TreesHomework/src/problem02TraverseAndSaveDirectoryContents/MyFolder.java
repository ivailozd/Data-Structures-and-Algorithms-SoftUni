package problem02TraverseAndSaveDirectoryContents;

import java.util.LinkedList;
import java.util.List;

public class MyFolder {
	
	private String name;
	private long size;
	private List<MyFile> files = new LinkedList<>();
	private List<MyFolder> folders = new LinkedList<>();
	
	public MyFolder(String name) {
		setName(name);
		setSize(0);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<MyFile> getFiles() {
		return files;
	}
	
	public List<MyFolder> getFolders() {
		return folders;
	}

	public long getSize() {
		
		for (MyFile file : files) {
			size += file.getSize();
		}
		
		for (MyFolder folder : folders) {
			size += folder.getSize();
		}
		
		return size;
	}
	
	private void setSize(long size) {
		this.size = size;
	}
}
