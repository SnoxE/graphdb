package mozdzierz.szymon.graphdb.database.car;

import org.springframework.data.annotation.Id;

public record Car(
        @Id
        Integer id,
        String make,
        String model) {}
