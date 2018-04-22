
public class Turn
{
	int ore;
	int ox;
	int sil;
	int sol;
	int wat;
	
	
	@Override
	public String toString( )
	{
		System.out.println ( "Turn [ore=" + ore + ", ox=" + ox + ", sil=" + sil + ", sol=" + sol + ", wat=" + wat + "]" );
		return "Turn [ore=" + ore + ", ox=" + ox + ", sil=" + sil + ", sol=" + sol + ", wat=" + wat + "]";
	}
	public Turn()
	{

		this.ore = ore;
		this.ox = ox;
		this.sil = sil;
		this.sol = sol;
		this.wat = wat;
	}
	//setter and getters for resources
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
