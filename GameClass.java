/**********************************************************
 * Program Name       : GameClass
 * Author             : Robert Short
 * Date               : 3/29/2020
 * Program Description: This program will interact with the
 *    user to create a GameClass object and call the startGame method
 *    to play a game of wheel of fortune.
 *
 * Methods:
 * -------
 * GameClass - creates GameClass objects
 * startGame - starts wheel of fortune
 * playRound - plays a full turn for a player
 * endGame - ends wheel of fortune
 * isAVowel - checks if a character is a vowel
 * menuDisplay - displays various menus to the player
 * displayPlayerStats - displays all player stats
 * wheelValue - returns an integer for the wheel spin
 *
 **********************************************************/

import java.util.*;
import java.io.*;

public class GameClass
{
	//class constants

	//class variables
    private ArrayList<PlayerClass> playerList;     //list of PlayerClass object
    private ArrayList<PhraseClass> gamePhrases;    //list of PhraseClass objects
    private PhraseClass currentPhrase;             //current PhraseClass object
    private WheelClass wheel;                      //wheel object
    private String previousGuesses = "";           //holds all previous
                                                   //guessed letters

   /**********************************************************
    * Method Name       : GameClass
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This is the GameClass constructor for GameClass
    *    objects using inputted fields to define their respective variable.
    *
    * BEGIN PhraseClass
    *    Set playerList = inputted playerList
    *    Set gamePhrases = inputted gamePhrases
    *    Set currentPhrase = first phrase in gamePhrases
    *    Initialize wheel
    * END GameClass
    **********************************************************/

    public GameClass(ArrayList<PhraseClass> iGamePhrases,
                     ArrayList<PlayerClass> iPlayerList)
    {
		playerList = iPlayerList;
		gamePhrases = iGamePhrases;
		currentPhrase = gamePhrases.get(0);
		wheel = new WheelClass();

	}//end GameClass

    /******************************************
     * Method Name        : startGame
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method starte a wheel of fortune game
     *     and increments through a list of players allowing them each
     *     to call the playRound method and play a turn in wheel of fortune
     *
     * BEGIN startGame
     *   Print out welcome message
	 *   WHILE(gamePhrases still has phrases)
	 *       WHILE(it is player 1's turn or player 2's turn or player 3's turn)
	 *		     FOR(every player)
	 *			     IF(it is the current player's turn)
	 *				     Call the spinwheel method and assign it to nextTurn
	 *					 Set the current player's isPlayersTurn = false
	 *					 IF(there is a next turn)
	 *					     IF(the phrase has been revealed)
	 *						     Delete the current phrase from the list
	 *						     Set currentPhrase = next phrase in the list
     *                           Reset previousGuesses
	 *						 END IF
	 *						 Call displayPlayerStats method to display all
	 *						 player stats
	 *					     IF(current player isn't the last player in the list)
	 *				    	    Set the next player's isPlayersTurn = true
	 *					     ELSE
	 *				        	Set the first player's isPlayersTurn = true
	 *					     END IF
	 *				     END IF
	 *			     END IF
	 *		     END FOR
	 *	     END WHILE
	 *   END WHILE
	 *   Call the endGame method to end the game
	 *   Return playerList
     * END startGame
     ******************************************/

	public ArrayList<PlayerClass> startGame() throws InterruptedException
	{
	    //local constants

	    //local variables
	    boolean nextTurn;        //holds next turn truth value


        /***************** Start startGame method ************/

        //print out welcome message
        System.out.print("\n\n\n\n");
        System.out.println(setLeft(46, "Welcome to Wheel of Fortune!"));

	    //while(gamePhrases still has phrases)
	    while(gamePhrases.size() != 0)
	    {
			//while(it is player 1's turn or player 2's turn or player 3's turn)
			while(playerList.get(0).isPlayersTurn ||
				  playerList.get(1).isPlayersTurn ||
				  playerList.get(2).isPlayersTurn)
			{
			    //for(every player)
				for(int i = 0; i < 3; i++)
				{
					//if(it is the current player's turn)
					if(playerList.get(i).isPlayersTurn)
					{
						//call the spinwheel method and assign it to nextTurn
						nextTurn = playRound(playerList.get(i));

						//set the current player's isPlayersTurn = false
						playerList.get(i).isPlayersTurn = false;

						//if(there is a next turn)
						if(nextTurn)
						{
							//if(the phrase has been revealed)
							if(currentPhrase.guessPhrase(currentPhrase.hiddenPhrase))
							{
							    //delete the current phrase from the list
								gamePhrases.remove(0);

							    //set currentPhrase = next phrase in the list
			                    currentPhrase = gamePhrases.get(0);

                                //reset previousGuesses
			                    previousGuesses = "";

							}//end if

							//call displayPlayerStats method to display all
							//player stats
							displayPlayerStats();

						    //if(current player isn't the last player in the list)
						    if(i < 2)
						    {
					    	    //set the next player's isPlayersTurn = true
						        playerList.get(i + 1).isPlayersTurn = true;
						    }

						    //else
						    else
						    {
					        	//set the first player's isPlayersTurn = true
						        playerList.get(0).isPlayersTurn = true;

						    }//end if

					    }//end if

				    }//end if

			    }//end for

		    }//end while

	    }//end while

	    //call the endGame method to end the game
	    endGame();

	    //return playerList
	    return playerList;

	}//end startGame

