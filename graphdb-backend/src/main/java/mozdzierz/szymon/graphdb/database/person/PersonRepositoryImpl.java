package mozdzierz.szymon.graphdb.database.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.neo4j.driver.Driver;

@Service
public class PersonRepositoryImpl {
    @Autowired
    Driver driver;

    public void addPerson(String name) throws Exception {
        if(name != null) {
            try (var session = driver.session()) {
                session.run("MATCH (c:Count)\n" +
                        "            SET c.count = c.count + 1\n" +
                        "            WITH c.count AS newId\n" +
                        "CREATE (" + name.toLowerCase().trim() + ":Person {id: newId, name: \"" + name + "\"})");
            }
        }
    }

    public void addCarByUserId(Integer userId, String make, String model) throws Exception {
        if(userId  > 0 && make != null && model != null) {
            try (var session = driver.session()) {
                session.run("MATCH (c:Count)\n" +
                        "SET c.count = c.count + 1\n" +
                        "WITH c.count AS newId\n" +
                        "CREATE (" + "xdd" +
                        ":Car {id: newId, make: \"" + make + "\", model: \"" + model + "\"}\n)" +
                        "WITH * \n" +
                        "MATCH (c:Count)\n" +
                        "WITH c.count AS newId\n" +
                        "match (xd:Person{id:" + userId + "})," +
                        "(" + model.toLowerCase().trim() + ":Car {id: newId})\n" +
                        "create (xd)-[:OWNS]->(" + model.toLowerCase().trim() + ")\n");
            }
        }
    }

    public void deleteCarById(Integer carId) throws Exception {
        if(carId  > 0) {
            try (var session = driver.session()) {
                session.run("MATCH (n:Car{id:" + carId + "})\n" +
                        "DETACH DELETE n");
            }
        }
    }
}
