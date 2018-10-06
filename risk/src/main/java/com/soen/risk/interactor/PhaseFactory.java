package com.soen.risk.interactor;

import com.soen.risk.interactor.phase.AttackPhase;
import com.soen.risk.interactor.phase.FortifyPhase;
import com.soen.risk.interactor.phase.ReinforcePhase;

public class PhaseFactory {
    public static Phase build(String name) {
        switch (name.toLowerCase()) {
            case "reinforce":
                return new ReinforcePhase();
            case "attack":
                return new AttackPhase();
            case "fortify":
                return new FortifyPhase();
        }
        return null;
    }

    // DIP - dependency inversion principle
    public interface Phase {
        public void execute();
    }
}