   /**********************************************************
    * Method Name       : playRound
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method plays a full turn with a player
    *    and returns if the player won the game or not.
    *
    * BEGIN playRound
    *   Set nextTurn = false
	*	WHILE(nextTurn is false)
	*		Initialize scanner
	*		IF(newOptionFlag = true)
	*			IF(player's balance > 250 or
	*			   the current value is Free Play)
	*				Reprompt user with three options
	*			ELSE
	*				Reprompt user with two options
	*			END IF
	*			Set choice = user input
	*			Set counter = 0
	*			Call menuDisplay with counter to display current menu
	*			Print out who's turn it is to spin the wheel
	*			Call the spin method from wheel and assign it to value
	*			Print out value
	*			Set counter = 0
	*		    Call menuDisplay with counter to display current menu
	*		END IF
    *       IF(value contains bankrupt)
	*			Print out the current player went bankrupt
	*			Call bankrupt method
	*	        Set nextTurn = true
	*	    ELSE IF(value contains Lose A Turn)
	*			Print out the current player lost their turn
	*			Set nextTurn = true
	*	    ELSE
	*			IF(newOptionFlag = false)
	*				Set counter = 1
	*				IF(player's round balance > 250 or
	*				   current value is Free Play)
	*					Set counter = 2
	*				END IF
	*			    Call menuDisplay with counter to display current menu
	*				Set choice = user input
	*			END IF
	*			Set newOptionFlag = false
    *           SWITCH(choice)
	*				CASE "3":
	*				    Clear screen
	*				    IF(previousGuesses contains all vowels)
	*						Print out all vowels have been bought message
	*					ELSE
	*						IF(player's round balance < 250
	*						   and value isn't Free Play)
	*							Print out that they're not able to
	*							buy a vowel
	*							Set newOptionFlag = true
	*						ELSE
	*							Print out previously bought vowels
	*							IF(previousGuesses contains "a")
	*								Print out "a"
	*						    END IF
	*						    IF(previousGuesses contains "e")
	*								Print out "e"
	*						    END IF
	*						    IF(previousGuesses contains "i")
	*								Print out "i"
	*						    END IF
	*						    IF(previousGuesses contains "o")
	*								Print out "o"
	*						    END IF
	*						    IF(previousGuesses contains "u")
	*								Print out "u"
	*						    END IF
	*						    IF(previousGuesses containts "y")
	*								Print out "y"
	*					        END IF
    *                            Set counter = 0
	*						    Call menuDisplay with counter to display
	*						    current menu
	*						    IF(value is not Free Play)
	*								Take away $250 from the current player
	*					        END IF
	*					        Prompt player to enter a vowel
	*						    Set guessedLetter = next character inputted
	*					        IF(guessedLetter is uppercase)
	*								Set guessedLetter = lowercase
	*						    END IF
    *                           WHILE(previousGuesses contains guessedLetter or
    *                                 guessedLetter is not a vowel)
	*								IF(guessedLetter is not a vowel)
	*									Print out that guessedLetter is not a vowel
    *                                    Prompt player to enter a vowel
	*						    	ELSE IF(previousGuesses contains guessedLetter)
	*									Print out that guessedLetter has been
	*									chosen and reprompt the player
	*						    	END IF
	*						    	Set guessedLetter = next character inputted
	*						    END WHILE
	*						    Clear screen
	*						    Call guessLetter method and assign it to amount
	*						    IF(amount > 0)
	*								Print out hiddenPhrase
	*						        Print out that guessedLetter was in the
	*						        phrase
	*						    ELSE
	*								Print out that guessedLetter was not in
	*								the phrase
	*						        Set nextTurn = true
	*						    END IF
	*						    Add guessedLetter to previousGuesses
	*						END IF
	*				    END IF
    *               END CASE
    *               CASE "1":
	*				    Clear screen
	*				    IF(hiddenPhrase contains "_")
	*						Print out previously guessed letters
	*						Set counter = 0
	*						Call menuDisplay with counter to display current
	*						menu
	*						Prompt the player to enter a letter
    *                       Set guessedLetter = next inputted character
	*						IF(guessedLetter is uppercase)
	*							Set guessedLetter = lowercase
	*						END IF
    *                       WHILE(previousGuesses contains guessedLetter or
    *                             guessedLetter is a vowel)
	*							IF(guessedLetter is a vowel)
	*								Print out that guessedLetter is a vowel
    *                               Prompt the player to enter a letter
	*							ELSE IF(previousGuesses contains guessedletter)
	*								Print out that guessedletter has already
	*								been chosen and reprompt player
	*							END IF
	*							Set guessedLetter = next inputted character
	*						END WHILE
	*						Clear screen
	*						Call guessLetter method and assign it to amount
	*						IF(amount > 0)
    *                           Print out that guessedLetter was in the
    *                           phrase
	*							Call calcRoundBalance to add the funds of the
	*							current player
	*						ELSE
	*							Print out that guessedLetter was not in the
	*							phrase
	*						    Set nextTurn = true
	*						END IF
	*						Add guessedLetter to previousGuesses
	*					ELSE
	*						Set counter = 0
	*						Call menuDisplay method with counter to display
	*						current menu
	*						Prompt the player to guess the phrase
	*					    Set guessedPhrase = next inputted line
	*					    IF(guessedPhrase is correct)
	*							Clear screen
	*							Set counter = 0
	*							Call menuDisplay method with counter to
	*							Display current menu
	*							Print out that guessedPhrase was the
	*							correct phrase and that the current player
	*							wins the round
    *                            Call the calcGameBalance method to update
    *                           the current player's game balance
	*							FOR(every player)
	*						    	Reset their round balance
	*		                    END FOR
	*						ELSE
	*							Print out that guessedPhrase is not
	*							the phrase
	*						END IF
	*						Set nextTurn = true
	*			        END IF
	*			    END CASE
    *               CASE "2":
	*				    Clear screen
	*				    Set counter = 0
	*				    Call menuDisplay with counter to display current menu
	*				    Prompt the player to guess the phrase
	*				    Set guessedPhrase = next inputted line
	*			        IF(guessedPhrase is correct)
	*						Clear screen
	*						Set counter = 0
	*						Call menuDisplay with counter to display
	*						current menu
	*					    Print out that guessedPhrase is the
	*						correct phrase and the current player
	*						wins the round
    *                       Call calcGameBalance method to update the
    *                       current player's game balance
	*					    FOR(every player)
	*							Reset their round balance
	*					    END FOR
	*					ELSE
	*						Print out that guessedPhrase is not correct
	*						phrase
	*					END IF
	*					Set nextTurn = true
	*			    END CASE
    *               DEFAULT:
	*				    Print out that this option is not an option
	*				    Set newOptionFlag = true
	*				END DEFAULT
	*			END SWITCH
    *       END IF
	*   END WHILE
	*   IF(gamePhrases has more than one phrase left)
	*		Return true
	*   ELSE IF(gamePhrases has one phrase left and the current phrase
	*	        has been guessed)
	*		Delete the current phrase from the list
    *       Return false
	*	ELSE
	*		Return true
	*	END IF
    * END playRound
    **********************************************************/

