package mozdzierz.szymon.graphdb.controller;

import mozdzierz.szymon.graphdb.common.ContentDto;
import mozdzierz.szymon.graphdb.common.ResponseDto;
import mozdzierz.szymon.graphdb.database.car.CarDto;
import mozdzierz.szymon.graphdb.database.person.PersonDto;
import mozdzierz.szymon.graphdb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static mozdzierz.szymon.graphdb.database.person.PersonDto.PersonDtoWithCarList;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/get-all-users")
    public ContentDto<PersonDto> getAllPeople() { return personService.getAllUsers(); }

    @GetMapping("/get-user-by-id")
    public PersonDtoWithCarList getAllPeopleWithCars(
            @RequestParam("id") Integer id) { return personService.getAllUsersWithCars(id); }

    @PostMapping("/add-person")
    public ResponseEntity<ResponseDto> addPerson(@RequestParam("name") String name) throws Exception {
        personService.addPerson(name);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/add-car")
    public ResponseEntity<ResponseDto> addCarToUser(
            @PathVariable("userId") String userId,
            @RequestBody CarDto carDto) throws Exception {
        personService.addCarByUserId(Integer.parseInt(userId), carDto.make(), carDto.model());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-car-by-id")
    public ResponseEntity<ResponseDto> deleteCarById(@RequestParam("id") Integer id) throws Exception {
        personService.deleteCarById(id);

        return ResponseEntity.ok().build();
    }
}
