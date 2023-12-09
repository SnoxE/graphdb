package mozdzierz.szymon.graphdb;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphdbApplication implements CommandLineRunner {

	@Autowired
	Driver driver;

	public static void main(String[] args) {
		SpringApplication.run(GraphdbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (var session = driver.session()){
			session.run("match (Person {name: 'Frans Doe'})-[:PARENT]->()-[:PARENT]->(child)\n" +
					"return  DISTINCT child.name").list().forEach(record -> {
				System.out.println(record.get("child.name"));
			});
		}
	}

}
