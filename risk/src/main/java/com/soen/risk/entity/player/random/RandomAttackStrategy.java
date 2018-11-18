package com.soen.risk.entity.player.random;

import java.util.List;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

public class RandomAttackStrategy implements AttackStrategy {
	private List<Country> won;
	private List<Country> lost;
    @Override
    public void execute(List<Country> countries, Map map) {

    }

    @Override
    public int getAttackCounter() {
        return 0;
    }
    public List<Country> getWon() {
		return won;
	}

	
	public List<Country> getLost() {
		return lost;
	}
}
