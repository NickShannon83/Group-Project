
public class HighScores
{
	
	String playerName;
	int score;
	
	
	@Override
	public String toString( )
	{
		System.out.println ( "HighScores [playerName=" + playerName + ", score=" + score + "]" );
		return "HighScores [playerName=" + playerName + ", score=" + score + "]";
	}
	public HighScores(String playerName, int score)
	{
		super ( );
		this.playerName = playerName;
		this.score = score;
	}
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
