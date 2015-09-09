package collectionOfPersons.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import collectionOfPersons.main.*;

public class PerformanceTestsPersonCollection {
	private PersonCollection persons;
	
	@Before
	public void setUp() {
//		persons = new PersonList();
		persons = new PersonMap();
	}
	
	private void addPersons(int count) {
        for (int i = 0; i < count; i++) {
            this.persons.addPerson(
                "pesho" + i + "@gmail" + (i % 100) + ".com",
                "Pesho" + (i % 100),
                i % 100,
                "Sofia" + (i % 100));
        }
    }

	@Test(timeout=250)
	public void testPerformance_AddPerson() {
        addPersons(5000);
        assertEquals(5000, persons.size());
    }
	
	@Test(timeout=200)
	public void testPerformance_FindPerson() {
        addPersons(5000);
        for (int i = 0; i < 100000; i++) {
            Person existingPerson = persons.findPerson("pesho1@gmail1.com");
            assertNotNull(existingPerson);
            Person nonExistingPerson = persons.findPerson("non-existing email");
            assertNull(nonExistingPerson);
        }
    }
	
	@Test(timeout=300)
	public void testPerformance_FindPersonsByEmailDomain() {
        addPersons(5000);
        for (int i = 0; i < 10000; i++) {
            List<Person> existingPersons = (List<Person>) persons.findPersons("gmail1.com");
            assertEquals(50, existingPersons.size());
            List<Person> notExistingPersons = (List<Person>) persons.findPersons("non-existing email");
            assertEquals(0, notExistingPersons.size());
        }
    }
	
	@Test(timeout=300)
	public void testPerformance_FindPersonsByNameAndTown() {
        addPersons(5000);
        for (int i = 0; i < 10000; i++) {
        	List<Person> existingPersons = (List<Person>) persons.findPersons("Pesho1", "Sofia1");
        	assertEquals(50, existingPersons.size());
        	List<Person> notExistingPersons = (List<Person>) persons.findPersons("Pesho1", "Sofia5");
        	assertEquals(0, notExistingPersons.size());
        }
    }
	
	@Test(timeout=300)
	public void testPerformance_FindPersonsByAgeRange() {
        addPersons(5000);
        for (int i = 0; i < 2000; i++) {
            List<Person> existingPersons = (List<Person>) persons.findPersons(20, 21);
            assertEquals(100, existingPersons.size());
            List<Person> notExistingPersons = (List<Person>) persons.findPersons(500, 600);
            assertEquals(0, notExistingPersons.size());
        }
    }
	
	@Test(timeout=300)
	public void testPerformance_FindPersonsByTownAndAgeRange() {
        addPersons(5000);
        for (int i = 0; i < 5000; i++) {
            List<Person> existingPersons = (List<Person>) persons.findPersons(18, 22, "Sofia20");
            assertEquals(50, existingPersons.size());
            List<Person> notExistingTownPersons = (List<Person>) persons.findPersons(20, 30, "Missing town");
            assertEquals(0, notExistingTownPersons.size());
            List<Person> notExistingAgePersons = (List<Person>) persons.findPersons(200, 300, "Sofia1");
            assertEquals(0, notExistingAgePersons.size());
        }
    }

}