	private boolean playRound(PlayerClass player) throws InterruptedException
	{
		//local constants

		//local variable
		boolean nextTurn;              //holds whether or not the game ends
		String choice = "";            //holds the player's choice from menu
		String value = "";             //holds the spun value
		Scanner scanner;               //scanner for user input
		char guessedLetter;            //holds the guessed letter
		String guessedPhrase;          //holds the guessed phrase
		int counter = 0;               //counter variable for menu display
		int winAmount;                 //holds the amount won for correct guess
		int amount;                    //holds how many letters were revealed
		                               //in hiddenPhrase
		boolean newOptionFlag = false; //holds whether or not the player entered a
		                               //bad option

		/***************** Start playRound method ************/

        //set nextTurn = false
		nextTurn = false;

		// while(nextTurn is false)
		while (!nextTurn)
		{
			//initialize scanner
			scanner = new Scanner(System.in);

			//if(newOptionFlag = true)
			if(newOptionFlag)
			{
				System.out.print("\n");

				//if(player's balance > 250 or
				//   the current value is Free Play)
				if(player.calcRoundBalance(0) > 250 ||
					   wheelValue(value) == 0)
				{
					//reprompt user with three options
					System.out.print(setLeft(46, "Enter an option(1-3): "));
				}

				else
				{
					//reprompt user with two options
					System.out.print(setLeft(46, "Enter an option(1-2): "));

				}//end if

				//set choice = user input
		        choice = scanner.nextLine();

			}

			else
			{
				//set counter = 0
				counter = 0;

				//call menuDisplay with counter to display current menu
				menuDisplay(counter);

				//print out who's turn it is to spin the wheel
				System.out.print("\n");
				System.out.println(setLeft(46, player.name + "'s turn to "
							                 + "spin the wheel!"));

				Thread.sleep(2000);

				//call the spin method from wheel and assign it to value
				value = wheel.spin();

				//print out value
				System.out.println(value + "\n");

				//set counter = 0
			    counter = 0;

			    //call menuDisplay with counter to display current menu
		 	    menuDisplay(counter);

			}//end if

            //if(value contains bankrupt)
		    if (value.contains("Bankrupt"))
		    {
				//print out the current player went bankrupt
				System.out.println(setLeft(46, player.name +
				                          " went Bankrupt! "));

				//call bankrupt method
		        player.bankrupt();

		        //set nextTurn = true
		        nextTurn = true;
		        Thread.sleep(2000);
		    }

		    //else if(value contains Lose A Turn)
		    else if (value.contains("Lose A Turn"))
		    {
				//print out the current player lost their turn
				System.out.println(setLeft(46, player.name +
				                          " lost their turn! "));

				//set nextTurn = true
                nextTurn = true;

                Thread.sleep(2000);
		    }

		    //else
		    else
		    {
				//if(newOptionFlag = false)
                if(!newOptionFlag)
                {
					//set counter = 1
					counter = 1;

					//if(player's round balance > 250 or
					//   current value is Free Play)
					if(player.calcRoundBalance(0) > 250 ||
					   wheelValue(value) == 0)
					{
						//set counter = 2
						counter = 2;

					}//end if

				    //call menuDisplay with counter to display current menu
					menuDisplay(counter);

					//set choice = user input
		            choice = scanner.nextLine();

				}//end if

				//set newOptionFlag = false
				newOptionFlag = false;

                //switch(choice)
		        switch(choice)
		        {
					//case "3":
					case "3":

					    //clear screen
					    cls();

					    //if(previousGuesses contains all vowels)
					    if(previousGuesses.contains("a") &&
					       previousGuesses.contains("e") &&
					       previousGuesses.contains("i") &&
					       previousGuesses.contains("o") &&
					       previousGuesses.contains("u") &&
					       previousGuesses.contains("y"))
					    {
							//print out all vowels have been bought message
							System.out.print("\n\n\n\n");
						    System.out.println(setLeft(46,
							"All Vowels have been bought already!"));

							Thread.sleep(2000);
                            cls();
						}

						//else
						else
						{
							//if(player's round balance < 250
							//   and value isn't Free Play)
							if(player.calcRoundBalance(0) < 250 &&
												   wheelValue(value) != 0)
							{
								//print out that they're not able to
								//buy a vowel
								System.out.println(setLeft(46, "You can't "
								                        + "buy a vowel!"));

								//set newOptionFlag = true
						    	newOptionFlag = true;
							}
							//else
							else
						    {
								//print out previously bought vowels
								System.out.print("Previous Bought Vowels: ");

								//if(previousGuesses contains "a")
							    if(previousGuesses.contains("a"))
							    {
									//print out "a"
								    System.out.print("a");

							    }//end if

							    //if(previousGuesses contains "e")
							    if(previousGuesses.contains("e"))
							    {
									//print out "e"
							    	System.out.print("e");

							    }//end if

							    //if(previousGuesses contains "i")
							    if(previousGuesses.contains("i"))
							    {
									//print out "i"
							    	System.out.print("i");

							    }//end if

							    //if(previousGuesses contains "o")
							    if(previousGuesses.contains("o"))
							    {
									//print out "o"
							    	System.out.print("o");

							    }//end if

							    //if(previousGuesses contains "u")
							    if(previousGuesses.contains("u"))
							    {
									//print out "u"
							    	System.out.print("u");

							    }//end if

							    //if(previousGuesses containts "y")
							    if(previousGuesses.contains("y"))
							    {
									//print out "y"
							    	System.out.print("y");

						        }//end if

						        System.out.print("\n");

                                //set counter = 0
							    counter = 0;

							    //call menuDisplay with counter to display
							    //current menu
							    menuDisplay(counter);

							    //if(value is not Free Play)
							    if(wheelValue(value) > 0)
							    {
									//take away $250 from the current player
							    	player.calcRoundBalance(-250);

						        }//end if

						        //prompt player to enter a vowel
							    System.out.print("\n\n");
							    System.out.print(setLeft(46,
							                    "Enter a vowel: "));

							    //set guessedLetter = next character inputted
						        guessedLetter = scanner.next().charAt(0);

						        //if(guessedLetter is uppercase)
						        if(guessedLetter <= 90)
							    {
									//set guessedLetter = lowercase
						        	guessedLetter += 32;

							    }//end if

                                //while(previousGuesses contains guessedLetter or
                                //      guessedLetter is not a vowel)
						        while(previousGuesses.contains(guessedLetter + "")
						              || !isAVowel(guessedLetter))
							    {
									//if(guessedLetter is not a vowel)
							        if(!isAVowel(guessedLetter))
							    	{
										//print out that guessedLetter is not a vowel
							    		System.out.println(setLeft(46,
							    		guessedLetter + " is not a vowel!"));

                                        //prompt player to enter a vowel
							    		System.out.print(setLeft(46, "\n" +
							    		"Enter a vowel: "));
							    	}

							    	//else if(previousGuesses contains guessedLetter)
							    	else if(previousGuesses.contains(guessedLetter
								                                 + ""))
							    	{
										//print out that guessedLetter has been chosen
										//and reprompt the player
							    		System.out.print(setLeft(46, guessedLetter
							    		+ " has already been chosen!\n")
							    		+ setLeft(46, "Pick another vowel: "));

							    	}//end if

							    	//set guessedLetter = next character inputted
							    	guessedLetter = scanner.next().charAt(0);

							    }//end while

							    //clear screen
							    cls();

							    //call guessLetter method and assign it to amount
							    amount = currentPhrase.guessLetter(guessedLetter);

							    //if(amount > 0)
							    if(amount > 0)
							    {
									//print out hiddenPhrase
							    	System.out.print("\n\n\n\n");
							        System.out.println(setLeft(46,
							               currentPhrase.hiddenPhrase));
							        System.out.print("\n\n");

							        //print out that guessedLetter was in the
							        //phrase
							        System.out.println(setLeft(46,
							        guessedLetter + " was in the phrase!"));

							        Thread.sleep(2000);
                                    cls();
							    }

							    //else
							    else
							    {
									//print out that guessedLetter was not in
									//the phrase
							    	System.out.print("\n\n\n\n");
							    	System.out.println(setLeft(46,
							        guessedLetter + " was not in the phrase!"));

							        //set nextTurn = true
							    	nextTurn = true;

							    }//end if

							    //add guessedLetter to previousGuesses
							    previousGuesses += guessedLetter;

							}//end if

					    }//end if

                        //end case
					    break;

                    //case "1":
					case "1":

					    //clear screen
					    cls();

					    //if(hiddenPhrase contains "_")
					    if(currentPhrase.hiddenPhrase.contains("_"))
					    {
							//print out previously guessed letters
					        System.out.println("Previous Guessed Letters: "
							                  + previousGuesses);

							//set counter = 0
							counter = 0;

							//call menuDisplay with counter to display current
							//menu
							menuDisplay(counter);

							//prompt the player to enter a letter
							System.out.print("\n");
							System.out.print(setLeft(46,
								"Enter a Letter(No Vowels): "));

                            //set guessedLetter = next inputted character
							guessedLetter = scanner.next().charAt(0);

							//if(guessedLetter is uppercase)
							if(guessedLetter <= 90)
							{
								//set guessedLetter = lowercase
								guessedLetter += 32;

							}//end if

                            //while(previousGuesses contains guessedLetter or
                            //      guessedLetter is a vowel)
							while(previousGuesses.contains(guessedLetter + "")
							      || isAVowel(guessedLetter))
							{
								//if(guessedLetter is a vowel)
							    if(isAVowel(guessedLetter))
								{
									//print out that guessedLetter is a vowel
							        System.out.println(setLeft(46,
									guessedLetter + " is a vowel!"));

                                    //prompt the player to enter a letter
                                    System.out.print("\n");
									System.out.print(setLeft(46,
										"Enter a Letter(No Vowels): "));
								}

								//else if(previousGuesses contains guessedletter)
								else if(previousGuesses.contains(guessedLetter
								                                 + ""))
							    {
									//print out that guessedletter has already
									//been chosen and reprompt player
									System.out.print(setLeft(46, guessedLetter
									    + " has already been chosen!\n")
									    + setLeft(46,
									    "Pick another Letter(No Vowels): "));

								}//end if

								//set guessedLetter = next inputted character
								guessedLetter = scanner.next().charAt(0);

							}//end while

							//clear screen
							cls();

							//call guessLetter method and assign it to amount
							amount = currentPhrase.guessLetter(guessedLetter);

							//if(amount > 0)
							if(amount > 0)
							{
                                //print out that guessedLetter was in the
                                //phrase
							    System.out.print("\n\n\n\n");
								System.out.println(setLeft(46,
								guessedLetter + " was in the phrase!"));

								//call calcRoundBalance to add the funds of the
								//current player
								player.calcRoundBalance(wheelValue(value) * amount);
							}

							//else
							else
							{
								//print out that guessedLetter was not in the
								//phrase
								System.out.print("\n\n\n\n");
								System.out.println(setLeft(46,
							       guessedLetter + " was not in the phrase!"));

							    //set nextTurn = true
							    nextTurn = true;

							}//end if

							//add guessedLetter to previousGuesses
							previousGuesses += guessedLetter;

							Thread.sleep(2000);
                            cls();
						}

						//else
						else
						{
							//set counter = 0
							counter = 0;

							//call menuDisplay method with counter to display
							//current menu
							menuDisplay(counter);

							//prompt the player to guess the phrase
                            System.out.print("\n\n");
						    System.out.print(setLeft(46,
						                        "Guess the phrase: "));

						    //set guessedPhrase = next inputted line
						    guessedPhrase = scanner.nextLine();

						    //if(guessedPhrase is correct)
						    if(currentPhrase.guessPhrase(guessedPhrase))
						    {
								//clear screen
								cls();

								//set counter = 0
								counter = 0;

								//call menuDisplay method with counter to
								//display current menu
								menuDisplay(counter);

								//print out that guessedPhrase was the
								//correct phrase and that the current player
								//wins the round
								System.out.print("\n\n");
								System.out.println(setLeft(46,
								 guessedPhrase +
							     " is the correct phrase!") + "\n" +
						        setLeft(46, player.name + " wins the round!" ));

                                //call the calcGameBalance method to update
                                //the current player's game balance
								player.calcGameBalance(player.calcRoundBalance(0));

								//for(every player)
								for(int i = 0; i < 3; i++)
								{
							    	//reset their round balance
									playerList.get(i).bankrupt();

			                    }//end for
							}

							//else
							else
							{
								//print out that guessedPhrase is not
								//the phrase
								System.out.print("\n\n\n\n");
								System.out.println(setLeft(46,
							     guessedPhrase + " is not the phrase!"));

							}//end if

							//set nextTurn = true
							nextTurn = true;

							Thread.sleep(2000);
                            cls();

				        }//end if

				        //end case
					    break;

                    //case "2":
					case "2":

					    //clear screen
					    cls();

					    //set counter = 0
					    counter = 0;

					    //call menuDisplay with counter to display current menu
					    menuDisplay(counter);

					    //prompt the player to guess the phrase
					    System.out.print(setLeft(46, "Guess the phrase: "));

					    //set guessedPhrase = next inputted line
				        guessedPhrase = scanner.nextLine();

				        //if(guessedPhrase is correct)
						if(currentPhrase.guessPhrase(guessedPhrase))
						{
							//clear screen
							cls();

							//set counter = 0
							counter = 0;

							//call menuDisplay with counter to display
							//current menu
							menuDisplay(counter);

							//print out that guessedPhrase is the
							//correct phrase and the current player
							//wins the round
							System.out.print("\n\n");
							System.out.println(setLeft(46,
							 guessedPhrase +
							 " is the correct phrase!") + "\n" +
						     setLeft(46, player.name + " wins the round!" ));

                            //call calcGameBalance method to update the
                            //current player's game balance
							player.calcGameBalance(player.calcRoundBalance(0));

							//for(every player)
							for(int i = 0; i < 3; i++)
							{
								//reset their round balance
								playerList.get(i).bankrupt();

						    }//end for
						}

						//else
						else
						{
							//print out that guessedPhrase is not correct
							//phrase
							System.out.print("\n\n\n\n");
							System.out.println(setLeft(46,
							  guessedPhrase + " is not the phrase!"));

						}//end if

						//set nextTurn = true
						nextTurn = true;

						Thread.sleep(2000);
						cls();

						//end case
					    break;

                    //default:
					default:

					    //print out that this option is not an option
					    System.out.println(setLeft(46, choice +
					    " is not an option!"));

					    //set newOptionFlag = true
					    newOptionFlag = true;

					    //end default
		                break;

				}//end switch

            }//end if

	    }//end while

	    //if(gamePhrases has more than one phrase left)
        if(gamePhrases.size() > 1)
        {
			//return true
			return true;
		}

		//else if(gamePhrases has one phrase left and the current phrase
		//        has been guessed)
		else if(gamePhrases.size() == 1 &&
		currentPhrase.guessPhrase(currentPhrase.hiddenPhrase))
		{
			//delete the current phrase from the list
			gamePhrases.remove(0);

            //return false
			return false;
		}

		//else
		else
		{
			//return true
			return true;

		}//end if

	}//end playRound

