package mozdzierz.szymon.graphdb.controller;

import mozdzierz.szymon.graphdb.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GraphController {

    @Autowired
    GraphService graphService;

    @GetMapping("/hello")
    public String sayHello() {
        return graphService.sayHelloFromService();
    }
}
