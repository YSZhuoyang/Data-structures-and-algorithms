/**
 * Created by oscar on 6/23/16.
 *
 * Determine whether all characters in a string is unique.
 */
public class UniqueCharStr
{

	public boolean uniqueCharStr(String s)
	{
		if (s == null || s.isEmpty())
		{
			return false;
		}

		int[] hashTableArray = new int[200];

		for (int i = 0; i < s.length(); i++)
		{
			if (hashTableArray[s.charAt(i)] == 1)
			{
				return false;
			}
			else
			{
				hashTableArray[s.charAt(i)]++;
			}
		}

		return true;
	}
}
