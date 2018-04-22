/**************************************************************
 * Abstract class that represents a purchasable Resource
 * 
 * @author Luke Johnson
 *
 *         Class for all purchasable Resources in the game
 */
public class Resource
{
	private int value;	//point value of a resource
	private String type;	//resource's type (astronaut, bio-dome, research, roads)
	private boolean owned;	//ownership of the object
	private boolean unlocked;	//whether the resource is currently purchasable
	private int oreCost;	//cost in ore
	private int siliconCost;	//cost in silicon
	private int oxygenCost;	//cost it oxygen
	private int waterCost;	//cost in water
	private int solarCost;	//cost in solar

	/************************************************************
	 * Default Constructor
	 */
	public Resource()
	{
		this.value = -1;
		this.type = null;
		this.oreCost = 0;
		this.siliconCost = 0;
		this.oxygenCost = 0;
		this.waterCost = 0;
		this.solarCost = 0;
		this.owned = false;
		this.unlocked = false;

	}

	/************************************************************
	 * Constructor with value and cost parameters Set owned and unlocked to false
	 * 
	 * @param value:
	 *           point value of the Resource
	 * @param cost:
	 *           cost of the Resource
	 */
	public Resource(int value, String type, int oreCost, int siliconCost, int oxygenCost, int waterCost, int solarCost)
	{
		this.value = value;
		this.type = type;
		this.oreCost = oreCost;
		this.siliconCost = siliconCost;
		this.oxygenCost = oxygenCost;
		this.waterCost = waterCost;
		this.solarCost = solarCost;
		this.owned = false;
		this.unlocked = false;
	}

	/*******************************************************
	 * Overridden toString
	 */
	@Override
	public String toString()
	{
		String resourceString = ("Resource Type: " + this.type + "\n Value: " + this.value + "\n Cost = Ore: "
				+ this.oreCost + ", Silicon: " + siliconCost + ", Oxygen: " + this.oxygenCost + ", Water: " + this.waterCost
				+ ", Solar: " + this.solarCost + "\n Owned: " + (this.owned ? "Yes" : "No") + "\n Purchasable: "
				+ (this.unlocked ? "Yes" : "No") + "\n");
		return resourceString;
	}

	/**********************************************************************************************************
	 * GETTERS AND SETTERS
	 */
	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public boolean isOwned()
	{
		return owned;
	}

	public void setOwned(boolean owned)
	{
		this.owned = owned;
	}

	public boolean isUnlocked()
	{
		return unlocked;
	}

	public void setUnlocked(boolean unlocked)
	{
		this.unlocked = unlocked;
	}

	public int getOreCost()
	{
		return oreCost;
	}

	public int getSiliconCost()
	{
		return siliconCost;
	}

	public int getOxygenCost()
	{
		return oxygenCost;
	}

	public int getWaterCost()
	{
		return waterCost;
	}

	public int getSolarCost()
	{
		return solarCost;
	}
}
