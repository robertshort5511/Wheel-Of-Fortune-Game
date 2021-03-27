
/**********************************************************
 * Program Name   : Wheel Class
 * Author         : Robert Short
 * Date           : 3/29/20
 * Program Description: This class stores values in an ArrayList and
 *    displays a spinning wheel animation to the user everytime the
 *    spin method is called.
 *
 * Methods:
 * -------
 * WheelClass - constructor that adds values to a String arraylist
 * Spin - returns a value from the list thats formatted
 *
 **********************************************************/
import java.util.*;

public class WheelClass
{

    //class constants

    //class variables
    private ArrayList<String> wheelValues = new ArrayList<String>();	//wheel
    private Random rand = new Random();									//random object

   /**********************************************************
    * Method Name       : WheelClass
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method adds values to wheelValues
    *
    * BEGIN WheelClass
	*	Add "$500" to wheelValues
	*	Add "$300" to wheelValues
	*	Add "Bankrupt" to wheelValues
    *	Add "$3500" to wheelValues
    *   Add "$500" to wheelValues
    *   Add "$900" to wheelValues
    *   Add "$700" to wheelValues
    *   Add "$1000" to wheelValues
    *   Add "$800" to wheelValues
    *   Add "$500" to wheelValues
    *   Add "$400" to wheelValues
    *   Add "Bankrupt" to wheelValues
    *   Add "$1000000" to wheelValues
    *   Add "Bankrupt" to wheelValues
    *   Add "$600" to wheelValues
    *   Add "$350" to wheelValues
    *   Add "$500" to wheelValues
    *   Add "$900" to wheelValues
    *   Add "Bankrupt" to wheelValues
    *   Add "$650" to wheelValues
    *   Add "Free Play" to wheelValues
    *   Add "$1000" to wheelValues
    *   Add "Lose A Turn" to wheelValues
    *   Add "$800" to wheelValues
    *   Add "$500" to wheelValues
    *   Add "$450" to wheelValues
    * END WheelClass
    **********************************************************/

    public WheelClass()
    {
		wheelValues.add("$500");		   //$500 on wheel
		wheelValues.add("$300");	       //$300 on wheel
		wheelValues.add("Bankrupt");       //Bankrupt on wheel
		wheelValues.add("$3500");		   //$3500 on wheel
        wheelValues.add("$500");		   //$500 on wheel
        wheelValues.add("$900");	       //$900 on wheel
        wheelValues.add("$700");		   //$700 on wheel
        wheelValues.add("$1000");		   //$1000 on wheel
        wheelValues.add("$800");		   //$800 on wheel
        wheelValues.add("$500");		   //$500 on wheel
        wheelValues.add("$400");		   //$400 on wheel
        wheelValues.add("Bankrupt");	   //Bankrupt on wheel
        wheelValues.add("$1000000");	   //$1000000 on wheel
        wheelValues.add("Bankrupt");	   //Bankrupt on wheel
        wheelValues.add("$600");	       //$600 on wheel
        wheelValues.add("$350");	       //$350 on wheel
        wheelValues.add("$500");	       //$500 on wheel
        wheelValues.add("$900");		   //$900 on wheel
        wheelValues.add("Bankrupt");	   //Bankrupt on wheel
        wheelValues.add("$650");		   //$650 on wheel
        wheelValues.add("Free Play");	   //Free Play on wheel
        wheelValues.add("$1000");		   //$1000 on wheel
        wheelValues.add("Lose A Turn");    //Lose a Turn on wheel
        wheelValues.add("$800");		   //$800 on wheel
        wheelValues.add("$500");		   //$500 on wheel
        wheelValues.add("$450");		   //$450 on wheel

    }//end WheelClass

    /**********************************************************
    * Method Name       : WheelClass
    * Author            : Robert Short
    * Date              : 3/29/20
    * Method Description: This method returns a random value from
    *    wheelValues formatted with a border around it
    *
    * BEGIN spin
    *   Get random value from wheelValues and assign it to spinValue
	*	Clear screen
	*	Add the first half of the border to valueFormatted
    *   Set alreadyAdded = false
	*	FOR(every player)
	*		FOR(every tempValue in wheelValues)
	*			Print out the top half of the border
	*			IF(length of tempValue == 4)
	*				Set tempFormat = "|     " + tempValue + "    |"
	*				Print out tempFormat
	*				IF(tempValue == spinValue and alreadyAdded = false)
	*					Add "Spin: " + tempFormat + "\n" to valueFormatted
	*				    Set alreadyAdded = true
	*			    END IF
	*		    ELSE IF(length of tempValue == 5)
	*				Set tempFormat = "|    " + tempValue + "    |"
	*				Print out tempFormat
	*				IF(tempValue == spinValue and alreadyAdded = false)
	*					Add "Spin: " + tempFormat + "\n" to valueFormatted
	*				    Set alreadyAdded = true
	*			    END IF
	*		    ELSE IF(length of tempValue == 8)
	*				Set tempFormat = "|   " + tempValue + "  |"
	*				Print out tempFormat
	*				IF(tempValue == spinValue and alreadyAdded = false)
	*					Add "Spin: " + tempFormat + "\n" to valueFormatted
	*				    Set alreadyAdded = true
	*			    END IF
	*		    ELSE IF(length of tempValue == 9)
	*				Set tempFormat = "|  " + tempValue + "  |"
	*		    	Print out tempFormat
	*			    IF(tempValue == spinValue and alreadyAdded = false)
	*			        Add "Spin: " + tempFormat + "\n" to valueFormatted
	*		            Set alreadyAdded = true
	*			    END IF
	*		    ELSE IF(length of tempValue == 11)
	*				Set tempFormat = "| " + tempValue + " |"
	*				Print out tempFormat
	*				IF(tempValue == spinValue and alreadyAdded = false)
	*			    	Add "Spin: " + tempFormat + "\n" to valueFormatted
	*				    Set alreadyAdded = true
	*			    END IF
	*		    END IF
	*		    Print out the bottom half of the border
	*	    END FOR
	*	END FOR
	*	Add the bottom half of the border to valueFormatted
    *   Clear screen
    *   Return valueFormatted
    * END spin
    **********************************************************/

