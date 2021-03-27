/**********************************************************
 * Program Name       : PlayerClass
 * Author             : Robert Short
 * Date               : 3/29/2020
 * Program Description: This program will create PlayerClass objects
 *    and allow the round balance, game balance, and total balance
 *    to be changed.
 *
 * Methods:
 * -------
 * PlayerClass - instantiates a PlayerClass object
 * bankrupt - sets players round balance to 0
 * calcRoundBalance - adds to the players round balance
 * calcGameBalance - adds to the players game balance
 * calcTotalBalance - adds to the players total balance
 *
 **********************************************************/

import java.util.*;
import java.io.*;

public class PlayerClass
{
    //class constants

    //class variables
    protected String name;               //player's name
    public boolean isPlayersTurn;        //is it the players turn?
    private int gameBalance;             //balance for the current game
    private int roundBalance;            //balance for the current round
    private int totalBalance;            //balance throughout all games played

    /******************************************
     * Method Name        : PlayerClass
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method is a constructor for PlayerClass
     *     objects, assigns the inputted fields to their respective
     *     variables.
     *
     * BEGIN PlayerClass
     *     Sets name = inputted name
     *     Sets isPlayersTurn = inputted truth value
     *     Sets gameBalance = inputted game balance
     *     Sets roundBalance = inputted round balance
     *     Sets totalBalance = inputted total balance
     * END PlayerClass
     ******************************************/

    public PlayerClass(String iName, boolean iIsPlayersTurn, int iGameBalance,
                       int iRoundBalance, int iTotalBalance)
    {
        this.name = iName;
        this.isPlayersTurn = iIsPlayersTurn;
        this.gameBalance = iGameBalance;
        this.roundBalance = iRoundBalance;
        this.totalBalance = iTotalBalance;

    }//end PlayerClass

    /******************************************
     * Method Name        : bankrupt
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method will be used when the wheel lands
     *     on bankrupt, sets roundBalance = 0
     *
     * BEGIN bankrupt
     *     initialize roundBalance to 0
     * END bankrupt
     ******************************************/

    public void bankrupt()
	{
		//local constants

		//local variables

		/***************** Start bankrupt method ************/

	    roundBalance = 0;

	}//end bankrupt

    /******************************************
     * Method Name        : calcRoundBalance
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method will be used to add any amount(int bal)
     *     to the players round Balance
     *
     * BEGIN calcRoundBalance
     *     add bal to roundBalance
     *     return roundBalance
     * END calcRoundBalance
     ******************************************/

	public int calcRoundBalance(int bal)
	{
		//local constants

		//local variables

		/***************** Start calcRoundBalance method ************/

		//add bal to roundBalance
	    roundBalance += bal;

	    //return roundBalance
	    return roundBalance;

	}//end calcRoundBalance

    /******************************************
     * Method Name        : calcGameBalance
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method will be used to add any amount(int bal)
     *     to the players game Balance
     *
     * BEGIN calcGameBalance
     *     add bal to gameBalance
     *     return gameBalance
     * END calcGameBalance
     ******************************************/

	public int calcGameBalance(int bal)
	{
	    //local constants

		//local variables

		/***************** Start calcGameBalance method ************/

		//add bal to gameBalance
	    gameBalance += bal;

	    //return gameBalance
	    return gameBalance;

	}//end calcGameBalance

    /******************************************
     * Method Name        : calcTotalBalance
     * Author             : Robert Short
     * Date               : 3/29/2020
     * Method Description : This method will be used to add any amount(int bal)
     *     to the players total Balance (total balance is used when more than 1 game
     *     is played)
     *
     * BEGIN calcTotalBalance
     *     add bal to totalBalance
     *     return totalBalance
     * END calcTotalBalance
     ******************************************/

    public int calcTotalBalance (int bal)
	{
		//local constants

		//local variables

		/***************** Start calcTotalBalance method ************/

		//add bal to totalBalance
	    totalBalance += bal;

	    //return totalBalance
	    return totalBalance;

	}//end calcTotalBalance

}//end PlayerClass