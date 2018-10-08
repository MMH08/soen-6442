package com.soen.risk.interactor.phase;

import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;

public class StartupPhase extends Phase {

    public StartupPhase(){
        this.setName("startupPhase");
    }

    public void execute(String playerName, String countryName, int armyCount) {
       // GamePlay.getInstance().getGame().getPlayers()

    }
}
