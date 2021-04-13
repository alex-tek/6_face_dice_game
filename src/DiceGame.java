
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
/**
 * This is the DiceGame class, this class handles all of the game logistics.
 *
 * @author Alexander Tekleab
 */
public class DiceGame {

    // creating fields
    private Player[] players;
    private int numPlayers;
    private int pot;
    private boolean gameOver;
    private int gameMode;

    /**
     * This the constructor for the diceGame class and it calls the setUpGame
     * method.
     */
    public DiceGame() {
        // calling setUpGame
        setUpGame();

    }

    /**
     * This is a getter for the Player Array.
     *
     * @return - It returns the players from the array.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * This is a getter for the numPlayers.
     *
     * @return - It returns the number of players.
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * This is a getter for game over boolean field.
     *
     * @return - It returns a boolean checking if the game is over.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * This is the setUpGame method it sets up the came asks which difficulty
     * and number of players along with their name, it also calls the
     * displayRules method.
     */
    public void setUpGame() {
        // creating scanner
        Scanner input = new Scanner(System.in);
        // selectiong difficulty and printing out errors if not within boundar.
        System.out.println("There are 2 modes in this Dice Game. While mode 1 is easy, mode 2 is hard. Choose wisely x_x.  \n"
                + "1 = Easy mode \n"
                + "2 = Difficult mode\n");
        System.out.print("Select difficulty mode: ");
        gameMode = input.nextInt();
        while (gameMode != 1 && gameMode != 2) {
            System.out.println("\nError! Please select 1 or 2.");
            System.out.println("Select mode: \n1 = Easy mode\n2 = Difficult mode");
            System.out.print("\nSelect difficulty mode: ");
            gameMode = input.nextInt();
        }
        System.out.println("\nEnter the number of players: ");
        numPlayers = input.nextInt();
        input.nextLine();
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter the name of player " + (i + 1) + " : ");
            players[i] = new Player(input.nextLine());

        }
        // calling displayRules method
        displayRules();
    }

    /**
     * This displayRules method displays the rules for 2 modes of dice roll,
     * hard mode and easy mode.
     */
    public void displayRules() {
        if (gameMode == 1) {
            // printing rules for easy mode(2-12) dice roll
            System.out.println("\nRULES!");
            System.out.println("Each player places a bet and chooses a number between 2 and 12.\nThe total of all the bets forms a \"pot\".\n"
                    + "Then two dices are rolled.\nIf one of the players bet on the result correctly, she or he wins the entire pot.\n"
                    + "If more than one player bet on that number, the one who bet the most wins the entire pot.\n"
                    + "If there is a tie, they split the pot.\nIf nobody bet on that number, the money remains in the pot for the next round.\n"
                    + "The game is over if one of the players run out of money.");
        } else {
            // printing rules for hard mode(1-6) dice roll
            System.out.println("\nRULES!");
            System.out.println("Each player places a bet and choose two number between 1 and 6.\nThe total of all the bets forms a \"pot\".\n"
                    + "Then two dices are rolled.\nIf one of the players bet on the result corectly, she or he wins the entire pot.\n"
                    + "If more than one player bet on that number, the one who bet the most wins the entire pot.\n"
                    + "If there is a tie , they split the pot.\nIf nobody bet on that number, the money remains in that pot for next round.\n"
                    + "The game is over if one of the players runs out of money.");
        }

    }

    /**
     * This playGame method checks for when the game is over it also prints out
     * the balance of the pot and calls the checkWinner methods.
     */
    public void playGame() {
        int outcome1 = 0, outcome2 = 0;

        // creating conditions for easy mode(2-12)
        if (gameMode == 1) {
            for (int i = 0; i < numPlayers; i++) {
                playTurn(players[i]);
            }
            System.out.println("Balance in the pot = " + pot);
            Random random = new Random();
            outcome1 = (random.nextInt(12) + 1);
            checkWinner(outcome1);

            for (int i = 0; i < numPlayers; i++) {
                if (players[i].getBalance() == 0) {
                    gameOver = true;
                    break;
                }
            }
        } else {
            // creating condition for hard mode(1-6)
            for (int i = 0; i < numPlayers; i++) {
                playTurn(players[i]);
            }
            System.out.println("Balance in the pot = " + pot);
            Random random = new Random();
            outcome1 = (random.nextInt(6) + 1);
            outcome2 = (random.nextInt(6) + 1);
            checkWinner(outcome1, outcome2);
            for (int i = 0; i < numPlayers; i++) {
                if (players[i].getBalance() == 0) {

                    gameOver = true;
                    break;
                }
            }
        }

    }

    /**
     * This is the playerTurn method it asks for players bet amount each turn
     * and subtracts it from their balance, It also check if input dice roll is
     * between (2-12) or (1-6).
     *
     * @param player - Uses the parameter to get player balance and set player
     * balance.
     */
    public void playTurn(Player player) {

        Scanner input = new Scanner(System.in);
        // creating conditions for players betting amount
        System.out.println(player.getName() + ", " + "you have $" + player.getBalance() + " left, how much are you going to bet?");
        System.out.println("Enter your bet amount: ");
        int betAmount = input.nextInt();
        while (betAmount > player.getBalance()) {
            System.out.println("Error! Not enough balance!");
            System.out.println("Enter your bet amount: ");
            betAmount = input.nextInt();
        }
        player.setBetAmount(betAmount);
        player.setBalance(player.getBalance() - betAmount);
        pot += betAmount;
        if (gameMode == 1) {
            // creating condition for easy mode guess
            System.out.println("Enter the number you're betting on between 2 to 12: ");
            int guess = input.nextInt();
            while (guess < 2 || guess > 12) {
                System.out.println("Error! Your betting number should be between 2 to 12.");
                System.out.println("Enter the number you're betting on between 2 to 12: ");
                guess = input.nextInt();
            }
            player.setGuess1(guess);
        } else {
            // creating condition for first guess and second of hard mode
            System.out.println("Enter the first number you're betting on between 1 to 6: ");
            int guess = input.nextInt();
            while (guess < 1 || guess > 6) {
                System.out.println("Error! Your first betting number should be between 1 to 6.");
                System.out.println("Enter the first number you're betting on between 1 to 6: ");
                guess = input.nextInt();
            }
            player.setGuess1(guess);
            System.out.println("Enter the second number you're betting on between 1 to 6: ");
            guess = input.nextInt();
            while (guess < 1 || guess > 6) {
                System.out.println("Error! Your second betting number should be between 1 to 6.");
                System.out.println("Enter the second number you're betting on between 1 to 6: ");
                guess = input.nextInt();
            }
            player.setGuess2(guess);
        }

    }

    /**
     * This checkWinner method is for the easy mode it checks the rolled
     * dice(2-12) winner outcome and figure out the winner balance.
     *
     * @param outcome
     */
    public void checkWinner(int outcome) {
        int maxBet = 0;
        // creating ArrayLists
        ArrayList<Player> winner = new ArrayList<Player>();
        ArrayList<Player> loser = new ArrayList<Player>();
        ArrayList<Player> highestBet = new ArrayList<Player>();

        for (int i = 0; i < numPlayers; i++) {

            if (players[i].getGuess1() != outcome) {
                loser.add(players[i]);

            } else {
                winner.add(players[i]);

            }
        }

        // creating condition for 0 winners
        if (winner.size() == 0) {

            System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe outcome is " + outcome + " for this round!\n"
                    + "No winner in this round =(!\n");
            return;
        } else if (winner.size() == 1) {
            for (int i = 0; i < winner.size(); i++) {

                winner.get(i).setBalance(winner.get(i).getBalance() + pot);

                System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe outcome is " + outcome + " for this round!\n"
                        + winner.get(i).getName() + " won $" + pot + " this round");

                pot = 0;
            }
            for (int i = 0; i < loser.size(); i++) {
                System.out.println(loser.get(i).getName() + " lost $" + loser.get(i).getBetAmount() + " this round.");
            }
        }

        if (winner.size() > 1) {
            // checking for maxBet amount and which player has it
            for (int i = 0; i < winner.size(); i++) {
                if (winner.get(i).getBetAmount() > maxBet) {
                    maxBet = winner.get(i).getBetAmount();
                }

            }

            for (int i = 0; i < winner.size(); i++) {
                if (winner.get(i).getBetAmount() == maxBet) {
                    highestBet.add(winner.get(i));

                } else {
                    loser.add(winner.get(i));

                }
            }
            // setting up condition for highesetBet winner
            if (highestBet.size() == 1) {

                for (int i = 0; i < highestBet.size(); i++) {
                    System.out.println("The oucome is " + outcome + " for this round!");
                    highestBet.get(i).setBalance(highestBet.get(i).getBalance() + pot);
                    System.out.println(highestBet.get(i).getName() + " won $" + pot + " this round.");

                }

                for (int i = 0; i < loser.size(); i++) {
                    System.out.println(loser.get(i).getName() + " lost $" + loser.get(i).getBetAmount() + " this round.");

                }
            }

            if (highestBet.size() > 1) {

                // creating condition for multiple winners
                for (int i = 0; i < highestBet.size(); i++) {

                    highestBet.get(i).setBalance(highestBet.get(i).getBalance() + pot / highestBet.size());
                    System.out.println(highestBet.get(i).getName() + " won $" + pot / highestBet.size() + " this round.");

                }
                for (int i = 0; i < loser.size(); i++) {
                    System.out.println(loser.get(i).getName() + " lost $" + loser.get(i).getBetAmount() + " this round.");

                }

            }
            pot = 0;

        }

    }

    /**
     * This overloaded checkWinner method is for the hard mode(difficult mode)
     * it checks the two rolled dices(1-6) and figure out the winner.
     *
     * @param outcome1 - first rolled dice outcome.
     * @param outcome2 - second rolled dice outcome.
     */
    public void checkWinner(int outcome1, int outcome2) {
        int maxBet = 0;

        // creating arrays
        ArrayList<Player> winner = new ArrayList<Player>();
        ArrayList<Player> loser = new ArrayList<Player>();
        ArrayList<Player> highestBet = new ArrayList<Player>();

        // looping through players
        for (int i = 0; i < numPlayers; i++) {
            if (((players[i].getGuess1() == outcome1) && (players[i].getGuess2() == outcome2))) {
                winner.add(players[i]);

            } else {
                loser.add(players[i]);
            }
        }
        // checking for the winning coditions
        if (winner.size() == 0) {

            System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe first outcome is " + outcome1 + " for this round!\n"
                    + "The second outcome is " + outcome2 + " for this round!\n"
                    + "No winner in this round\n");
            return;
        } else if (winner.size() == 1) {
            for (int i = 0; i < winner.size(); i++) {

                winner.get(i).setBalance(winner.get(i).getBalance() + pot);

                System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe first outcome is " + outcome1 + " for this round!\n"
                        + "The second outcome is " + outcome2 + " for this round!\n"
                        + winner.get(i).getName() + " won $" + pot + " this round");

                pot = 0;
            }
            for (int i = 0; i < loser.size(); i++) {
                System.out.println(loser.get(i).getName() + " lost $" + loser.get(i).getBetAmount() + " this round.");
            }
        }

        if (winner.size() > 1) {
            for (int i = 0; i < winner.size(); i++) {
                if (winner.get(i).getBetAmount() > maxBet) {
                    maxBet = winner.get(i).getBetAmount();
                }

            }

            for (int i = 0; i < winner.size(); i++) {
                if (winner.get(i).getBetAmount() == maxBet) {
                    highestBet.add(winner.get(i));

                } else {
                    loser.add(winner.get(i));

                }
            }

            if (highestBet.size() == 1) {

                for (int i = 0; i < highestBet.size(); i++) {
                    System.out.println("\nThere is $" + pot + " in the pot for this round!\nThe first outcome is " + outcome1 + " for this round!\n"
                            + "The second outcome is " + outcome2 + " for this round!\n");
                    highestBet.get(i).setBalance(highestBet.get(i).getBalance() + pot);
                    System.out.println(highestBet.get(i).getName() + " won $" + pot + " this round.");

                }

                for (int i = 0; i < loser.size(); i++) {
                    System.out.println(loser.get(i).getName() + " lost $" + loser.get(i).getBetAmount() + " this round.");

                }
            }

            if (highestBet.size() > 1) {

                // looping through highestBet
                for (int i = 0; i < highestBet.size(); i++) {

                    // printing winner and dividing pot upon winners
                    highestBet.get(i).setBalance(highestBet.get(i).getBalance() + pot / highestBet.size());
                    System.out.println(highestBet.get(i).getName() + " won $" + pot / highestBet.size() + " this round.");

                }
                for (int i = 0; i < loser.size(); i++) {
                    System.out.println(loser.get(i).getName() + " lost $" + loser.get(i).getBetAmount() + " this round.");

                }

            }
            pot = 0;

        }
    }

} // end class
