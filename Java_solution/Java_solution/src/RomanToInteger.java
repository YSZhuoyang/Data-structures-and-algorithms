/**
 * Created by oscar on 4/18/16.
 */
public class RomanToInteger
{

	// My solution which beats 82% java submissions
	public int romanToInt(String s)
	{
		if (s == null || s.isEmpty())
		{
			return 0;
		}

		int units = 0;
		int tens = 0;
		int hundreds = 0;
		int thousands = 0;

		char last = 'a';

		for (int i = 0; i < s.length(); i++)
		{
			switch (s.charAt(i))
			{
				case 'I':
					units++;
					last = 'I';
					break;

				case 'V':
					if (last == 'I')
					{
						units += 3;
					}
					else
					{
						units += 5;
					}

					last = 'V';
					break;

				case 'X':
					if (last == 'I')
					{
						units += 8;
					}
					else
					{
						tens += 10;
					}

					last = 'X';
					break;

				case 'L':
					if (last == 'X')
					{
						tens += 30;
					}
					else
					{
						tens += 50;
					}

					last = 'L';
					break;

				case 'C':
					if (last == 'X')
					{
						tens += 80;
					}
					else
					{
						hundreds += 100;
					}

					last = 'C';
					break;

				case 'D':
					if (last == 'C')
					{
						hundreds += 300;
					}
					else
					{
						hundreds += 500;
					}

					last = 'D';
					break;

				case 'M':
					if (last == 'C')
					{
						hundreds += 800;
					}
					else
					{
						thousands += 1000;
					}

					last = 'M';
					break;

				default:
					break;
			}
		}

		return units + tens + hundreds + thousands;
	}
}
