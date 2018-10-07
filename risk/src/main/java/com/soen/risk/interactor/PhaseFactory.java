package com.soen.risk.interactor;


import com.soen.risk.interactor.phase.AttackPhase;
import com.soen.risk.interactor.phase.FortifyPhase;
import com.soen.risk.interactor.phase.ReinforcePhase;
import com.soen.risk.interactor.phase.StartupPhase;

public class PhaseFactory {
    public static Phase build(String name) {
        switch (name.toLowerCase()) {
            case "startup":
                return new StartupPhase();
            case "reinforce":
                return new ReinforcePhase();
            case "attack":
                return new AttackPhase();
            case "fortify":
                return new FortifyPhase();
        }
        return null;
    }
}