   /**********************************************************
    * Method Name       : isAVowel
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method checks if a character is
    *    a vowel and returns a boolean result
    *
    * BEGIN isAVowel
    *   IF(inputted character is a vowel)
	*	    Set vowelCheck = true
	*   ELSE
	*	    Set vowelCheck = false
	*	END IF
    *   Return vowelCheck
    * END isAVowel
    **********************************************************/

	private boolean isAVowel(char vowel)
	{
		//local constants

		//local variables
		boolean vowelCheck;                 //true if the char is a vowel
		String vowelString = vowel + "";    //converts the char into a String

		/***************** Start isAVowel method ************/

        //if(inputted character is a vowel)
		if(vowelString.compareToIgnoreCase("a") == 0 ||
		   vowelString.compareToIgnoreCase("e") == 0 ||
		   vowelString.compareToIgnoreCase("i") == 0 ||
		   vowelString.compareToIgnoreCase("o") == 0 ||
		   vowelString.compareToIgnoreCase("u") == 0 ||
		   vowelString.compareToIgnoreCase("y") == 0)
		{
            //set vowelCheck = true
		    vowelCheck = true;
		}

		//else
		else
		{
			//set vowelCheck = false
			vowelCheck = false;

		}//end if

        //return vowelCheck
		return vowelCheck;

	}//end isAVowel

