package mozdzierz.szymon.graphdb.database.person;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Integer> {}