package org.mthu.interstellar.service;

import org.mthu.interstellar.model.Planet;
import org.mthu.interstellar.repository.PlanetJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService{

	@Autowired
	PlanetJpaRepository repository;
	
	@Override
	public void save(Planet planet) {
		
		repository.save(planet);
	}

}
