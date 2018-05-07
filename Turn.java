/****************************************************
 * 
 * @author Nick Shannon
 *	Class to keep track of each turns resources gained
 */
public class Turn
{
	int ore;
	int ox;
	int sil;
	int sol;
	int wat;

	@Override
	//overwritten toString
	public String toString( )
	{
		System.out.println (
				"Turn [ore = " + ore + ", ox = " + ox + ", sil = " + sil + ", sol = " + sol + ", wat = " + wat + "]" );
		return "Turn [ore = " + ore + ", ox = " + ox + ", sil = " + sil + ", sol = " + sol + ", wat = " + wat + "]";
	}
	//Constructor
	public Turn()
	{
		this.ore = 0;
		this.ox = 0;
		this.sil = 0;
		this.sol = 0;
		this.wat = 0;
	}


	// setter and getters for resources
	public int getOre( )
	{
		return ore;
	}

	public void setOre( int ore )
	{
		this.ore = ore;
	}

	public int getOx( )
	{
		return ox;
	}

	public void setOx( int ox )
	{
		this.ox = ox;
	}

	public int getSil( )
	{
		return sil;
	}

	public void setSil( int sil )
	{
		this.sil = sil;
	}

	public int getSol( )
	{
		return sol;
	}

	public void setSol( int sol )
	{
		this.sol = sol;
	}

	public int getWat( )
	{
		return wat;
	}

	public void setWat( int wat )
	{
		this.wat = wat;
	}
}