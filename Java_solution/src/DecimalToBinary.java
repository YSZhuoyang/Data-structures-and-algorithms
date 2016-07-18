import java.util.HashMap;
import java.util.Stack;

/**
 * Created by oscar on 7/18/16.
 *
 * Convert a decimal number to binary representation.
 *
 * Idea:
 * 1. Recursively find out indices of 1s.
 * 2. Speed up searching process by using a stack storing powers of 2 in a descending order.
 */
public class DecimalToBinary
{
	Stack<Integer> powerOf2;
	Stack<Integer> posOfOnes;
	HashMap<Integer, Integer> binTable;

	public String convertDecimalToBinary(String decimal)
	{
		if (decimal == null || decimal.isEmpty())
		{
			return "";
		}
		else if (decimal.equals("0") || decimal.equals("1"))
		{
			return decimal;
		}

		// Construct a hash map for finding indices,
		// And a stack storing powers of 2
		binTable = new HashMap<>(31);
		powerOf2 = new Stack<>();
		int power;

		for (int i = 0; i < 31; i++)
		{
			power = (int) Math.pow(2, i);
			binTable.put(power, i);
			powerOf2.push(power);
		}

		posOfOnes = new Stack<>();
		int d = Integer.parseInt(decimal);

		// Find all indices of 1s and store them into the queue through dfs
		findOnes(d);

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

	private void findOnes(int decimal)
	{
		if (decimal <= 0 || powerOf2.isEmpty())
		{
			return;
		}

		while (powerOf2.peek() > decimal)
		{
			powerOf2.pop();
		}

		posOfOnes.push(binTable.get(powerOf2.peek()));
		findOnes(decimal - powerOf2.pop());
	}
}
