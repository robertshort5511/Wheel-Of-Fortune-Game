/**********************************************************
 * Program Name       : GameClassDriver
 * Author             : Robert Short
 * Date               : 3/29/2020
 * Program Description: This program will interact with the
 *    user to create a GameClass object and call the startGame method
 *    to play a game of wheel of fortune.
 *
 * Methods:
 * -------
 * main - instantiates a GameClass object and calls the startGame method
 *        to start a game of wheel of fortune
 * getPlayers - creates a list of PlayerClass object
 * getRandomPhrases - returns a list of random PhraseClass objects
 *
 **********************************************************/

import java.util.*;
import java.io.*;

public class GameClassDriver
{
	//class constants

	//class variables


    /******************************************
     * Method Name        : main
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method instantiates a GameClass
     *     object and calls the startGame method, with the list
     *     of players and the random phrase.  Also is responsible
     *     for checking if the user wants to play another game with
     *     the same players or just play a new game.
     *
     * BEGIN main
     *   Set phraseList = null
     *   Prompt user for file name
     *   Set fileName = user input
     *   WHILE(phraseList is null)
	 *		Instantiate a PhraseListClass object with fileName
	 *		and assign it to phraseList
     *      IF(phraseList is still null)
	 *			Set fileName = user input
	 *		END IF
	 *	END WHILE
	 *	Clear screen
     *  Set newGame = "1"
     *  WHILE(newGame == "1")
	 *		Initialize playerlist
	 *		Call the getPlayers method with playerList and scanner to prompt
	 *      user for players and assign it to the playerList list
     *      Initialize gamePhrases
	 *		WHILE(playerList is not null)
	 *			Call the getRandomPhrases method and assign it to
	 *          gamePhrases
     *          IF(gamePhrases is not null)
	 *				Set the first player's isPlayersTurn = true
	 *				Instantiate a GameClass object with gamePhrases
	 *				and playerList and call the startGame method to
	 *				play a game of wheel of fortune and assign it
	 *				to playerList
	 *			ELSE
	 *				Print out that there is not enough phrases left for another
	 *              game
	 *				Set playerList = null
	 *			END IF
	 *		END WHILE
     *      IF(gamePhrases is not null)
	 *			Prompt user for new game option with new players
	 *			Set newGame = user input
	 *		ELSE
	 *			Set newGame = "0"
	 *	    END IF
	 *	    Clear screen
	 *  END WHILE
     * END main
     ******************************************/

    public static void main(String[] args) throws InterruptedException
    {
        //local constants

        //local variables
        ArrayList<PlayerClass> playerList;   //list of players
        ArrayList<PhraseClass> phraseList;   //list of all possible phrases
        ArrayList<PhraseClass> gamePhrases;  //list of phrases for the game
        PhraseClass currentPhrase;           //holds the returned random phrase
        String newGame;                      //holds input for new game option
        Scanner s = new Scanner(System.in);  //scanner for user input
        String fileName;                     //file name

        /***************** Start main method ************/

        //set phraseList = null
        phraseList = null;

        //prompt user for file name
        System.out.println("\n\n");
        System.out.print(setLeft(46,"Enter the file of phrases: "));

        //set fileName = user input
        fileName = s.nextLine();

        //while(phraseList is null)
        while(phraseList == null)
        {
			//instantiate a PhraseListClass object with fileName
			//and assign it to phraseList
			phraseList = (new PhraseListClass(fileName)).phraseList;

            //if(phraseList is still null)
			if(phraseList == null)
			{
				//set fileName = user input
                fileName = s.nextLine();

			}//end if

		}//end while

		//clear screen
        cls();

        //set newGame = "1"
        newGame = "1";

        //while(newGame == "1")
        while(newGame.compareToIgnoreCase("1") == 0)
        {
			//initialize playerlist
	        playerList = new ArrayList<PlayerClass>();

			//call the getPlayers method with the scanner to prompt user for
			//players and assign it to the playerList list
			playerList = getPlayers(playerList, s);

            //initialize gamePhrases
		    gamePhrases = new ArrayList<PhraseClass>();

			//while(playerList is not null)
			while(playerList != null)
			{
				//call the getRandomPhrases method and assign it to
	            //gamePhrases
				gamePhrases = getRandomPhrases(phraseList, gamePhrases);

                //if(gamePhrases is not null)
		        if(gamePhrases != null)
		        {
					//set the first player's isPlayersTurn = true
					playerList.get(0).isPlayersTurn = true;

					//instantiate a GameClass object with gamePhrases
					//and playerList and call the startGame method to
					//play a game of wheel of fortune and assign it
					//to playerList
					playerList = (new GameClass(gamePhrases, playerList)).startGame();
				}

				//else
				else
				{
					//print out that there is not enough phrases left for another game
					System.out.print("\n\n\n\n");
					System.out.println(setLeft(46, "Not enough phrases left for another game!"));

					//set playerList = null
					playerList = null;

					Thread.sleep(2000);

				}//end if

			}//end while

            //if(gamePhrases is not null)
			if(gamePhrases != null)
			{
				//prompt user for new game option with new players
				System.out.print(setLeft(20, "Type 1 and press enter to play"
							           + " another game with new players, or"
							           + " press enter to quit: "));

				//set newGame = user input
			    newGame = s.nextLine();
			}

			//else
			else
			{
				//set newGame = "0"
				newGame = "0";

		    }//end if

		    //clear screen
			cls();

	    }//end while

    }//end main

