/**
 * Created by oscar on 4/22/16.
 * A very good challenge.
 */
public class StringToInt
{

	// My solution optimized, beating 84% java submissions
	// Note: remember to consider integer overflow, - / +
	public int myAtoi(String str)
	{
		if (str == null)
		{
			return 0;
		}

		str = str.trim();
		char[] cArray = str.toCharArray();
		int len = cArray.length;

		if (len == 0)
		{
			return 0;
		}
		
		// Need to check input format
		// ...

		char character;
		long integer = 0;
		int sign = 1;

		for (int i = 0; i < len; i++)
		{
			character = cArray[i];

			if (isDigit(character))
			{
				integer = integer * 10 + character - '0';

				if (integer > Integer.MAX_VALUE)
				{
					if (sign == 1)
					{
						return Integer.MAX_VALUE;
					}
					else
					{
						return Integer.MIN_VALUE;
					}
				}
			}
			else
			{
				if (i == 0)
				{
					if (character == '-')
					{
						sign = -1;
					}
					else if (character != '+')
					{
						break;
					}
				}
				else
				{
					break;
				}
			}
		}

		return sign * (int) integer;
	}

	private boolean isDigit(char c)
	{
		return c >= '0' && c <= '9';
	}
}
