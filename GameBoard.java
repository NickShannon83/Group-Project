/***************************************************
 * The game board
 * 
 * @author Luke Johnson
 *
 *         A class to represent the game board in Colonize Mars
 */
public class GameBoard
{
	private Node head;	//head of the linked list
	private int numTiles;	//number of nodes in the linked list (tiles in the gameBoard)

	/*****************************************************
	 * Default and only constructor
	 */
	public GameBoard()
	{
		this.head = new Node();
		this.numTiles = 1;
	}

	/************************************************
	 * Method to add new nodes to end of linked list
	 * 
	 * @param newNode: Node being added to list 
	 * @return: the head of the list
	 */
	public Node addToTail(Node newNode)
	{
		Node head = this.head;
		Node current = head;

		while (current.getLink() != null)
		{
			current = current.getLink();
		}

		current.setLink(newNode);
		return head;
	}

	/******************************************************************
	 * Method to print the entire list using the toString method for the nodes
	 */
	public void printBoard()
	{
		Node current = this.head;
		for (int i = 0; i < this.numTiles; i++)
		{
			System.out.println(current.toString());
			current = current.getLink();
		}
	}
	
	/************************************************************************
	 * Method to go to a node in the linked list
	 * 
	 * @param tileNum: desired node to traverse to
	 */
	public Node goToTile(int tileNum)
	{
		Node current = this.head;
		if (tileNum < 7 && tileNum > 0)
		{
			for (int i = 1; i < tileNum; i++)
			{
				current = current.getLink ( );
			}
		}
		return current;
	}
	
	/*************************************************************************/
	// GETTER

	public Node getHead()
	{
		return head;
	}

	public void setHead(Node head)
	{
		this.head = head;
	}

	public int getNumTiles()
	{
		return numTiles;
	}

	public void setNumTiles(int numTiles)
	{
		this.numTiles = numTiles;
	}
}