   /**********************************************************
    * Method Name       : getPlayers
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method asks the user to input the player names
    *   then adds new PlayerClass objects to playerList with the inputted name
    *
    * BEGIN getPlayers
	*	FOR(every player)
    *       Prompt user for the player's name
	*	    Set name = the inputted line
	*	    IF(this is the first player)
	*			Add the player to the playerList with isPlayersTurn = true
	*	    ELSE
	*			Add the player to the playerList with isPlayersTurn = false
	*		END IF
	*	END FOR
	*	Clear screen
	*	Return playerList
    * END getPlayers
    **********************************************************/

    public static ArrayList<PlayerClass> getPlayers(ArrayList<PlayerClass> playerList,
                                             Scanner scanner)
    {
		//local constants

		//local variables
		String name;        //the name of the player

		/***************** Start getPlayers method ************/

        System.out.println("\n\n");

		//for(every player)
		for(int i = 1; i < 4; i++)
		{
            //prompt user for the player's name
		    System.out.print(setLeft(46, "Enter the player " + i
		                                 + "'s name: "));

		    //set name = the inputted line
		    name = scanner.nextLine();

		    //if(this is the first player)
		    if(i == 1)
		    {
				//add the player to the playerList with isPlayersTurn = true
		        playerList.add(new PlayerClass(name, true, 0, 0, 0));
		    }

		    //else
		    else
		    {
				//add the player to the playerList with isPlayersTurn = false
				playerList.add(new PlayerClass(name, false, 0, 0, 0));

			}//end if

		}//end for

		//clear screen
		cls();

		//return playerList
		return playerList;

	}//end getPlayers

   /**********************************************************
    * Method Name       : getRandomPhrases
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method returns an array of PhraseClass objects
    *    with a PhraseClass object for each difficulty possible.
    *
    * BEGIN getRandomPhrases
	*	WHILE((an easy phrase hasn't been added or
	*	      a medium phrase hasn't been added or
	*	      a hard phrase hasn't been added) and
	*	      i < phraseList.size())
	*		IF(current phrase's difficulty is easy and an easy
	*		   phrase hasn't been added yet)
	*			Add the current phrase to the gamePhrases list
	*			Set the easy phrase flag = true
	*		ELSE IF(current phrase's difficulty is medium and a medium
	*		        phrase hasn't been added yet)
	*			Add the current phrase to the gamePhrases list
	*			Set the medium phrase flag = true
	*		ELSE IF(current phrase's difficulty is hard and a hard
	*		        phrase hasn't been added yet)
	*			Add the current phrase to the gamePhrases list
	*			Set the hard phrase flag = true
	*		END IF
    *       Increment i
	*	END WHILE
	*	IF(an easy phrase hasn't been added or
	*	   a medium phrase hasn't been added or
	*	   a hard phrase hasn't been added)
	*	    Return null
	*	END IF
	*	Return gamePhrases
    * END getRandomPhrases
    **********************************************************/

