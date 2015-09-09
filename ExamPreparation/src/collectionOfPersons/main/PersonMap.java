package collectionOfPersons.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class PersonMap implements PersonCollection {
	private final Map<String, Person> personsByEmail = new HashMap<>();
	private final Map<String, TreeSet<Person>> personByEmailDomain = new HashMap<>();
	private final Map<String, TreeSet<Person>> personByNameAndTown = new HashMap<>();
	private final Map<Integer, TreeSet<Person>> personByAge = new TreeMap<>();
	private final Map<String, TreeMap<Integer, TreeSet<Person>>> personByTownAndAge = new HashMap<>();

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
		personsByEmail.put(email, person);
		String domain = extractEmailDomain(email);
		personByEmailDomain.putIfAbsent(domain, new TreeSet<>());
		personByEmailDomain.get(domain).add(person);
		String nameAndTown = combineNameAndTown(name, town);
		personByNameAndTown.putIfAbsent(nameAndTown, new TreeSet<>());
		personByNameAndTown.get(nameAndTown).add(person);
		personByAge.putIfAbsent(age, new TreeSet<>());
		personByAge.get(age).add(person);
		personByTownAndAge.putIfAbsent(town, new TreeMap<>());
		personByTownAndAge.get(town).putIfAbsent(age, new TreeSet<>());
		personByTownAndAge.get(town).get(age).add(person);
		return true;
	}

	@Override
	public int size() {
		return personsByEmail.size();
	}

	@Override
	public Person findPerson(String email) {
		return personsByEmail.get(email);
	}

	@Override
	public boolean deletePerson(String email) {
		Person person = findPerson(email);
		if (person == null) {
			return false;
		}
		
		personsByEmail.remove(email);
		String domain = extractEmailDomain(email);
		personByEmailDomain.get(domain).remove(person);
		String nameAndTown = combineNameAndTown(person.getName(), person.getTown());
		personByNameAndTown.get(nameAndTown).remove(person);
		personByAge.get(person.getAge()).remove(person);
		personByTownAndAge.get(person.getTown()).get(person.getAge()).remove(person);
		return true;
	}

	@Override
	public Iterable<Person> findPersons(String emailDomain) {
		Set<Person> persons = personByEmailDomain.get(emailDomain);
		return persons != null ? new ArrayList<Person>(persons) : new ArrayList<Person>();
	}

	@Override
	public Iterable<Person> findPersons(String name, String town) {
		Set<Person> persons = personByNameAndTown.get(combineNameAndTown(name, town));
		return persons != null ? new ArrayList<Person>(persons) : new ArrayList<Person>();
	}

	@Override
	public Iterable<Person> findPersons(int startAge, int endAge) {
		Map<Integer, TreeSet<Person>> personsInRange = 
				((TreeMap<Integer, TreeSet<Person>>)personByAge)
				.headMap(endAge, true)
				.tailMap(startAge);
		List<Person> personsList = new ArrayList<>();
		for (TreeSet<Person> persons : personsInRange.values()) {
			personsList.addAll(persons);
		}
		
		return personsList;
	}

	@Override
	public Iterable<Person> findPersons(int startAge, int endAge, String town) {
		List<Person> personsList = new ArrayList<>();
		if (personByTownAndAge.get(town) != null) {
			Map<Integer, TreeSet<Person>> personsInRange = personByTownAndAge.get(town)
					.headMap(endAge, true)
					.tailMap(startAge);
			for (TreeSet<Person> persons : personsInRange.values()) {
				personsList.addAll(persons);
			}
		}
		
		return personsList;
	}
	
	private static String extractEmailDomain(String email){
		return email.split("@")[1];
	}
	
	private static String combineNameAndTown(String name, String town) {
		final String separator = "|!|";
		return name + separator + town;
	}

}
