package edu.hogwarts.controller;

import edu.hogwarts.model.House;
import edu.hogwarts.repository.HouseRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class HouseController {

    HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public List<House> getHouses(){
    return houseRepository.findAll();
    }
}
