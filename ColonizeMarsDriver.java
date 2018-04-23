
import java.util.*;

public class ColonizeMarsDriver
{
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		Player player1 = new Player();
		initializePlayer(player1);
		player1.getGameBoard().printBoard();

		// Execute 15 turns
		for (int i = 0; i < 15; i++)
		{
			player1.getTurn().setOx(2);
			player1.getTurn().setSil(2);

			player1.getTurn().toString();
			purchase(player1);
			player1.getGameBoard().printBoard();
			// roll dice
			// purchase
			// append score
			// end turn
			// switch player and repeat
		}
	}

	/*********************************************************************
	 * Method to purchase a resource
	 * 
	 * @author Luke Johnson
	 * 
	 * @param player:
	 *           player who is purchasing a resource
	 * @param turn:
	 *           player's assets in a turn
	 */
	public static void purchase(Player player)
	{
		int tileChoice, resourceChoice;
		Node head = player.getGameBoard().getHead();
		Node current = head;

		// Execute if player can purchase at least one resource
		if (player.canPurchase())
		{
			// execute while player can purchase
			while (player.canPurchase())
			{
				do
				{
					System.out.println("What tile would you like to purchase a resource from?");
					System.out.println("Enter a number 1 - 6.");
					tileChoice = input.nextInt();
					System.out.println("Which resource would you like to from the tile?");
					System.out.println("1) Main Road\n2) Side Road\n3) Astronaut\n4) Bio-dome\n" + "5) Research Facility");
					resourceChoice = input.nextInt();
					if ((tileChoice < 1 || tileChoice > 6) || (resourceChoice < 1 || resourceChoice > 5))
					{
						System.out.println("Invalid selection.");
					}
				} while (((tileChoice < 1) || (tileChoice > 6)) || (resourceChoice < 1 || resourceChoice > 5));

				for (int i = 1; i < tileChoice; i++)
				{
					current = current.getLink();
				}
				switch (resourceChoice)
				{
					// purchase a main road
					// unlock side road, next main road and bio-dome
					case 1:
						if (current.getMainRoad().isUnlocked() && !current.getMainRoad().isOwned()
								&& player.getTurn().getSil() > 0 && player.getTurn().getOx() > 0)
						{
							current.getMainRoad().setOwned(true);
							player.setScore(player.getScore() + current.getMainRoad().getValue());
							player.getTurn().setSil(player.getTurn().getSil() - 1);
							player.getTurn().setOx(player.getTurn().getOx() - 1);
							current.getSideRoad().setUnlocked(true);
							current.getBioDome().setUnlocked(true);
							;
							if (current.getLink() != null)
							{
								current.getLink().getMainRoad().setUnlocked(true);
							}
						}
						// output error statements
						else if (!current.getMainRoad().isUnlocked())
						{
							System.out.println("That is not unlocked.");
						}
						else if (current.getMainRoad().isOwned())
						{
							System.out.println("You already own that.");
						}
						else
						{
							System.out.println("You can't afford that.");
						}
						break;

					// purchase a side road
					// unlock the research facility
					case 2:
						if (current.getSideRoad().isUnlocked() && !current.getSideRoad().isOwned()
								&& player.getTurn().getSil() > 0 && player.getTurn().getOx() > 0)
						{
							current.getSideRoad().setOwned(true);
							player.setScore(player.getScore() + current.getSideRoad().getValue());
							player.getTurn().setSil(player.getTurn().getSil() - 1);
							player.getTurn().setOx(player.getTurn().getOx() - 1);
							current.getResearch().setUnlocked(true);
						}
						// output error statements
						else if (!current.getSideRoad().isUnlocked())
						{
							System.out.println("That is not unlocked.");
						}
						else if (current.getSideRoad().isOwned())
						{
							System.out.println("You already own that.");
						}
						else
						{
							System.out.println("You can't afford that.");
						}
						break;

					// purchase an astronaut
					// unlock the next astronaut
					case 3:
						if (current.getAstronaut().isUnlocked() && !current.getAstronaut().isOwned()
								&& player.getTurn().getWat() > 0 && player.getTurn().getSol() > 0
								&& player.getTurn().getOre() > 0)
						{
							current.getAstronaut().setOwned(true);
							player.setScore(player.getScore() + current.getAstronaut().getValue());
							player.getTurn().setWat(player.getTurn().getWat() - 1);
							player.getTurn().setOre(player.getTurn().getOre() - 1);
							player.getTurn().setSol(player.getTurn().getSol() - 1);
							if (current.getLink() != null)
							{
								current.getLink().getAstronaut().setUnlocked(true);
							}
						}
						// output error statements
						else if (!current.getAstronaut().isUnlocked())
						{
							System.out.println("That is not unlocked.");
						}
						else if (current.getAstronaut().isOwned())
						{
							System.out.println("You already own that.");
						}
						else
						{
							System.out.println("You can't afford that.");
						}
						break;

					// purchase a bio-dome
					case 4:
						if (current.getBioDome().isUnlocked() && !current.getBioDome().isOwned()
								&& player.getTurn().getSil() > 0 && player.getTurn().getOx() > 0
								&& player.getTurn().getWat() > 0 && player.getTurn().getSol() > 0)
						{
							current.getBioDome().setOwned(true);
							player.setScore(player.getScore() + current.getBioDome().getValue());
							player.getTurn().setWat(player.getTurn().getWat() - 1);
							player.getTurn().setOx(player.getTurn().getOx() - 1);
							player.getTurn().setSol(player.getTurn().getSol() - 1);
							player.getTurn().setSil(player.getTurn().getSil() - 1);
						}
						// output error statements
						else if (!current.getBioDome().isUnlocked())
						{
							System.out.println("That is not unlocked.");
						}
						else if (current.getBioDome().isOwned())
						{
							System.out.println("You already own that.");
						}
						else
						{
							System.out.println("You can't afford that.");
						}
						break;

					// purchase a research facility
					case 5:
						if (current.getResearch().isUnlocked() && !current.getResearch().isOwned()
								&& player.getTurn().getOre() == 3 && player.getTurn().getSol() == 2)
						{
							current.getResearch().setOwned(true);
							player.setScore(player.getScore() + current.getResearch().getValue());
							player.getTurn().setWat(player.getTurn().getWat() - 1);
							player.getTurn().setOx(player.getTurn().getOx() - 1);
							player.getTurn().setSol(player.getTurn().getSol() - 1);
							player.getTurn().setSil(player.getTurn().getSil() - 1);
						}

						// output error statements
						else if (!current.getResearch().isUnlocked())
						{
							System.out.println("That is not unlocked.");
						}
						else if (current.getResearch().isOwned())
						{
							System.out.println("You already own that.");
						}
						else
						{
							System.out.println("You can't afford that.");
						}
						break;
				}
				// set current back to the head of the list
				current = head;
			}
			System.out.println("No further purchases available.");
		}
		else
		{
			System.out.println("No purchases available.\nBetter luck next turn.");
		}
	}

	/**********************************************************************************
	 * Method to initialize a player
	 * 
	 * @author Luke Johnson
	 * @param player:
	 *           player whose board is being initialized
	 */
	public static void initializePlayer(Player player)
	{
		Node head = player.getGameBoard().getHead();
		Node current = head;

		// create all of the tiles on the board
		for (int i = 0; i < 5; i++)
		{
			Node newNode = new Node((i + 2), current.getResearch().getValue(), current.getAstronaut().getValue(),
					current.getBioDome().getValue());
			player.getGameBoard().addToTail(newNode);
			current.setLink(newNode);
			current = newNode;
			player.getGameBoard().setNumTiles(player.getGameBoard().getNumTiles() + 1);

		}
		// unlock main road and astronaut on first tile
		player.getGameBoard().getHead().getMainRoad().setUnlocked(true);
		player.getGameBoard().getHead().getAstronaut().setUnlocked(true);
	}
}
