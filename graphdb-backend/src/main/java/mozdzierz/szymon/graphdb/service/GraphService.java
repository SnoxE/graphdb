package mozdzierz.szymon.graphdb.service;

import mozdzierz.szymon.graphdb.database.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphService {

    @Autowired
    private final GraphRepository graphRepository;

    public GraphService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public String sayHelloFromService() {
        return "Hello From Service";
    }
}
