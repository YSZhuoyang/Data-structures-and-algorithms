/**
 * Created by oscar on 6/29/16.
 *
 * Given k eggs, a building with n floors, find out the
 * highest floor where an egg being dropped will not break
 * (an unrealistic problem but a good practice for binary search)
 * eggs can be picked up if they do not break.
 */
public class ThrowEggs
{

	public int findFloor(int k, int n)
	{
		if (k < 1 || n < 1)
		{
			return -1;
		}
		else if (!eggBreak(n))
		{
			return n;
		}

		return binarySearch(1, n, k);
	}

	public int binarySearch(int left, int right, int k)
	{
		if (k == 1)
		{
			for (int i = left; i < right; i++)
			{
				if (eggBreak(i))
				{
					return i;
				}
			}

			return right;
		}

		int middle = (left + right) / 2;

		if (!eggBreak(middle))
		{
			return binarySearch(middle, right, k - 1);
		}
		else
		{
			return binarySearch(left, middle, k - 1);
		}
	}

	public boolean eggBreak(int l)
	{
		return l > 5;
	}
}
