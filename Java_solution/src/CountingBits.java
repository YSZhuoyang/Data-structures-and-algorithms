/**
 * Created by oscar on 7/18/16.
 */
public class CountingBits
{

	// My solution with O(N) time complexity and O(N) extra space
	public int[] countBits(int num)
	{
		int[] numBits = new int[num + 1];
		int[] bin = new int[num + 1];

		bin[0] = 0;
		numBits[0] = 0;

		for (int i = 1; i <= num; i++)
		{
			numBits[i] = addBinary(numBits[i - 1], bin);
		}

		return numBits;
	}

	private int addBinary(int bitCounter, int[] bin)
	{
		int carry = 1;
		int i = 0;

		while (i < bin.length && carry > 0)
		{
			int d = bin[i];
			carry += d;
			d = carry % 2;
			bin[i] = d;
			carry /= 2;

			if (d == 1)
			{
				bitCounter++;
			}
			else
			{
				bitCounter--;
			}

			i++;
		}

		if (carry > 0)
		{
			bin[i] = carry;

			return bitCounter + 1;
		}
		else
		{
			return bitCounter;
		}
	}
}
