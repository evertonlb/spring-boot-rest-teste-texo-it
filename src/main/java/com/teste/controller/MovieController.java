package com.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.response.Result;
import com.teste.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping("/worstMovie")
	public Result getWorstMovie(){
	 	return movieService.getMinMax();
	}

}
