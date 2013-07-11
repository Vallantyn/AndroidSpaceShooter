package engine;

/**
 * Created by Julien on 09/07/13.
 */
public class Dice
{
	public static boolean get4(int chance)
	{
		return getN(4, chance);
	}

	public static boolean get6(int chance)
	{
		return getN(6, chance);
	}

	public static boolean get8(int chance)
	{
		return getN(8, chance);
	}

	public static boolean get10(int chance)
	{
		return getN(10, chance);
	}

	public static boolean get12(int chance)
	{
		return getN(12, chance);
	}

	public static boolean get20(int chance)
	{
		return getN(20, chance);
	}

	public static boolean get100(int chance)
	{
		return getN(100, chance);
	}

	public static boolean getN(int dice, int chance)
	{
		return (((int)Math.random()*dice) < chance);
	}
}
