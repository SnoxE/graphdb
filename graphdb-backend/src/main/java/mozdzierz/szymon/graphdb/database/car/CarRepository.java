package mozdzierz.szymon.graphdb.database.car;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CarRepository extends Neo4jRepository<Car, Integer>  {}
