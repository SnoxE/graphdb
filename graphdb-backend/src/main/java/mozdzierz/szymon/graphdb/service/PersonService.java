package mozdzierz.szymon.graphdb.service;

import mozdzierz.szymon.graphdb.database.Person;
import mozdzierz.szymon.graphdb.database.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPersonsByName(String name) {
        return personRepository.findByName(name);
    }
}
