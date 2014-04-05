package dungeongame;

public class Level
{
	private int numberOfRedies;
	private int numberOfPinkies;
	private int numberOfOrangies;
	private int numberOfBiggies;
	private int numberOfBlocks;
	public Level(int numberOfRedies, int numberOfPinkies, int numberOfOrangies, int numberOfBiggies, int numberOfBlocks)
	{
		this.numberOfRedies = numberOfRedies;
		this.numberOfPinkies = numberOfPinkies;
		this.numberOfOrangies = numberOfOrangies;
		this.numberOfBiggies = numberOfBiggies;
		this.numberOfBlocks = numberOfBlocks;
	}

	public int getNumberOfThings(int Index)
	{
		if (Index == 1)
		{
			return numberOfRedies;
		} else if (Index == 2)
		{
			return numberOfPinkies;

		} else if (Index == 3)
		{
			return numberOfOrangies;

		} else if (Index == 4)
		{
			return numberOfBiggies;

		} else if (Index == 5)
		{
			return numberOfBlocks;

		}else{
			return 0;
		}
	}
}
