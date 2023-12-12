package mozdzierz.szymon.graphdb.database.person;

import mozdzierz.szymon.graphdb.database.car.Car;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public record Person(
        @Id
        Integer id,
        String name,
        @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING) List<Car> carList) {}
