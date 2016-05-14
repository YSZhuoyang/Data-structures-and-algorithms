/**
 * Created by oscar on 5/14/16.
 */
public class ZigZagConversion
{

	// A good solution which beats 71% java submissions
	public String convert(String s, int numRows)
	{
		if (s == null || s.isEmpty() || numRows == 1 || s.length() < numRows)
		{
			return s;
		}
		else if (numRows <= 0)
		{
			return "";
		}

		int offsetOfFirstAndLastRow = numRows * 2 - 2;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numRows; i++)
		{
			int next = i;

			if (i == 0 || i == numRows - 1)
			{
				while (next < s.length())
				{
					sb.append(s.charAt(next));

					next += offsetOfFirstAndLastRow;
				}
			}
			else
			{
				int column = 0;

				while (next < s.length())
				{
					sb.append(s.charAt(next));
					column++;

					if (column % 2 == 1)
					{
						next += 2 * (numRows - i - 1);
					}
					else
					{
						next += 2 * i;
					}
				}
			}
		}

		return sb.toString();
	}
}
