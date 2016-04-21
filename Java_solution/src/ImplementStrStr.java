/**
 * Created by oscar on 4/21/16.
 */
public class ImplementStrStr
{

	// My solution which beats 52%
	// Using String.indexOf() is very fast
	public int strStr(String haystack, String needle)
	{
		if (haystack == null || needle == null)
		{
			return -1;
		}
		else if (haystack.isEmpty() || needle.isEmpty() || needle.length() > haystack.length())
		{
			if (needle.isEmpty())
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}

		int newStart = 0;
		int hayLen = haystack.length();
		int neeLen = needle.length();
		boolean newStartFound = false;

		for (int i = 0; i <= hayLen - neeLen; i++)
		{
			if (haystack.charAt(i) == needle.charAt(0))
			{
				for (int j = 0; j < neeLen; j++)
				{
					if (!newStartFound && j > 0 && needle.charAt(j) == haystack.charAt(i))
					{
						newStart = i + j;
						newStartFound = true;
					}

					if (haystack.charAt(i + j) != needle.charAt(j))
					{
						if (!newStartFound)
						{
							i = i + j - 1;
						}
						else
						{
							i = newStart - 1;
						}

						newStartFound = false;
						break;
					}

					if (j == neeLen - 1)
					{
						return i;
					}
				}
			}
		}

		return -1;
	}
}
