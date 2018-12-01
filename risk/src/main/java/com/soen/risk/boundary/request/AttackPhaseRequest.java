package com.soen.risk.boundary.request;

/**
 * The Class AttackPhaseRequest.
 * 
 */
public class AttackPhaseRequest {
    
    /** The attacking country. */
    private String attackingCountry;
    
    /** The attacking dice count. */
    private int attackingDiceCount;
    
    /** The defending country. */
    private String defendingCountry;
    
    /** The defending dice count. */
    private int defendingDiceCount;
    
    /** The skip attack. */
    private int skipAttack;
    
    /** The all out mode. */
    private int allOutMode;

    /**
     * Instantiates a new attack phase request.
     *
     * @param args the args
     */
    public AttackPhaseRequest(String... args){
        this.attackingCountry = args[0];
        this.attackingDiceCount = Integer.valueOf(args[1]);
        this.defendingCountry = args[2];
        this.defendingDiceCount = Integer.valueOf(args[3]);
        this.skipAttack = Integer.valueOf(args[4]);
        this.allOutMode = Integer.valueOf(args[5]);
    }

    /**
     * Gets the attacking country.
     *
     * @return the attacking country
     */
    public String getAttackingCountry() {
        return attackingCountry;
    }

    /**
     * Sets the attacking country.
     *
     * @param attackingCountry the new attacking country
     */
    public void setAttackingCountry(String attackingCountry) {
        this.attackingCountry = attackingCountry;
    }

    /**
     * Gets the defending country.
     *
     * @return the defending country
     */
    public String getDefendingCountry() {
        return defendingCountry;
    }

    /**
     * Sets the defending country.
     *
     * @param defendingCountry the new defending country
     */
    public void setDefendingCountry(String defendingCountry) {
        this.defendingCountry = defendingCountry;
    }

    /**
     * Gets the skip attack.
     *
     * @return the skip attack
     */
    public int getSkipAttack() {
        return skipAttack;
    }

    /**
     * Sets the skip attack.
     *
     * @param skipAttack the new skip attack
     */
    public void setSkipAttack(int skipAttack) {
        this.skipAttack = skipAttack;
    }

    /**
     * Gets the all out mode.
     *
     * @return the all out mode
     */
    public int getAllOutMode() {
        return allOutMode;
    }

    /**
     * Sets the all out mode.
     *
     * @param allOutMode the new all out mode
     */
    public void setAllOutMode(int allOutMode) {
        this.allOutMode = allOutMode;
    }

    /**
     * Gets the attacking dice count.
     *
     * @return the attacking dice count
     */
    public int getAttackingDiceCount() {
        return attackingDiceCount;
    }

    /**
     * Sets the attacking dice count.
     *
     * @param attackingDiceCount the new attacking dice count
     */
    public void setAttackingDiceCount(int attackingDiceCount) {
        this.attackingDiceCount = attackingDiceCount;
    }

    /**
     * Gets the defending dice count.
     *
     * @return the defending dice count
     */
    public int getDefendingDiceCount() {
        return defendingDiceCount;
    }

    /**
     * Sets the defending dice count.
     *
     * @param defendingDiceCount the new defending dice count
     */
    public void setDefendingDiceCount(int defendingDiceCount) {
        this.defendingDiceCount = defendingDiceCount;
    }
}
