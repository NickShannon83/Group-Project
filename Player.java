/*************************************************************************************
 * A player
 * 
 * @author Luke Johnson
 * @author Nicks
 *
 *         A class to represent a player in the game
 */
public class Player
{
	private GameBoard gameBoard; // player's gameBoard
	private Turn turn; // a turn for a player that stores their dice roll
	private int score; // player's score
	private String name; // The players name
	private int turnNum;

	/***********************************************************************************
	 * Default and only constructor for Player
	 */
	public Player()
	{
		this.gameBoard = new GameBoard ( );
		this.score = 0;
		this.turn = new Turn ( );
	}

	// The player constructor
	public Player(String name)
	{
		this.gameBoard = new GameBoard ( );
		this.score = 0;
		this.turn = new Turn ( );
		this.name = name;
		this.turnNum = 1;
	}



	/***********************************************************************************
	 * Method used to determine whether a player is able to make a purchase
	 * 
	 * @return canPurchase: boolean representing whether or not the player can purchase
	 */
	public boolean canPurchase( )
	{
		boolean canPurchase = false;

		if ( ( turn.getSil ( ) > 0 && turn.getOx ( ) > 0 )
				|| ( turn.getWat ( ) > 0 && turn.getSol ( ) > 0 && turn.getOre ( ) > 0 )
				|| ( turn.getSil ( ) > 0 && turn.getOx ( ) > 0 && turn.getWat ( ) > 0 && turn.getSol ( ) > 0 )
				|| ( turn.getOre ( ) == 3 && turn.getSol ( ) == 2 ) )
		{
			canPurchase = true;
		}
		return canPurchase;
	}

	/***********************************************************************************
	 * GETTERS AND SETTERS
	 */
	public GameBoard getGameBoard( )
	{
		return gameBoard;
	}

	public void setGameBoard( GameBoard gameBoard )
	{
		this.gameBoard = gameBoard;
	}

	public int getScore( )
	{
		return score;
	}

	public void setScore( int score )
	{
		this.score = score;
	}

	public Turn getTurn( )
	{
		return turn;
	}

	public void setTurn( Turn turn )
	{
		this.turn = turn;
	}

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}
	public int getTurnNum( )
	{
		return turnNum;
	}

	public void setTurnNum( int turnNum )
	{
		this.turnNum = turnNum;
	}

}