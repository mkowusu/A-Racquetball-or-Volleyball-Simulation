package volleyballRacquetball;

public class Game {
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
	    public Game (String simGame, int simNumGames)
	    {   game = simGame;
	        numberOfGames = simNumGames;
	        winByTwo = simGame.equals("volleyball");
	    }
	    
	    /**                         
	     * Format a probability (a number between 0.0 and 1.0)   
	     * as a 2-character integer percentage, followed by a 
	     * "%" character.
	     */
	     public static String formatPercent (double value)
	     {   String str = "" + Math.round(value * 100.0);
	         while (str.length() < 3)
	             str = " " + str;
	         return str + "%";
	     }
	     
			private Object playUntilWin(String string, String string2,
					double probWinVolley, int i, int j) {
				// TODO Auto-generated method stub
				return "A";
			}

	     
	    public void simulateGames(){
	        // print headings             
	        System.out.println ("\nSimulation of " + game
	                        + " based on " + numberOfGames + " games");
	        System.out.println ("Must win by 2:  " + winByTwo);
	        System.out.println ();
	        System.out.println ("   Probabilities       Percentage");
	        System.out.println ("for winning volley      of Wins");
	        System.out.println ("    A      B           A       B");
	        System.out.println ();
	        // Simulate games for 40% to 60% probabilities for A     
	        for (int prob40To60 = 40; prob40To60 <= 60; prob40To60++)
	          { double probWinVolley = prob40To60 / 100.0;

	            // Simulate games for a given probability     
	            int AWins = 0;  // at first neither A nor B  
	            int BWins = 0;  //   has won any games
	            for (int i = 0; i < numberOfGames; i++)
	               { // tally winner of game   
	                 if (playUntilWin ("A", "B", probWinVolley, 
	                                   0, 0).equals("A"))
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
	    
	    public static void main (String Args []) {
	    	Game rac1 = new Game ("racquetball", 1000);
	    	rac1.simulateGames() ;

	    	Game vol = new Game ("volleyball", 500);
	    	vol.simulateGames();

	    }
	    
}
