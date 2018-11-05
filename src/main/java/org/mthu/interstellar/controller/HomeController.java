package org.mthu.interstellar.controller;

import java.util.List;

import org.mthu.interstellar.model.Vertex;
import org.mthu.interstellar.service.ShortestPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	private ShortestPathService service;

	@RequestMapping("/getPath")
	public String getPath(@RequestParam("source")String source, @RequestParam("destination")String destination, Model model) {
		System.out.println("Source : " + source + " Destination " + destination);
		List<Vertex> nodes = service.getPath(source, destination);
		model.addAttribute("nodes", nodes);
		return "index";
	}
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
}
