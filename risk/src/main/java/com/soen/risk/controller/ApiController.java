package com.soen.risk.controller;

import com.soen.risk.entity.Map;
import com.soen.risk.interactor.GamePlay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class ApiController {

	@RequestMapping("/createMap")
	public void createMap() {
		Map map = new Map();
	}

	@RequestMapping("/GamePlay")
	public void play(@RequestParam("filename") String filename, @RequestParam("players") int countOfPlayers) {
		GamePlay.getInstance().build(filename, countOfPlayers);
	}

	@RequestMapping("/nextMove")
    public void nextMove(){
        GamePlay.getInstance();
    }

}
