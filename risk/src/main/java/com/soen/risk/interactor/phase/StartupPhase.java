package com.soen.risk.interactor.phase;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;

public class StartupPhase implements Phase {
    private String countryName;
    private int armyCount;


    private String name;
    private GamePlay gamePlay;

    public StartupPhase(String countryName, int armyCount) {
        this.countryName = countryName;
        this.armyCount = armyCount;

        this.name = "startupPhase";
        this.gamePlay = GamePlay.getInstance();
    }

    public boolean isValid() {
        if (allPlayersHaveZeroArmy()) {
            this.gamePlay.updateCurrentPhase();
            return false;
        } else if (currentPlayerHasZeroArmy()) {
            this.gamePlay.updateCurrentPlayer();
            return false;
        }
        return true;
    }

    private boolean allPlayersHaveZeroArmy() {
        for (Player p : this.gamePlay.getGame().getPlayers()) {
            if (p.getArmyCapacity() != 0) return false;
        }
        return true;
    }

    private boolean currentPlayerHasZeroArmy() {
        return this.gamePlay.getCurrentPlayer().getArmyCapacity() == 0;
    }

    public void begin() {
    }

    @Override
    public void execute() {
        for (Country c : this.gamePlay.getGame().getMap().getCountries()) {
            if (c.getName().equals(this.countryName)) {
                c.setArmy(this.armyCount);
                this.gamePlay.getCurrentPlayer().setArmyCapacity(
                        this.gamePlay.getCurrentPlayer().getArmyCapacity() - this.armyCount);
                break;
            }
        }
    }

    public void exit() {
        this.gamePlay.updateCurrentPlayer();
    }

    public String getName() {
        return this.name;
    }
}
