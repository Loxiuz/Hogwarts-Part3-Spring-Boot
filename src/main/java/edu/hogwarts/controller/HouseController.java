package edu.hogwarts.controller;

import edu.hogwarts.repository.HouseRepository;

public class HouseController {

    private final HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }
}
