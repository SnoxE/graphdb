package mozdzierz.szymon.graphdb.database;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public record Person(
        @Id @GeneratedValue Long id,
        String name) {}