   /**********************************************************
    * Method Name       : displayPlayerStats
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method displays the name, round balance,
    *    game balance, and total balance for every player in playerList
    *
    * BEGIN displayPlayerStats
    *   Clear screen
	*	FOR(every player in playerList)
	*		Print out their name, round balance,
	*		game balance, and total balance
	*	END FOR
    * END displayPlayerStats
    **********************************************************/

	private void displayPlayerStats() throws InterruptedException
	{
		//local constants

		//local variables

		/***************** Start displayPlayerStats method ************/

		//clear screen
		cls();

		//for(every player in playerList)
		for (PlayerClass player : playerList)
		{
			//print out their name, round balance,
			//game balance, and total balance
		    System.out.print(setLeft(10, player.name));
		    System.out.print(setLeft(10, "Round Balance: " +
                    "$" + player.calcRoundBalance(0)));
            System.out.print(setLeft(10, " Game Balance: " +
		                    "$" + player.calcGameBalance(0)));
		    System.out.print(setLeft(10, "Total Balance: " +
                    "$" + player.calcTotalBalance(0)));
            System.out.print("\n");

	    }//end for

		Thread.sleep(2000);
		cls();

	}//end displayPlayerStats

   /**********************************************************
    * Method Name       : wheelValue
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method returns the integer value of the
    *     formatted string of the wheel spin
    *
    * BEGIN wheelValue
    *   IF(value contains "$500")
	*		Set newValue = 500
	*	ELSE IF(value contains "$300")
	*		Set newValue = 300
	*	ELSE IF(value contains "$3500")
	*		Set newValue = 3500
	*	ELSE IF(value contains "$900")
	*		Set newValue = 900
	*   ELSE IF(value contains "$700")
	*		Set newValue = 700
	*	ELSE IF(value contains "$1000000")
	*		Set newValue = 1000000
	*	ELSE IF(value contains "$1000")
	*		Set newValue = 1000
	*	ELSE IF(value contains "$800")
	*		Set newValue = 800
	*	ELSE IF(value contains "$400")
	*		Set newValue = 400
	*	ELSE IF(value contains "$600")
	*		Set newValue = 600
	*	ELSE IF(value contains "$350")
	*		Set newValue = 350
	*	ELSE IF(value contains "$650")
	*		Set newValue = 650
	*	ELSE IF(value contains "Free PLay")
	*		Set newValue = 0
	*	ELSE IF(value contains "$450")
	*		Set newValue = 450
	*	END IF
    *   Return newValue
    * END wheelValue
    **********************************************************/

