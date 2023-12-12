package mozdzierz.szymon.graphdb.database.car;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarDto(
        @JsonProperty("id") Integer id,
        @JsonProperty("make") String make,
        @JsonProperty("model") String model) {}
