package com.soen.risk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.soen.risk.boundary.usecase.LoadGameInfoTest;
import com.soen.risk.boundary.usecase.LoadGameTest;
import com.soen.risk.boundary.usecase.SaveGameTest;
import com.soen.risk.entity.MapTest;
import com.soen.risk.entity.player.aggressive.*;
import com.soen.risk.entity.player.benevolent.*;
import com.soen.risk.entity.player.cheater.*;
import com.soen.risk.entity.player.human.*;
import com.soen.risk.entity.player.random.*;
import com.soen.risk.entity.player.random.RandomStartupStrategy;


@RunWith(Suite.class)
@SuiteClasses({ LoadGameInfoTest.class, LoadGameTest.class, SaveGameTest.class,
	MapTest.class,
	AggressiveAttackStrategyTest.class,AggressiveReinforceStrategyTest.class,AggressiveFortifyStrategyTest.class,
	BenevolentReinforceStrategyTest.class,BenevolentFortifyStrategyTest.class,
	CheaterReinforceStrategyTest.class,CheaterFortifyStrategyTest.class,CheaterAttackStrategyTest.class,
	HumanReinforceStrategyTest.class,HumanFortifyStrategyTest.class,HumanAttackStrategyTest.class,
	RandomReinforceStrategyTest.class,RandomFortifyStrategyTest.class,RandomAttackStrategyTest.class,RandomStartupStrategy.class
	

		})
public class AllTests {

}
