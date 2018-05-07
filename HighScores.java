/*********************************************
 * 
 * @author Nicks
 *	Class that holds scores and names of each player 
 */

public class HighScores
{

	String playerName;
	int score;
	
	//@Override
	//overwritten toString method
	public String toString( int count )
	{
		return count + " " + playerName + "\t" + score;
	}
	//constructor
	public HighScores(String playerName, int score)
	{
		super ( );
		this.playerName = playerName;
		this.score = score;
	}
	//setters and getters
	public String getPlayerName( )
	{
		return playerName;
	}

	public void setPlayerName( String playerName )
	{
		this.playerName = playerName;
	}

	public int getScore( )
	{
		return score;
	}

	public void setScore( int score )
	{
		this.score = score;
	}

}