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
