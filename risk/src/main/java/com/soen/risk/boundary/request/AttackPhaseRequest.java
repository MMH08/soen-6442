package com.soen.risk.boundary.request;

/**
 * The Class AttackPhaseRequest.
 */
public class AttackPhaseRequest {
    private String attackingCountry;
    private int attackingDiceCount;
    private String defendingCountry;
    private int defendingDiceCount;
    private int skipAttack;
    private int allOutMode;

    public AttackPhaseRequest(String... args){
        this.attackingCountry = args[0];
        this.attackingDiceCount = Integer.valueOf(args[1]);
        this.defendingCountry = args[2];
        this.defendingDiceCount = Integer.valueOf(args[3]);
        this.skipAttack = Integer.valueOf(args[4]);
        this.allOutMode = Integer.valueOf(args[5]);
    }

    public String getAttackingCountry() {
        return attackingCountry;
    }

    public void setAttackingCountry(String attackingCountry) {
        this.attackingCountry = attackingCountry;
    }

    public String getDefendingCountry() {
        return defendingCountry;
    }

    public void setDefendingCountry(String defendingCountry) {
        this.defendingCountry = defendingCountry;
    }

    public int getSkipAttack() {
        return skipAttack;
    }

    public void setSkipAttack(int skipAttack) {
        this.skipAttack = skipAttack;
    }

    public int getAllOutMode() {
        return allOutMode;
    }

    public void setAllOutMode(int allOutMode) {
        this.allOutMode = allOutMode;
    }

    public int getAttackingDiceCount() {
        return attackingDiceCount;
    }

    public void setAttackingDiceCount(int attackingDiceCount) {
        this.attackingDiceCount = attackingDiceCount;
    }

    public int getDefendingDiceCount() {
        return defendingDiceCount;
    }

    public void setDefendingDiceCount(int defendingDiceCount) {
        this.defendingDiceCount = defendingDiceCount;
    }
}
