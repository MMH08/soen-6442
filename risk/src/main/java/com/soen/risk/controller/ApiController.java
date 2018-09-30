package com.soen.risk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class ApiController {
	
	@RequestMapping("/load-map")
	public void loadMap(@RequestParam("filename") String filename) {
		// #Todo: load map call.
	}
	
	@RequestMapping("/play")
	public void play() {
		
	}

}
