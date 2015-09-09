package collectionOfPersons.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PersonList implements PersonCollection {
	private List<Person> persons = new ArrayList<>();

	@Override
	public boolean addPerson(String email, String name, int age, String town) {
		if (findPerson(email) != null) {
			return false;
		}
		
		Person person = new Person(){
			{
				setEmail(email);
				setName(name);
				setAge(age);
				setTown(town);
			}
		};
		persons.add(person);
		return true;
	}

	@Override
	public int size() {
		return persons.size();
	}

	@Override
	public Person findPerson(String email) {
		Person person = null;
		try {
			person = persons.stream()
					.filter(p -> p.getEmail().equals(email))
					.findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
		
		return person;
	}

	@Override
	public boolean deletePerson(String email) {
		Person person = findPerson(email);
		return persons.remove(person);
	}

	@Override
	public Iterable<Person> findPersons(String emailDomain) {
		return persons.stream()
				.filter(p -> p.getEmail().endsWith("@" + emailDomain))
				.sorted((p, o)-> p.getEmail().compareTo(o.getEmail()))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<Person> findPersons(String name, String town) {
		return persons.stream()
				.filter(p -> p.getName().equals(name) && p.getTown().equals(town))
				.sorted((p, o) -> p.getEmail().compareTo(o.getEmail()))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<Person> findPersons(int startAge, int endAge) {
		Comparator<Person> comparator = Comparator.comparing(p -> p.getAge());
		return persons.stream()
				.filter(p -> startAge <= p.getAge() && p.getAge() <= endAge)
				.sorted(comparator.thenComparing(p -> p.getEmail()))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<Person> findPersons(int startAge, int endAge, String town) {
		Comparator<Person> comparator = Comparator.comparing(p -> p.getAge());
		return persons.stream()
				.filter(p -> p.getTown().equals(town) && startAge <= p.getAge() && p.getAge() <= endAge)
				.sorted(comparator.thenComparing(p -> p.getEmail()))
				.collect(Collectors.toList());
	}

}
