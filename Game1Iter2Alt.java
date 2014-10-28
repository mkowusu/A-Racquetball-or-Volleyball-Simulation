package volleyballRacquetball;

/* 
 * Racquetball/Volleyball Simulation:  
 *    Solution Alternative 1:  Store game, simulation data in object
 *    Iterative Game Approach:  Repeat A serves then B serves until win
 *                              uses helper methods when playing game
 * Copyright(c) 2011 by Henry M. Walker
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Henry M. Walker
 */

// The Math library is needed for its random() function
import java.lang.Math;

/**
 * This class simulates a volleyball or racquetball game
 * between players/teams A and B, based on the probability
 * that A will win a volley.  1000 games are simulated,
 * with the probability of A winning a volley being 
 * 0.40, 0.41, ..., 0.59, 0.60 .
 */
public class Game1Iter2Alt {
    public String game; // either "racquetball" or "volleyball"
    public boolean winByTwo;
    public int numberOfGames;

    /**
     * Constructor specifies whether racquetball or volleyball
     * will be simulated (default racquetball) and 
     * the number of games to be simulated
     * Constructor also uses the racquetball/volleyball 
     * information to determine if players of the game 
     * must win by 2 or 1, respectively.
     */
    public Game1Iter2Alt (String simGame, int simNumGames)
    {
	game = simGame;
	numberOfGames = simNumGames;
   	winByTwo = simGame.equals("volleyball");
    }
    
    public static boolean printGame = true;

    /**
     * Format a probability (a number between 0.0 and 1.0)
     * as a 2-character integer percentage, followed by a
     * "%" character.
     */
    public static String formatPercent (double value)
    {
	String str = "" + Math.round(value * 100.0);
	while (str.length() < 3)
	    str = " " + str;
	return str + "%";
    }
	
    /**
     * Determine if player with first score has won
     * @parms firstScore, secondScore are scores of opposing players/teams
     * @returns true if player with first score has won; false otherwise
     */
    public boolean won (int firstScore, int secondScore)
    {
      // check if first person has won
	return ((firstScore >= 15)
		&& ((! winByTwo)
		    || (firstScore >= secondScore + 2)));
    }

    /**
	 * allows player with first score to continue serving, 
	 * until player has won game or lost volley
	 * @parms probWinVolley is probability that player with first score
	 *                      wins volley
	 *        firstScore, secondScore are scores of the two players/teams
	 * @returns final score of server */
    public int serve (double probWinVolley, int firstScore, int secondScore)
    {   while (Math.random() < probWinVolley)
	    {  firstScore++;
	       if (won (firstScore, secondScore))
		   break;
	    }
	return firstScore;
    }

    /**
     * Play one game of racquetball or volleyball to conclusion
     * @parms  probWinVolley specifies the likelihood the server wins a volley
     * @returns winner of game: either "A" or "B"
     */
    public String playUntilWin (double probWinVolley) 
    {
	//  start at 0-0
	int AScore = 0;
	int BScore = 0;

	// continue indefinitely 
	//     (until A wins after A's serve or B wins after B's serve)
	while (true)
	    {
		// A serves
		AScore = serve (probWinVolley, AScore, BScore);
		// stop if A has won
	    if (won (AScore, BScore))
	    { if (printGame)
			System.out.println (" - Scores (A/B):  " 
					+ AScore 
					+ " / " + BScore);
		return "A";
		}
	    // B serves
	    BScore = serve (1.0 - probWinVolley, BScore, AScore);
	    // stop if B has won
	    if (won (BScore, AScore))
	    { if (printGame)
			System.out.println (" - Scores (A/B):  " 
					+ AScore 
					+ " / " + BScore);  
		return "B";
		}
	} 
    }

    /**
     * Run simulation of 1000 games for probability of "A" winning
     *   a volley covering the range 0.40, 0.41, ..., 0.59, 0.60.
     * For each probability of "A" winning,
     *   simulate games with Player/Team A always serving first
     *   print one line with the percentage of volleys won by A and B 
     *     and percentage of games won by A and B
     */
    public void simulateGames ()
    {   // print headings
	System.out.println ("\nSimulation of " + game
			    + " based on " + numberOfGames + " games");
	System.out.println ("Must win by 2:  " + winByTwo);
	System.out.println ();
	System.out.println ("    Probabilities         Percentage");
	System.out.println (" for winning volley        of Wins");
	System.out.println ("     A       B            A        B");
        System.out.println ();
	
	// Simulate games for 40% to 60% probabilities for A
	for (int prob40To60 = 40; prob40To60 <= 60; prob40To60++)
	    {
	      double probWinVolley = prob40To60 / 100.0;
 
	      // Simulate games for a given probability
	      int AWins = 0;  // at first neither A nor B has won any games
	      int BWins = 0;
	      for (int i = 0; i < numberOfGames; i++)
		  { // tally winner of game
		    if (playUntilWin (probWinVolley).equals("A"))
			AWins++;
		    else
			BWins++;
		  }
	      System.out.println ("   "
			  + formatPercent(probWinVolley)   + "    " 
			  + formatPercent(1-probWinVolley) + "         "
			  + formatPercent(((double) AWins) / numberOfGames)
			  + "     "
			  + formatPercent(((double) BWins) / numberOfGames));
	    }
	System.out.println ("\nEnd of Simulation\n");
    }

	/**
	 * @parm args is a command-line argument specifies the type of game
	 *       "racquetball" indicates server need not win by 2 points
	 *       "volleyball" indicates server must win by 2 points
	 *       no parameter or other parameter defaults to "racquetball"
	 */
    public static void main (String [] args)
    {
	Game1Iter2Alt rac1 = new Game1Iter2Alt ("racquetball", 1000);
	rac1.simulateGames() ;

	Game1Iter2Alt rac2 = new Game1Iter2Alt ("volleyball", 500);
	rac2.simulateGames();
    }	
}