    private int wheelValue(String value)
    {
		//local constants

		//local variables
		int newValue = 0;    //holds the integer value of the wheel spin

		/***************** Start wheelValue method ************/

        //if(value contains "$500")
		if(value.contains("$500"))
		{
			//set newValue = 500
			newValue = 500;
		}

		//else if(value contains "$300")
		else if(value.contains("$300"))
		{
			//set newValue = 300
			newValue = 300;
		}

		//else if(value contains "$3500")
		else if(value.contains("$3500"))
		{
			//set newValue = 3500
			newValue = 3500;
		}

		//else if(value contains "$900")
		else if(value.contains("$900"))
		{
			//set newValue = 900
			newValue = 900;
		}

		//else if(value contains "$700")
		else if(value.contains("$700"))
		{
			//set newValue = 700
			newValue = 700;
		}

		//else if(value contains "$1000000")
		else if(value.contains("$1000000"))
	    {
			//set newValue = 1000000
	    	newValue = 1000000;
		}

		//else if(value contains "$1000")
		else if(value.contains("$1000"))
		{
			//set newValue = 1000
			newValue = 1000;
		}

		//else if(value contains "$800")
		else if(value.contains("$800"))
		{
			//set newValue = 800
			newValue = 800;
		}

		//else if(value contains "$400")
		else if(value.contains("$400"))
		{
			//set newValue = 400
			newValue = 400;
		}

		//else if(value contains "$600")
		else if(value.contains("$600"))
		{
			//set newValue = 600
			newValue = 600;
		}

		//else if(value contains "$350")
		else if(value.contains("$350"))
		{
			//set newValue = 350
			newValue = 350;
		}

		//else if(value contains "$650")
		else if(value.contains("$650"))
		{
			//set newValue = 650
			newValue = 650;
		}

		//else if(value contains "Free PLay")
		else if(value.contains("Free Play"))
		{
			//set newValue = 0
			newValue = 0;
		}

		//else if(value contains "$450")
		else if(value.contains("$450"))
		{
			//set newValue = 450
			newValue = 450;

		}//end if

        //return newValue
		return newValue;

	}//end wheelValue

