package collectionOfPersons.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import collectionOfPersons.main.*;

public class UnitTestsPersonCollection {
	private PersonCollection persons;

	@Before
	public void setUp() {
//		persons = new PersonList();
		persons = new PersonMap();
	}

	@Test
	public void addPerson_ShouldWorkCorrectly() {
		boolean isAdded = persons.addPerson("pesho@gmail.com", "Peter", 18, "Sofia");
		assertTrue(isAdded);
		assertEquals(1, persons.size());
	}
	
	@Test
	public void addPerson_DuplicatedEmail_ShouldWorkCorrectly() {
        boolean isAddedFirst = persons.addPerson("pesho@gmail.com", "Peter", 18, "Sofia");
        boolean isAddedSecond = persons.addPerson("pesho@gmail.com", "Maria", 24, "Plovdiv");
        assertTrue(isAddedFirst);
        assertFalse(isAddedSecond);
        assertEquals(1, persons.size());
    }
	
	@Test
	public void findPerson_ExistingPerson_ShouldReturnPerson() {
        persons.addPerson("pesho@gmail.com", "Peter", 28, "Plovdiv");
        Person person = persons.findPerson("pesho@gmail.com");
        assertNotNull(person);
    }
    
	@Test
	public void findPerson_NonExistingPerson_ShouldReturnNothing() {
        Person person = persons.findPerson("pesho@gmail.com");
        assertNull(person);
    }
	
	@Test
	public void deletePerson_ShouldWorkCorrectly() {
        persons.addPerson("pesho@gmail.com", "Peter", 28, "Plovdiv");
        boolean isDeletedExisting = persons.deletePerson("pesho@gmail.com");
        boolean isDeletedNonExisting = persons.deletePerson("pesho@gmail.com");
        assertTrue(isDeletedExisting);
        assertFalse(isDeletedNonExisting);
        assertEquals(0, persons.size());
    }
	
