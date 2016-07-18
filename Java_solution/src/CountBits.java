import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 7/18/16.
 */
public class CountBits
{
	List<Integer> list = new ArrayList<>();

	// My solution with O(N) time complexity and O(N) extra space
	public int[] countBits(int num)
	{
		int[] res = new int[num + 1];

		int bitCounter = 0;
		list.add(0);
		res[0] = 0;

		for (int i = 1; i <= num; i++)
		{
			bitCounter = addBinary(bitCounter, list);
			res[i] = bitCounter;
		}

		return res;
	}

	private int addBinary(int bitCounter, List<Integer> list)
	{
		int carry = 1;

		for (int i = 0; i < list.size(); i++)
		{
			if (carry > 0)
			{
				int d = list.get(i);
				carry += d;
				d = carry % 2;
				list.set(i, d);
				carry /= 2;

				if (d == 1)
				{
					bitCounter++;
				}
				else
				{
					bitCounter--;
				}
			}
			else
			{
				return bitCounter;
			}
		}

		if (carry > 0)
		{
			list.add(carry);

			return bitCounter + 1;
		}
		else
		{
			return bitCounter;
		}
	}
}