   /**********************************************************
    * Method Name       : menuDisplay
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method displays various menus based on an
    *     inputted integer
    *
    * BEGIN menuDisplay
    *   SWITCH(counter)
	*		CASE 0:
	*		    Print out the phrase with the description
	*		END CASE
	*	    CASE 1:
	*	        Print out two option menu and prompt the player
	*		    to enter an option
	*	    END CASE
	*		CASE 2:
	*		    Print out three option menu and prompt the player
	*		    to enter an option
	*		END CASE
	*	    DEFAULT:
	*			Print out that this isn't a menu option
	*		END DEFAULT
	*	END SWITCH
    * END menuDisplay
    **********************************************************/

	private void menuDisplay(int counter)
	{
		//local constants

		//local variables

		/***************** Start menuDisplay method ************/

		//switch(counter)
		switch(counter)
		{
			//case 0:
			case 0:

			    //print out the phrase with the description
			    System.out.print("\n\n\n");
			    System.out.println(setLeft(46,
			                      currentPhrase.hiddenPhrase));
                System.out.print("\n");
			    System.out.println(
			    setLeft(46, "Description: " + currentPhrase.description));

			    //end case
			    break;

            //case 1:
			case 1:

			    //print out two option menu and prompt the player
			    //to enter an option
			    System.out.print("\n");
			    System.out.println(setLeft(46, "1.) Guess A Letter"));
				System.out.println(setLeft(46, "2.) Guess The Phrase"));
				System.out.print("\n");
				System.out.print(setLeft(46, "Enter an option(1-2): "));

				//end case
			    break;

			//case 2:
			case 2:

			    //print out three option menu and prompt the player
			    //to enter an option
			    System.out.print("\n");
			    System.out.println(setLeft(46, "1.) Guess A Letter"));
				System.out.println(setLeft(46, "2.) Guess The Phrase"));
				System.out.println(setLeft(46, "3.) Buy a Vowel"));
				System.out.print("\n");
				System.out.print(setLeft(46, "Enter an option(1-3): "));

				//end case
			    break;

            //default:
			default:

			    //print out that this isn't a menu option
			    System.out.println("\n\n\n\n");
			    System.out.println(setLeft(46, "Not a menu option!"));

			    //end default
			    break;

		}//end switch

	}//end menuDisplay

