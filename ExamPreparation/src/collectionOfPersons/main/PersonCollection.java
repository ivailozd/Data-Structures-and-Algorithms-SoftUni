package collectionOfPersons.main;

public interface PersonCollection {
	boolean addPerson (String email, String name, int age, String town);
	int size();
	Person findPerson(String email);
	boolean deletePerson(String email);
	Iterable<Person> findPersons(String emailDomain);
	Iterable<Person> findPersons(String name, String town);
	Iterable<Person> findPersons(int startAge, int endAge);
	Iterable<Person> findPersons(int startAge, int endAge, String town);
}