	public static ArrayList<PhraseClass> getRandomPhrases(
		   ArrayList<PhraseClass> phraseList, ArrayList<PhraseClass> gamePhrases)
	{
		//local constants

		//local variables
		boolean easyFlag = false;              //easy phrase flag
		boolean mediumFlag = false;            //medium phrase flag
		boolean hardFlag = false;              //hard phrase flag
		int i = 0;                             //incrementer for phraseList

		/***************** Start getRandomPhrases method ************/

		//while((an easy phrase hasn't been added or
		//      a medium phrase hasn't been added or
		//      a hard phrase hasn't been added) and
		//      i < phraseList.size())
		while((!easyFlag || !mediumFlag || !hardFlag) && i < phraseList.size())
		{
			//if(current phrase's difficulty is easy and an easy
			//   phrase hasn't been added yet)
			if(phraseList.get(i).difficulty.compareToIgnoreCase("Easy")
			   == 0 && !easyFlag)
			{
				//add the current phrase to the gamePhrases list
				gamePhrases.add(phraseList.remove(i));

				//set the easy phrase flag = true
				easyFlag = true;
			}

			//else if(current phrase's difficulty is medium and a medium
			//        phrase hasn't been added yet)
			else if(phraseList.get(i).difficulty.compareToIgnoreCase("Medium")
			        == 0 && !mediumFlag)
			{
				//add the current phrase to the gamePhrases list
				gamePhrases.add(phraseList.remove(i));

				//set the medium phrase flag = true
				mediumFlag = true;
			}

			//else if(current phrase's difficulty is hard and a hard
			//        phrase hasn't been added yet)
			else if(phraseList.get(i).difficulty.compareToIgnoreCase("Hard")
			        == 0 && !hardFlag)
			{
				//add the current phrase to the gamePhrases list
				gamePhrases.add(phraseList.remove(i));

				//set the hard phrase flag = true
				hardFlag = true;

			}//end if

            //increment i
			i++;

		}//end while

		//if(an easy phrase hasn't been added or
		//      a medium phrase hasn't been added or
		//      a hard phrase hasn't been added)
		if(!easyFlag || !mediumFlag || !hardFlag)
		{
			//return null
			return null;

		}//end if

		//return gamePhrases
		return gamePhrases;

	}//end getRandomPhrases

    /**********************************************
	 * Method Name        : setLeft
	 * Author             : Robert Short
	 * Date               : 3/13/2020
	 * Method Description : This method aligns the output.
	 * BEGIN setLeft
	 *     FOR every iteration from j - whitespaces
	 *         Add a whitespace to a temporary string
	 *     END FOR
	 *     Print out word
	 * END setLeft
	 **********************************************/

	public static String setLeft(int whitespaces, String word)
	{
		//local constants

		//local variables
        String temp = "";

        /***************** Start setLeft method ************/

		//for every iteration from j - whitespaces
		for(int j = 0; j < whitespaces; j ++)
		{
			//add a whitespace to a temporary string
			temp += " ";

		}//end for

		//print the word out
		return temp + word;

	}//end setLeft

     /**********************************************
     * Method Name        : cls
     * Author             : Robert Short
     * Date               : 3/13/2020
     * Method Description : This method clears the screen.
     *
     * BEGIN cls
     *     TRY
     *	       Create a new processbuilder for clear screen method
     *	   END TRY
     *     CATCH(if the process builder can't be built)
     *	       Print out the error
     *	   END CATCH
     * END cls
     **********************************************/

    private static void cls()
    {
        //local constants

	    //local variables


	    /***************** Start cls method ************/

	    try
	    {
	       //create a new process builder for clear screen method
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

	    }//end try

	    //catch if the process builder can't be built
	    catch (Exception E)
	    {
	        //print out error
	        System.out.println(E);

	    }//end catch

    }//end cls

}//end GameClassDriver