    public String spin() throws InterruptedException
    {
		//local constants

		//local variables
		String spinValue;              //holds what the user spun
		String valueFormatted = "";    //holds spin value with a border
		String tempFormat;             //holds the temporary format
        boolean alreadyAdded;          //used to get rid of duplicate info

		/***************** Start spin method ************/

		//get random value from wheelValues and assign it to spinValue
		spinValue = wheelValues.get(rand.nextInt(wheelValues.size()));

		//clear screen
		cls();

		//add the first half of the border to valueFormatted
        valueFormatted += setLeft(6, "---------------") + "\n" +
                          setLeft(6, "|             |") + "\n" +
                          setLeft(6, "|             |") + "\n";

        //set alreadyAdded = false
        alreadyAdded = false;

		//for(every player)
        for(int i = 0; i < 3; i++)
        {
			//for(every tempValue in wheelValues)
		    for(String tempValue: wheelValues)
		    {
				//print out the top half of the border
			    System.out.println(setLeft(46, "---------------"));
			    System.out.println(setLeft(46, "|             |"));
			    System.out.println(setLeft(46, "|             |"));

				//if(length of tempValue == 4)
			    if(tempValue.length() == 4)
			    {
					//set tempFormat = "|     " + tempValue + "    |"
					tempFormat = "|     " + tempValue + "    |";

					//print out tempFormat
			    	System.out.println(setLeft(46, tempFormat));

					//if(tempValue == spinValue and alreadyAdded = false)
                    if(tempValue.equals(spinValue) && !alreadyAdded)
					{
						//add "Spin: " + tempFormat + "\n" to valueFormatted
					    valueFormatted += "Spin: " + tempFormat + "\n";

					    //set alreadyAdded = true
					    alreadyAdded = true;

				    }//end if

			    }

			    //else if(length of tempValue == 5)
			    else if(tempValue.length() == 5)
			    {
					//set tempFormat = "|    " + tempValue + "    |"
			        tempFormat = "|    " + tempValue + "    |";

					//print out tempFormat
					System.out.println(setLeft(46, tempFormat));

					//if(tempValue == spinValue and alreadyAdded = false)
					if(tempValue.equals(spinValue) && !alreadyAdded)
					{
						//add "Spin: " + tempFormat + "\n" to valueFormatted
					    valueFormatted += "Spin: " + tempFormat + "\n";

					    //set alreadyAdded = true
					    alreadyAdded = true;

				    }//end if

			    }

			    //else if(length of tempValue == 8)
			    else if(tempValue.length() == 8)
			    {
					//set tempFormat = "|   " + tempValue + "  |"
					tempFormat = "|   " + tempValue + "  |";

					//print out tempFormat
					System.out.println(setLeft(46, tempFormat));

					//if(tempValue == spinValue and alreadyAdded = false)
					if(tempValue.equals(spinValue) && !alreadyAdded)
					{
						//add "Spin: " + tempFormat + "\n" to valueFormatted
					    valueFormatted += "Spin: " + tempFormat + "\n";

					    //set alreadyAdded = true
					    alreadyAdded = true;

				    }//end if

			    }

			    //else if(length of tempValue == 9)
			    else if(tempValue.length() == 9)
			    {
					//set tempFormat = "|  " + tempValue + "  |"
			    	tempFormat = "|  " + tempValue + "  |";

			    	//print out tempFormat
					System.out.println(setLeft(46, tempFormat));

				    //if(tempValue == spinValue and alreadyAdded = false)
                    if(tempValue.equals(spinValue) && !alreadyAdded)
				    {
				        //add "Spin: " + tempFormat + "\n" to valueFormatted
					    valueFormatted += "Spin: " + tempFormat + "\n";

			            //set alreadyAdded = true
			            alreadyAdded = true;

				    }//end if

			    }

			    //else if(length of tempValue == 11)
			    else if(tempValue.length() == 11)
			    {

					//set tempFormat = "| " + tempValue + " |"
			    	tempFormat = "| " + tempValue + " |";

					//print out tempFormat
					System.out.println(setLeft(46, tempFormat));

					//if(tempValue == spinValue and alreadyAdded = false)
					if(tempValue.equals(spinValue) && !alreadyAdded)
					{
				    	//add "Spin: " + tempFormat + "\n" to valueFormatted
					    valueFormatted += "Spin: " + tempFormat + "\n";

					    //set alreadyAdded = true
					    alreadyAdded = true;

				    }//end if

			    }//end if


			    //print out the bottom half of the border
			    System.out.println(setLeft(46, "|             |"));
			    System.out.println(setLeft(46, "|             |"));
			    System.out.println(setLeft(46, "---------------"));

			    Thread.sleep(100);

		    }//end for

		}//end for

		//add the bottom half of the border to valueFormatted
        valueFormatted += setLeft(6, "|             |") + "\n" +
                          setLeft(6, "|             |") + "\n" +
                          setLeft(6, "---------------") + "\n";

        //clear screen
        cls();

        //return valueFormatted
        return valueFormatted;

    }//end spin

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

    private void cls()
    {
        //local constants

	    //local variables


	    /***************** Start cls method ************/

        //try
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

}//end WheelClass


