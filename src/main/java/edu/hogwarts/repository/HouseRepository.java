package edu.hogwarts.repository;

import edu.hogwarts.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, String> {
}
