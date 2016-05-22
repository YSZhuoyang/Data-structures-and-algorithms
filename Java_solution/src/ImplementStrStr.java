/**
 * Created by oscar on 4/21/16.
 */
public class ImplementStrStr
{

	// My solution which beats 63.97% java submissions.
	// Using String.indexOf() is very fast.
	public int strStr(String haystack, String needle)
	{
		if (haystack == null || needle == null || needle.length() > haystack.length())
		{
			return -1;
		}
		else if (needle.isEmpty())
		{
			return 0;
		}

		// Traversing a character array is much faster
		// than Traversing a string
		char[] hayArr = haystack.toCharArray();
		char[] neeArr = needle.toCharArray();

		int newStart = 0;
		int hayLen = hayArr.length;
		int neeLen = neeArr.length;

		boolean newStartFound = false;

		for (int i = 0; i <= hayLen - neeLen; i++)
		{
			if (hayArr[i] == neeArr[0])
			{
				for (int j = 0; j < neeLen; j++)
				{
					if (!newStartFound && j > 0 && neeArr[j] == neeArr[0])
					{
						newStart = i + j;
						newStartFound = true;
					}

					if (hayArr[i + j] != neeArr[j])
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
