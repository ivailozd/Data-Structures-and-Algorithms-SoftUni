package collectionOfPersons.main;

public class Person implements Comparable<Person>{
	private String email;
	private String name;
	private int age;
	private String town;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public int compareTo(Person o) {
		return this.getEmail().compareTo(o.getEmail());
	}
}
