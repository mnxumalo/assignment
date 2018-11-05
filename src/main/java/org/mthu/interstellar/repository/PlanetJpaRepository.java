package org.mthu.interstellar.repository;

import org.mthu.interstellar.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetJpaRepository extends JpaRepository<Planet, Integer>{

}
