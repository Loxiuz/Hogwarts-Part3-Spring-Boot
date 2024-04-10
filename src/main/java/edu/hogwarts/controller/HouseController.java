package edu.hogwarts.controller;

import edu.hogwarts.model.House;
import edu.hogwarts.repository.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public List<House> getHouses(){
       return houseRepository.findAll();
    }

    @GetMapping(path = "/{name}")
    public House getHouseByName(@PathVariable String name){
        return houseRepository.findById(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "House not found in database"));
    }
}
