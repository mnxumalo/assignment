package org.mthu.interstellar.service;

import org.mthu.interstellar.model.Route;
import org.mthu.interstellar.repository.RouteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	RouteJpaRepository repository;

	@Override
	public void save(Route route) {
		
		repository.save(route);		
	}

}