	@Test
	public void findPersonsByEmailDomain_ShouldReturnMatchingPersons() {
        persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Sofia");
        persons.addPerson("mary@gmail.com", "Maria", 21, "Plovdiv");
        persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");

        List<Person> personsGmail = (List<Person>) persons.findPersons("gmail.com");
        List<Person> personsYahoo = (List<Person>) persons.findPersons("yahoo.co.uk");
        List<Person> personsHoo = (List<Person>) persons.findPersons("hoo.co.uk");

        assertArrayEquals(
        		new String[]{ "ani@gmail.com", "mary@gmail.com", "pesho@gmail.com" },
        		personsGmail.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "kiro@yahoo.co.uk" },
        		personsYahoo.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ },
        		personsHoo.stream().map(p -> p.getEmail()).toArray());
    }
	
	@Test
	public void findPersonsByNameAndTown_ShouldReturnMatchingPersons() {
        persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Sofia");
        persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
        persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
        persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");

        List<Person> personsPeshoPlovdiv = (List<Person>) persons.findPersons("Pesho", "Plovdiv");
        List<Person> personsLowercase = (List<Person>) persons.findPersons("pesho", "plovdiv");
        List<Person> personsPeshoNoTown = (List<Person>) persons.findPersons("Pesho", null);
        List<Person> personsAnnaBourgas = (List<Person>) persons.findPersons("Anna", "Bourgas");

        assertArrayEquals(
        		new String[]{ "pepi2@yahoo.fr", "pepi@gmail.com", "pesho@gmail.com" },
        		personsPeshoPlovdiv.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ },
        		personsLowercase.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ },
        		personsPeshoNoTown.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "ani@gmail.com" },
        		personsAnnaBourgas.stream().map(p -> p.getEmail()).toArray());
    }

	@Test
	public void FindPersonsByAgeRange_ShouldReturnMatchingPersons() {
        persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Sofia");
        persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
        persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
        persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");
        persons.addPerson("asen@gmail.com", "Asen", 21, "Rousse");

        List<Person> personsAgedFrom21to22 = (List<Person>) persons.findPersons(21, 22);
        List<Person> personsAgedFrom10to11 = (List<Person>) persons.findPersons(10, 11);
        List<Person> personsAged21 = (List<Person>) persons.findPersons(21, 21);
        List<Person> personsAged19 = (List<Person>) persons.findPersons(19, 19);
        List<Person> personsAgedFrom0to1000 = (List<Person>) persons.findPersons(0, 1000);

        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "pepi2@yahoo.fr", "pepi@gmail.com", "kiro@yahoo.co.uk" },
        		personsAgedFrom21to22.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ },
        		personsAgedFrom10to11.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "pepi2@yahoo.fr" , "pepi@gmail.com" },
        		personsAged21.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "ani@gmail.com" },
        		personsAged19.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "ani@gmail.com", "asen@gmail.com", "pepi2@yahoo.fr", "pepi@gmail.com", "kiro@yahoo.co.uk", "pesho@gmail.com" },
        		personsAgedFrom0to1000.stream().map(p -> p.getEmail()).toArray());
    }
	
	@Test
	public void findPersonsByAgeRangeAndTown_ShouldReturnMatchingPersons() {
        persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        persons.addPerson("kirosofia@yahoo.co.uk", "Kiril", 22, "Sofia");
        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Plovdiv");
        persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
        persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
        persons.addPerson("ani17@gmail.com", "Anna", 17, "Bourgas");
        persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");
        persons.addPerson("asen.rousse@gmail.com", "Asen", 21, "Rousse");
        persons.addPerson("asen@gmail.com", "Asen", 21, "Plovdiv");

        List<Person> personsAgedFrom21to22Plovdiv = (List<Person>) persons.findPersons(21, 22, "Plovdiv");
        List<Person> personsAgedFrom10to11Sofia = (List<Person>) persons.findPersons(10, 11, "Sofia");
        List<Person> personsAged21Plovdiv = (List<Person>) persons.findPersons(21, 21, "Plovdiv");
        List<Person> personsAged19Bourgas = (List<Person>) persons.findPersons(19, 19, "Bourgas");
        List<Person> personsAgedFrom0to1000Plovdiv = (List<Person>) persons.findPersons(0, 1000, "Plovdiv");
        List<Person> personsAgedFrom0to1000NewYork = (List<Person>) persons.findPersons(0, 1000, "New York");

        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "pepi2@yahoo.fr", "pepi@gmail.com", "kiro@yahoo.co.uk" },
        		personsAgedFrom21to22Plovdiv.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ },
        		personsAgedFrom10to11Sofia.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "pepi2@yahoo.fr" , "pepi@gmail.com" },
        		personsAged21Plovdiv.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "ani@gmail.com" },
        		personsAged19Bourgas.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "pepi2@yahoo.fr", "pepi@gmail.com", "kiro@yahoo.co.uk", "pesho@gmail.com" },
        		personsAgedFrom0to1000Plovdiv.stream().map(p -> p.getEmail()).toArray());
        assertArrayEquals(
        		new String[]{ },
        		personsAgedFrom0to1000NewYork.stream().map(p -> p.getEmail()).toArray());
    }
	
	@Test
	public void findDeletedPersons_ShouldReturnEmptyCollection() {
        persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        persons.addPerson("kirosofia@yahoo.co.uk", "Kiril", 22, "Sofia");
        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Plovdiv");
        persons.addPerson("pepi@gmail.com", "Pesho", 21, "Plovdiv");
        persons.addPerson("ani@gmail.com", "Anna", 19, "Bourgas");
        persons.addPerson("ani17@gmail.com", "Anna", 17, "Bourgas");
        persons.addPerson("pepi2@yahoo.fr", "Pesho", 21, "Plovdiv");
        persons.addPerson("asen.rousse@gmail.com", "Asen", 21, "Rousse");
        persons.addPerson("asen@gmail.com", "Asen", 21, "Plovdiv");

        persons.deletePerson("pesho@gmail.com");
        persons.deletePerson("kirosofia@yahoo.co.uk");
        persons.deletePerson("kiro@yahoo.co.uk");
        persons.deletePerson("pepi@gmail.com");
        persons.deletePerson("ani@gmail.com");
        persons.deletePerson("ani17@gmail.com");
        persons.deletePerson("pepi2@yahoo.fr");
        persons.deletePerson("asen.rousse@gmail.com");
        persons.deletePerson("asen@gmail.com");

        Person personPeshoGmail = persons.findPerson("pesho@gmail.com");

        List<Person> personsGmail = (List<Person>) persons.findPersons("gmail.com");
        List<Person> personsYahoo = (List<Person>) persons.findPersons("yahoo.co.uk");

        List<Person> personsPeshoPlovdiv = (List<Person>) persons.findPersons("Pesho", "Plovdiv");

        List<Person> personsAgedFrom21to22 = (List<Person>) persons.findPersons(21, 22);
        List<Person> personsAgedFrom0to1000 = (List<Person>) persons.findPersons(0, 1000);

        List<Person> personsAgedFrom21to22Plovdiv = (List<Person>) persons.findPersons(21, 22, "Plovdiv");
        List<Person> personsAged19Bourgas = (List<Person>) persons.findPersons(19, 19, "Bourgas");

        assertNull(personPeshoGmail);

        assertEquals(0, personsGmail.size());
        assertEquals(0, personsYahoo.size());

        assertEquals(0, personsPeshoPlovdiv.size());

        assertEquals(0, personsAgedFrom21to22.size());
        assertEquals(0, personsAgedFrom0to1000.size());

        assertEquals(0, personsAgedFrom21to22Plovdiv.size());
        assertEquals(0, personsAged19Bourgas.size());
    }
	
	@Test
	public void multipleOperations_ShouldReturnWorkCorrectly() {
        boolean isAdded = persons.addPerson("pesho@gmail.com", "Pesho", 28, "Plovdiv");
        assertTrue(isAdded);
        assertEquals(1, persons.size());

        isAdded = persons.addPerson("pesho@gmail.com", "Pesho2", 222, "Plovdiv222");
        assertFalse(isAdded);
        assertEquals(1, persons.size());

        persons.addPerson("kiro@yahoo.co.uk", "Kiril", 22, "Plovdiv");
        assertEquals(2, persons.size());

        persons.addPerson("asen@gmail.com", "Asen", 22, "Sofia");
        assertEquals(3, persons.size());

        Person person = persons.findPerson("non-existing person");
        assertNull(person);

        person = persons.findPerson("pesho@gmail.com");
        assertNotNull(person);
        assertEquals("pesho@gmail.com", person.getEmail());
        assertEquals("Pesho", person.getName());
        assertEquals(28, person.getAge());
        assertEquals("Plovdiv", person.getTown());

        List<Person> personsGmail = (List<Person>) persons.findPersons("gmail.com");
        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "pesho@gmail.com" },
        		personsGmail.stream().map(p -> p.getEmail()).toArray());

        List<Person> personsPeshoPlovdiv = (List<Person>) persons.findPersons("Pesho", "Plovdiv");
        assertArrayEquals(
        		new String[]{ "pesho@gmail.com" },
        		personsPeshoPlovdiv.stream().map(p -> p.getEmail()).toArray());

        List<Person> personsPeshoSofia = (List<Person>) persons.findPersons("Pesho", "Sofia");
        assertEquals(0, personsPeshoSofia.size());

        List<Person> personsKiroPlovdiv = (List<Person>) persons.findPersons("Kiro", "Plovdiv");
        assertEquals(0, personsKiroPlovdiv.size());

        List<Person> personsAge22To28 = (List<Person>) persons.findPersons(22, 28);
        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "kiro@yahoo.co.uk", "pesho@gmail.com" },
        		personsAge22To28.stream().map(p -> p.getEmail()).toArray());

        List<Person> personsAge22To28Plovdiv = (List<Person>) persons.findPersons(22, 28, "Plovdiv");
        assertArrayEquals(
        		new String[]{ "kiro@yahoo.co.uk", "pesho@gmail.com" },
        		personsAge22To28Plovdiv.stream().map(p -> p.getEmail()).toArray());

        boolean isDeleted = persons.deletePerson("pesho@gmail.com");
        assertTrue(isDeleted);

        isDeleted = persons.deletePerson("pesho@gmail.com");
        assertFalse(isDeleted);

        person = persons.findPerson("pesho@gmail.com");
        assertNull(person);

        personsGmail = (List<Person>) persons.findPersons("gmail.com");
        assertArrayEquals(
        		new String[]{ "asen@gmail.com" },
        		personsGmail.stream().map(p -> p.getEmail()).toArray());

        personsPeshoPlovdiv = (List<Person>) persons.findPersons("Pesho", "Plovdiv");
        assertArrayEquals(
        		new String[]{ },
        		personsPeshoPlovdiv.stream().map(p -> p.getEmail()).toArray());

        personsPeshoSofia = (List<Person>) persons.findPersons("Pesho", "Sofia");
        assertEquals(0, personsPeshoSofia.size());

        personsKiroPlovdiv = (List<Person>) persons.findPersons("Kiro", "Plovdiv");
        assertEquals(0, personsKiroPlovdiv.size());

        personsAge22To28 = (List<Person>) persons.findPersons(22, 28);
        assertArrayEquals(
        		new String[]{ "asen@gmail.com", "kiro@yahoo.co.uk" },
        		personsAge22To28.stream().map(p -> p.getEmail()).toArray());

        personsAge22To28Plovdiv = (List<Person>) persons.findPersons(22, 28, "Plovdiv");
        assertArrayEquals(
        		new String[]{ "kiro@yahoo.co.uk" },
        		personsAge22To28Plovdiv.stream().map(p -> p.getEmail()).toArray());
    }
}
