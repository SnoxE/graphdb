package mozdzierz.szymon.graphdb.service;

import mozdzierz.szymon.graphdb.common.ContentDto;
import mozdzierz.szymon.graphdb.database.person.Person;
import mozdzierz.szymon.graphdb.database.person.PersonDto;
import mozdzierz.szymon.graphdb.database.person.PersonRepository;
import mozdzierz.szymon.graphdb.database.person.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static mozdzierz.szymon.graphdb.database.person.PersonDto.PersonDtoWithCarList;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonRepositoryImpl personRepositoryImpl;
    @Autowired
    private CarService carService;

    public Optional<Person> getPersonByName(Integer id) {
        return personRepository.findById(id);
    }

    public ContentDto<PersonDto> getAllUsers() {
        List<PersonDto> personDtoList = personRepository.findAll()
                .stream().map(PersonService::toPersonDto).toList();
        return new ContentDto<>(personDtoList);
    }
    public PersonDtoWithCarList getAllUsersWithCars(Integer id) {
        return personRepository.findById(id).map(PersonService::toPersonDtoWithCars).orElse(null);
    }

    public void addPerson(String name) throws Exception{
        personRepositoryImpl.addPerson(name);
    }

    public void addCarByUserId(Integer userId, String make, String model) throws Exception {
        personRepositoryImpl.addCarByUserId(userId, make, model);
    }

    public void deleteCarById(Integer carId) throws Exception {
        personRepositoryImpl.deleteCarById(carId);
    }

    private static PersonDto toPersonDto(Person person) {
        return new PersonDto(
                person.id(),
                person.name());
    }

    private static PersonDtoWithCarList toPersonDtoWithCars(Person person) {
        return new PersonDtoWithCarList(
                person.id(),
                person.name(),
                person.carList().stream().map(CarService::toCarDto).toList());
    }
}
