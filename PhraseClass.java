/**********************************************************
 * Program Name   : PhraseClass
 * Author         : Robert Short
 * Date           : 3/29/2020
 * Program Description: This program creates PhraseClass objects
 *     from inputted fields to define their respective variables.
 *
 * Methods:
 * -------
 * PhraseClass - creates PhraseClass objects with user inputted fields
 * guessPhrase - checks if a guessed phrase = correctPhrase
 * guessLetter - checks if a guessed letter is in hiddenPhrase
 *
 **********************************************************/

import java.util.*;
import java.io.*;

public class PhraseClass
{
    //class constants

    //class variables
    private String correctPhrase;         //the correct phrase
    protected String hiddenPhrase;        //the current user phrase
    protected String difficulty;          //the difficulty of the phrase
    protected String description;         //the description of the phrase

   /**********************************************************
    * Method Name       : PhraseClass
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This is the PhraseClass constructor for PhraseClass
    *    objects using inputted fields to define their respective variable.
    *
    * BEGIN PhraseClass
    *    Set correctPhrase = inputted phrase
    *    Set difficulty = inputted difficulty
    *    Set description = inputted description
    *    Set hiddenPhrase = ""
    *    FOR(the length of correctPhrase)
    *        IF(the current character is not a space)
	*		     Set hiddenPhrase = hiddenPhrase + "_"
	*        ELSE
	*		     Set hiddenPhrase = hiddenPhrase + " "
    *        END IF
    *    END FOR
    * END PhraseClass
    **********************************************************/

    public PhraseClass(String phrase, String iDescription, String iDifficulty)
    {
        correctPhrase = phrase;
        description = iDescription;
        difficulty = iDifficulty;
        hiddenPhrase = "";

        //for(the length of correctPhrase)
        for (int i = 0; i < correctPhrase.length(); i++)
        {
			//if(the current character is not a space)
			if (correctPhrase.charAt(i) != ' ')
			{
			    //set hiddenPhrase = hiddenPhrase + "_"
			    hiddenPhrase += "_";
			}
			//else
			else
			{
			    //set hiddenPhrase = hiddenPhrase + " "
			    hiddenPhrase += " ";

            }//end if

        }//end for

    }//end PhraseClass

    public boolean guessPhrase(String guess)
	{
		//local constants

	    //local variables
	    boolean isCorrect;        //holds the truth value of the comparison
	                              //of the guess and correctPhrase

		/***************** Start guessPhrase method ************/

		//Set isCorrect = false
		isCorrect = false;

	    //if(guess.compareToIgnoreCase(correctPhrase) == 0)
	    if (guess.compareToIgnoreCase(correctPhrase) == 0)
	    {
			//Set isCorrect = true
			isCorrect = true;

			//Set hiddenPhrase = correctPhrase
	        hiddenPhrase = correctPhrase;

	    }//end if

	    //return isCorrect
	    return isCorrect;

	}//end guessPhrase

	public int guessLetter(char letter)
	{
		//local constants

		//local variables
	    int isFound;             //holds how many times the letter was found
	    String letterString;     //holds the letter variable in String form
	    String currentLetter;    //holds the current letter in correctPhrase
	    String firstPart;        //holds the first part of hiddenPhrase
	    String secondPart;       //holds the second part of hiddenPhrase

		/***************** Start guessLetter method ************/

		//set isFound = 0
	    isFound = 0;

        //for(the length of correctPhrase)
	    for (int i = 0; i < correctPhrase.length(); i++)
	    {
			//set letterString = letter = ""
			letterString = letter + "";

			//set currentLetter
			currentLetter = correctPhrase.charAt(i) + "";

			//if(the guessed letter = the current letter in correctPhrase)
	        if (letterString.compareToIgnoreCase(currentLetter) == 0)
	        {
			    //increment isFound
	            isFound++;

	            //set firstPart = the first part of hiddenPhrase before the
	            //letter
	            firstPart = hiddenPhrase.substring(0, i);

	            //set secondPart = the second part of hiddenPhrase after the
	            //letter
	            secondPart = hiddenPhrase.substring(i+1,
	                                                hiddenPhrase.length());

	            //set hiddenPhrase = firstPart + letter + secondPart
	            hiddenPhrase = firstPart + letter + secondPart;

	        }//end if

	    }//end for

	    //return isFound
	    return isFound;

    }//end guessLetter

}//end PhraseClass