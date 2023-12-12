package mozdzierz.szymon.graphdb.controller;

import mozdzierz.szymon.graphdb.common.ResponseDto;
import mozdzierz.szymon.graphdb.database.car.CarDto;
import mozdzierz.szymon.graphdb.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    CarService carService;

//    @PostMapping("/add")
//    public ResponseEntity<ResponseDto> addCar(@RequestBody CarDto carDto) {
//        carService.add
//    }

}
