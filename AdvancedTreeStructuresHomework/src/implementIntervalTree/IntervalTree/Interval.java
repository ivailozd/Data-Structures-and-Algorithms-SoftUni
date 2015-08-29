package implementIntervalTree.IntervalTree;

public class Interval<T extends Comparable<T>> implements Comparable<Interval<T>>{

	private T start;
	private T end;
	
	public Interval(T start, T end) {
		if (start.compareTo(end) > 0) {
			throw new IllegalArgumentException("Start must be less or equal to end.");
		}
		
		this.start = start;
		this.end = end;
	}
	
	public T getStart() {
		return start;
	}

	public T getEnd() {
		return end;
	}
	
	public boolean contains(T element) {
        return (start.compareTo(element) < 0 || end.compareTo(element) > 0);
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval<T> other = (Interval<T>) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public int compareTo(Interval<T> other) {
		if(start.compareTo(other.getStart()) < 0)
			return -1;
		else if(start.compareTo(other.getStart()) > 0)
			return 1;
		else if(end.compareTo(other.getEnd()) < 0)
			return -1;
		else if(end.compareTo(other.getEnd()) > 0)
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "[" + start + " : " + end + "]";
	}
}
