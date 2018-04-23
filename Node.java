/***************************************************************
 * A node that contains multiple objects
 * 
 * @author Luke Johnson
 *
 *         This class represents a tile on the game board
 */
public class Node
{
	private Resource research; // research station resource
	private Resource astronaut; // astronaut resource
	private Resource mainRoad; // main road resource
	private Resource sideRoad; // side road resource
	private Resource bioDome; // bio-dome resource
	private int nodeNumber; // tile number
	private Node link; // link to next node (tile)

	/************************************************************
	 * Default Constructor
	 */
	public Node()
	{
		this.research = new Resource(4, "Research", 3, 0, 0, 0, 2);
		this.astronaut = new Resource(1, "Astronaut", 1, 0, 0, 1, 1);
		this.mainRoad = new Resource(1, "Main Road", 0, 1, 1, 0, 0);
		this.sideRoad = new Resource(1, "Side Road", 0, 1, 1, 0, 0);
		this.bioDome = new Resource(2, "Bio-dome", 0, 1, 1, 1, 1);
		this.nodeNumber = 1;
		this.link = null;
	}

	/************************************************************
	 * Constructor with value parameter for use in setting item values
	 * 
	 * @param value:
	 *           used to set values in Node's items
	 */
	public Node(int nodeNumber, int researchValue, int astronautValue, int bioDomeValue)
	{
		this.nodeNumber = nodeNumber;
		this.research = new Resource(researchValue + 4, "Research", 3, 0, 0, 0, 2);
		if (nodeNumber == 6)
		{
			this.astronaut = new MarkWatneySpacePirate();
		}
		else
		{
			this.astronaut = new Resource(astronautValue + 1, "Astronaut", 1, 0, 0, 1, 1);
		}
		this.mainRoad = new Resource(1, "Main Road", 0, 1, 1, 0, 0);
		this.sideRoad = new Resource(1, "Side Road", 0, 1, 1, 0, 0);
		this.bioDome = new Resource(bioDomeValue + 2, "Bio-dome", 0, 1, 1, 1, 1);
		this.link = null;

	}
	
	@Override
	public String toString()
	{
		String tileString = "Tile " + this.nodeNumber + "\n" + this.research.toString() + this.astronaut.toString()
				+ this.bioDome.toString() + this.mainRoad.toString() + this.sideRoad.toString();
		return tileString;
	}

	/**********************************************************************************************
	 * GETTERS AND SETTERS
	 */
	public Resource getResearch()
	{
		return research;
	}

	public void setResearch(Resource research)
	{
		this.research = research;
	}

	public Resource getAstronaut()
	{
		return astronaut;
	}

	public void setAstronaut(Resource astronaut)
	{
		this.astronaut = astronaut;
	}

	public Resource getMainRoad()
	{
		return mainRoad;
	}

	public void setMainRoad(Resource mainRoad)
	{
		this.mainRoad = mainRoad;
	}

	public Resource getSideRoad()
	{
		return sideRoad;
	}

	public void setSideRoad(Resource sideRoad)
	{
		this.sideRoad = sideRoad;
	}

	public Resource getBioDome()
	{
		return bioDome;
	}

	public void setBioDome(Resource bioDome)
	{
		this.bioDome = bioDome;
	}

	public Node getLink()
	{
		return link;
	}

	public void setLink(Node link)
	{
		this.link = link;
	}
}
