package circularList;

public class Link<T> {
	
	public T data;
	public Link<?> left;
	public Link<?> right;
	
	public Link(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
