/**********************************************************
 * Program Name   : PhraseListClass
 * Author         : Robert Short
 * Date           : 3/29/2020
 * Program Description: This program will read a .txt file of phrases
 *      and store them into an arrayList of PhraseClass objects.
 *
 * Methods:
 * -------
 * PhraseListClass - reads in phrases from a .txt file and adds them to an
 *                   arrayList of PhraseClass objects
 *
 **********************************************************/

import java.io.*;
import java.util.*;

public class PhraseListClass
{
    //class constants

    //class varibles
    ArrayList<PhraseClass> phraseList;     //list of PhraseClass objects

    /**********************************************************
    * Method Name       : PhraseListClass
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This is the PhraseListClass constructor,
    *    that takes in a file name and returns a list of PhraseClass objects.
    *
    * BEGIN PhraseListClass
    *   TRY
    *	    Set input = a file object using the user inputted string
    *	    Set fr = a file reader object using the input file object
    *	    Set br = a buffered reader object using the fr file reader object
    *	    Set line = the current line from br
    *	    Initialize phraseList
    *	    WHILE(line is not null)
	*			Set st = a string tokenizer object created with line
	*		    Add a new PhraseClass object with the tokens in the tokenizer
	*		    to phraseList
	*		    Set line = the current line from br
	*       END WHILE
    *       Close br
	*   END TRY
	*   CATCH(Exception e)
    *       IF(e instanceof FileNotFoundException)
	*			Print out that the file doesn't exist and reprompt
	*			the user for another file
	*		ELSE IF(e instanceof IOException)
	*			Print out that the file was bad and reprompt the user
	*			for another file
	*		END IF
	*   END CATCH
    * END PhraseListClass
    **********************************************************/

    public PhraseListClass(String fileName)
    {
		//local constants

		//local variables
    	File input;            //file object
    	FileReader fr;         //file reader object created with input
    	BufferedReader br;     //buffered reader object created with fr
    	String line;           //current line of the file from br
    	StringTokenizer st;    //tokenizer object created with line

    	/***************** Start PhraseListClass method ************/

        //try
    	try
    	{
    	    //set input = a file object using the user inputted string
    	    input = new File (fileName);

    	    //set fr = a file reader object using the input file object
    	    fr = new FileReader(input);

    	    //set br = a buffered reader object using the fr file reader object
    	    br = new BufferedReader(fr);

    	    //set line = the current line from br
    	    line = br.readLine();

    	    //initialize phraseList
    	    phraseList = new ArrayList<PhraseClass>();

    	    //while(line is not null)
    	    while(line != null)
    	    {
				//set st = a string tokenizer object created with line
			    st = new StringTokenizer(line, ",");

			    //add a new PhraseClass object with the tokens in the tokenizer
			    //to phraseList
			    phraseList.add(new PhraseClass(st.nextToken(), st.nextToken(),
			                                   st.nextToken()));

			    //set line = the current line from br
    	        line = br.readLine();

	        }//end while

            //close br
            br.close();

	    }//end try

	    //catch(Exception e)
	    catch(Exception e)
	    {
            //if(e instanceof FileNotFoundException)
			if(e instanceof FileNotFoundException)
			{
				//print out that the file doesn't exist and reprompt
				//the user for another file
				System.out.println(setLeft(46, "File doesn't exist!"));
				System.out.print(setLeft(46, "Enter another file: "));

			}

			//else if(e instanceof IOException)
			else if(e instanceof IOException)
			{
				//print out that the file was bad and reprompt the user
				//for another file
				System.out.println(setLeft(46, "File was bad!"));
				System.out.print(setLeft(46, "Enter another file: "));

			}//end if

	    }//end catch

    }//end PhraseListClass

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

}//end PhraseListClass