   /**********************************************************
    * Method Name       : endGame
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method displays the winner of the game
    *     and asks the user if they want to play another game with the same
    *     players
    *
    * BEGIN endGame
    *   FOR(every player)
	*		Set player = the current position in playerList
	*		Call calcTotalBalance with the player's game balance
	*		Set bal = 2 * player's game balance
	*		IF(player's game balance > highest)
	*			Set highest = player's game balance
	*		    Set winner = player's name
	*	    END IF
	*		Reset player's game balance
    *    END FOR
	*	Print out the winner and the winner's game balance
	*	Prompt user for same players
	*	Set option = user input
	*   Clear screen
	*	IF(option is the same players option)
	*	    Set playerList = null
	*	END IF
    * END endGame
    **********************************************************/

	private void endGame()
	{
	    //local constants

	    //local variables
        Scanner scanner = new Scanner(System.in);    //scanner for input
        PlayerClass player;                          //holds player from
                                                     //playerList
        String option;                               //holds same players
                                                     //option
        String winner = "";                          //holds winner's name
        int highest = 0;                             //hold winner's game
                                                     //balance
        int bal;                                     //integer that is
                                                     //2 * player's game balance

        /***************** Start endGame method ************/

        //for(every player)
        for(int i = 0; i < 3; i++)
        {
			//set player = the current position in playerList
			player = playerList.get(i);

			//call calcTotalBalance with the player's game balance
			player.calcTotalBalance(player.calcGameBalance(0));

			//set bal = 2 * player's game balance
			bal = player.calcGameBalance(0) - (2 * player.calcGameBalance(0));

			//if(player's game balance > highest)
			if(player.calcGameBalance(0) > highest)
			{
				//set highest = player's game balance
				highest = player.calcGameBalance(0);

				//set winner = player's name
				winner = player.name;

			}//end if

			//reset player's game balance
			player.calcGameBalance(bal);

		}//end for

		System.out.print("\n\n");

        //print out the winner and the winner's game balance
        System.out.println(setLeft(46, winner +
              " won the game with a balance of: $" + highest));

        System.out.print("\n\n");

        //prompt user for same players
        System.out.print(setLeft(46,"Would you like to play another game " +
                                      "with the same players?\n") +
                           setLeft(46,"(Type 1 and press Enter for yes, " +
                                      "or press the Enter key for no): "));

        //set option = user input
        option = scanner.nextLine();

        //clear screen
        cls();

        //if(option is the same players option)
        if(option.compareTo("1") != 0)
        {
			//set playerList = null
			playerList = null;

		}//end if

    }//end endGame

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

    private String setLeft(int whitespaces, String word)
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
     * Author             : Gabriel Mercado
     * Date               : 3/13/2020
     * Course/Section     : CSC-264-501
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

    private void cls()
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

}//end GameClass