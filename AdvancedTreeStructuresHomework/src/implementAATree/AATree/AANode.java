package implementAATree.AATree;

class AANode<T> {
	private T value;
	private AANode<T> left;
	private AANode<T> right;
	private int level;
	
	public AANode() {
		value = null;
		left = this;
		right = this;
		level = 0;
	}
	
	public AANode(T value, AANode<T> left, AANode<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.level = 1;
	}
	
	public AANode(T value) {
		this(value, null, null);
	}

	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	public AANode<T> getLeft() {
		return left;
	}

	public void setLeft(AANode<T> left) {
		this.left = left;
	}

	public AANode<T> getRight() {
		return right;
	}

	public void setRight(AANode<T> right) {
		this.right = right;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
