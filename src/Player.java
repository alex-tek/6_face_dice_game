
/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */

/**
 * This is the Player class, this class stores information for the game players.
 *
 * @author Alexander Tekleab
 */
public class Player {

    private String name;
    private int balance;
    private int betAmount;
    private int guess1;
    private int guess2;
    public static int INTIAL_BALANCE = 500;

    /**
     * This is a constructor for the Player class.
     *
     * @param name - This parameter is setting player's name.
     */
    public Player(String name) {
        this.name = name;
        balance = INTIAL_BALANCE;

    }

    /**
     * This is a getter for the name field.
     *
     * @return - It return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter for the balance field.
     *
     * @return - It returns the balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * This is a getter for the betAmount field.
     *
     * @return - It returns the betAmount.
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * This is a getter for the first guess field.
     *
     * @return - It returns the first guess.
     */
    public int getGuess1() {
        return guess1;
    }

    /**
     * This is a getter for the second guess field.
     *
     * @return - It returns the second guess.
     */
    public int getGuess2() {
        return guess2;
    }

    /**
     * This is the setter for the balance field, it sets the balance.
     *
     * @param balance - uses the balance parameter to set balance.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * This is the setter for the betAmount field, it sets the betAmount.
     *
     * @param betAmount - uses the betAmount parameter to set betAmount.
     */
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    /**
     * This is the setter for the first guess field, it sets the first Guess.
     *
     * @param guess1 - uses the guess1 parameter to set guess1.
     */
    public void setGuess1(int guess1) {
        this.guess1 = guess1;
    }

    /**
     * This is the setter for the second guess field, it sets the second Guess.
     *
     * @param guess2 - uses the guess2 parameter to set guess2.
     */
    public void setGuess2(int guess2) {
        this.guess2 = guess2;
    }

}

