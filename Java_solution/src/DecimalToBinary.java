import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by oscar on 7/18/16.
 *
 * Convert a decimal number to binary representation
 */
public class DecimalToBinary
{
	HashMap<Integer, Integer> binTable;
	ArrayDeque<Integer> posOfOnes;

	public String convertDecimalToBinary(String decimal)
	{
		if (decimal == null || decimal.isEmpty())
		{
			return "";
		}

		// Construct a hash map storing powers of 2
		binTable = new HashMap<>(31);

		for (int i = 0; i < 31; i++)
		{
			binTable.put((int) Math.pow(2, i), i);
		}

		posOfOnes = new ArrayDeque<>();
		int d = Integer.parseInt(decimal);

		// Find all indices of 1s and store them into the queue through dfs
		if (!findOnes(d)) return "";

		// Construct a binary number with indices of 1s
		int pos;
		StringBuilder sb = new StringBuilder();

		while (!posOfOnes.isEmpty())
		{
			pos = posOfOnes.pop();

			for (int numZeros = pos - sb.length(); numZeros > 0; numZeros--)
			{
				sb.insert(0, '0');
			}

			sb.insert(0, '1');
		}

		return sb.toString();
	}

	private boolean findOnes(int decimal)
	{
		if (decimal > 0 && binTable.get(decimal) != null)
		{
			int bin = binTable.get(decimal);
			binTable.remove(decimal);
			posOfOnes.add(bin);

			return true;
		}

		for (int i = decimal - 1; i > 0; i--)
		{
			if (binTable.get(i) != null)
			{
				int bin = binTable.get(i);
				binTable.remove(i);

				if (findOnes(decimal - i))
				{
					posOfOnes.add(bin);

					return true;
				}
			}
		}

		return false;
	}
}
