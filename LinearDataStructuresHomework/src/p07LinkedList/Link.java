package p07LinkedList;

public class Link<T>{
	public T element;
	public Link<?> next;
	
	public Link(T element) {
		this.element = element;
	}
	
	@Override
	public String toString() {
		return element.toString();
	}
}
