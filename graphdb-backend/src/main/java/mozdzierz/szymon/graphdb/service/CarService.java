package mozdzierz.szymon.graphdb.service;

import mozdzierz.szymon.graphdb.database.car.Car;
import mozdzierz.szymon.graphdb.database.car.CarDto;
import mozdzierz.szymon.graphdb.database.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    static CarDto toCarDto(Car car) {
        return new CarDto(
                car.id(),
                car.make(),
                car.model());
    }
}
