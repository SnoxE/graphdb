package mozdzierz.szymon.graphdb.database.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import mozdzierz.szymon.graphdb.database.car.CarDto;

import java.util.List;

public record PersonDto(
        @JsonProperty("id") Integer id,
        @JsonProperty("name") String name) {
    public record PersonDtoWithCarList(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name,
            @JsonProperty("carList") List<CarDto> carList) {}
}
