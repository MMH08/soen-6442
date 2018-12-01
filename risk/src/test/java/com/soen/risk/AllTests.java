package com.soen.risk;

import com.soen.risk.boundary.usecase.LoadGameTest;
import com.soen.risk.boundary.usecase.SaveGameTest;
import com.soen.risk.boundary.usecase.StartTournamentTest;
import com.soen.risk.entity.GameTest;
import com.soen.risk.entity.MapTest;
import com.soen.risk.entity.PlayerTest;
import com.soen.risk.entity.player.aggressive.AggressiveAttackStrategyTest;
import com.soen.risk.entity.player.aggressive.AggressiveFortifyStrategyTest;
import com.soen.risk.entity.player.aggressive.AggressiveReinforceStrategyTest;
import com.soen.risk.entity.player.aggressive.AggressiveStartupStrategyTest;
import com.soen.risk.entity.player.benevolent.BenevolentAttackStrategyTest;
import com.soen.risk.entity.player.benevolent.BenevolentFortifyStrategyTest;
import com.soen.risk.entity.player.benevolent.BenevolentReinforceStrategyTest;
import com.soen.risk.entity.player.benevolent.BenevolentStartupStrategyTest;
import com.soen.risk.entity.player.cheater.CheaterAttackStrategyTest;
import com.soen.risk.entity.player.cheater.CheaterFortifyStrategyTest;
import com.soen.risk.entity.player.cheater.CheaterReinforceStrategyTest;
import com.soen.risk.entity.player.cheater.CheaterStartupStrategyTest;
import com.soen.risk.entity.player.human.HumanAttackStrategyTest;
import com.soen.risk.entity.player.human.HumanFortifyStrategyTest;
import com.soen.risk.entity.player.human.HumanReinforceStrategyTest;
import com.soen.risk.entity.player.human.HumanStartupStrategyTest;
import com.soen.risk.entity.player.random.RandomAttackStrategyTest;
import com.soen.risk.entity.player.random.RandomFortifyStrategyTest;
import com.soen.risk.entity.player.random.RandomReinforceStrategyTest;
import com.soen.risk.entity.player.random.RandomStartupStrategyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * The Class AllTests.
 */
@RunWith(Suite.class)
@SuiteClasses({LoadGameTest.class, SaveGameTest.class, StartTournamentTest.class,
        PlayerTest.class, MapTest.class, GameTest.class,
        AggressiveStartupStrategyTest.class, AggressiveReinforceStrategyTest.class, AggressiveAttackStrategyTest.class, AggressiveFortifyStrategyTest.class,
        BenevolentStartupStrategyTest.class, BenevolentReinforceStrategyTest.class, BenevolentAttackStrategyTest.class, BenevolentFortifyStrategyTest.class,
        CheaterStartupStrategyTest.class, CheaterReinforceStrategyTest.class, CheaterAttackStrategyTest.class, CheaterFortifyStrategyTest.class,
        HumanStartupStrategyTest.class, HumanReinforceStrategyTest.class, HumanAttackStrategyTest.class, HumanFortifyStrategyTest.class,
        RandomStartupStrategyTest.class, RandomReinforceStrategyTest.class, RandomAttackStrategyTest.class, RandomFortifyStrategyTest.class
})
public class AllTests {

}
