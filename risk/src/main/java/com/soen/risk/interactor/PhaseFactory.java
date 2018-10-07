package com.soen.risk.interactor;


import com.soen.risk.interactor.phase.AttackPhase;
import com.soen.risk.interactor.phase.FortifyPhase;
import com.soen.risk.interactor.phase.ReinforcePhase;
import com.soen.risk.interactor.phase.StartupPhase;

class PhaseFactory {
    static Phase build(String name) {
        switch (name.toLowerCase()) {
            case "startupphase":
                return new StartupPhase();
            case "reinforcephase":
                return new ReinforcePhase();
            case "attackphase":
                return new AttackPhase();
            case "fortifyphase":
                return new FortifyPhase();
        }
        return null;
    }
}

