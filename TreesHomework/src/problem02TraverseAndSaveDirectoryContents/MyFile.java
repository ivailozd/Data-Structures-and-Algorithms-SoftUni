package problem02TraverseAndSaveDirectoryContents;

public class MyFile {
	
	private String name;
	private long size;
	
	public MyFile(String name, long l) {
		setName(name);
		setSize(l);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
