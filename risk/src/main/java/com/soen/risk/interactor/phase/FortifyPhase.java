//REQUIRED!! Provide from which country to which country army move and number of army move
//IMPORTANT!! Check if desired countries to move should be less than capacity of country
package com.soen.risk.interactor.phase;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;

public class FortifyPhase implements Phase {
    private String name;
    private int check;

    private String startCountry;
    private String endCountry;
    private int armyCount;

    public FortifyPhase(String startCountry, String endCountry, int armyCount) {
        this.name = "fortifyPhase";
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.armyCount = armyCount;
    }

    @Override
    public boolean isValid() {
        if (GamePlay.getInstance().getGame().getPlayers().size() == 1) {
            String currentPlayer = GamePlay.getInstance().getCurrentPlayer().getName();
            logger.log(Level.INFO, "Player won the game !!! " + currentPlayer);
            GamePlay.getInstance().endGame();
            return false;
        }
        return true;
    }

    @Override
    public void begin() {
        Player p = GamePlay.getInstance().getCurrentPlayer();
        if (p.getCountries().size() == 0) {
            logger.log(Level.INFO, "Dropping the player " + p.getName());
            GamePlay.getInstance().getGame().dropPlayer(p);
        } else {
            if (!this.startCountry.equals(this.endCountry)) {
                this.createCountryArray(this.startCountry, this.endCountry, this.armyCount);//REQUIRED!! First country, second country and army number
            }
        }

    }

    @Override
    public void execute() {

    }

    @Override
    public void exit() {
        GamePlay.getInstance().updateCurrentPlayer();
        GamePlay.getInstance().updateCurrentPhase();
    }

    @Override
    public String getName() {
        return this.name;
    }

    boolean CheckPathValid(Player p, LinkedList<Integer> movingPath) {
        for (int countryId : movingPath) {
            int flag = 0;
            for (Country c : p.getCountries()) {
                if (c.getId() == countryId) {
                    flag = 1;
                    break;
                }
            }
            if (flag != 1) {
                logger.log(Level.INFO, "Invalid path.");
                return false;
            }
        }
        logger.log(Level.INFO, "Valid path.");
        return true;
    }

    //Implement Depth First Search Algorithm
    void checkingContacting(LinkedList adj[], int CurrentCountry, boolean visited[], LinkedList<Integer> movingPath, int shift) {
        visited[CurrentCountry] = true;
        if (this.check == 0) {
            movingPath.add(CurrentCountry);
        } else {
            return;
        }
        System.out.print(CurrentCountry + " ");
        if (shift == CurrentCountry) {

            this.check = movingPath.size();
            return;

        }
        Iterator<Integer> i = adj[CurrentCountry].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                checkingContacting(adj, n, visited, movingPath, shift);
        }
    }

    //Start searching the path between Countries.
    public void searchPathBetweenCountries(LinkedList adj[], int currentCountry, int shift, int army) {
        boolean v[] = new boolean[adj.length];
        Player p = GamePlay.getInstance().getCurrentPlayer();
        LinkedList<Integer> movingPath = new LinkedList();
        ArrayList<Country> coun = GamePlay.getInstance().getGame().getMap().getCountries();
        this.check = 0; //To check whether it will get its destination country

        //Start searching for path between both countries
        this.checkingContacting(adj, currentCountry, v, movingPath, shift);

        if (this.check != 0) {
            //Finally Testing whether player able to move army
            logger.log(Level.INFO, "Path Followed");
            for (int countryId : movingPath) {
                logger.log(Level.INFO, "Country index - " + countryId);
            }
            boolean test = this.CheckPathValid(p, movingPath);
            if (test) {
                for (Country c : coun) {
                    if (c.getId() == currentCountry) {
                        c.setArmy(c.getArmy() - army);
                    }
                    if (c.getId() == shift) {
                        c.setArmy(c.getArmy() + army);
                    }
                }
            }
        }
    }

    //Creating 2D array of map in form of their countries id
    public void createCountryArray(String currentCountry, String Shift, int army) {
        ArrayList<Country> coun = GamePlay.getInstance().getGame().getMap().getCountries();
        int currentCoun = 0;
        int ShiftCoun = 0;
        //Convert Country name to their ids
        for (Country c : coun) {
            if (c.getName().equals(currentCountry)) {
                currentCoun = c.getId();
            } else if (c.getName().equals(Shift)) {
                ShiftCoun = c.getId();
            }
        }

        LinkedList<LinkedList<Country>> ll = GamePlay.getInstance().getGame().getMap().getAdjCountry();

        LinkedList<Integer> adj[] = new LinkedList[ll.size()];
        for (int i = 0; i < ll.size(); i++) {
            adj[i] = new LinkedList();
        }
        for (LinkedList<Country> ll1 : ll) {
            int index = ll1.get(0).getId();
            int j = 0;
            for (Country c : ll1) {
                if (j != 0) {
                    adj[index].add(c.getId());
                }
                j++;

            }
        }
        for (int i = 0; i < adj.length; i++) {
            System.out.print(i + " ");
            for (int a : adj[i]) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        this.searchPathBetweenCountries(adj, currentCoun, ShiftCoun, army);
    }
}
