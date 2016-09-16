import java.util.HashMap;

/**
 * Created by oscar on 3/1/16.
 *
 * Longest substring without repeating characters
 */
public class LongestSubString
{

	// My optimized second solution using two pointers which beats 84.65% java submissions
	// There are two tricks:
	// 1. An int array was used as a hash map.
	// 2. Notice that the right pointer always goes forward,
	//    never goes backward.
	public int lengthOfLongestSubstring(String s)
	{
		if (s == null)
		{
			return 0;
		}

		s = s.trim();

		if (s.isEmpty())
		{
			return 0;
		}

		int rPointer = 0;
		int max = 0;
		int len;
		char[] sArray = s.toCharArray();
		int[] memo = new int[255];

		// Init array with string characters, set their index to -1
		for (int i = 0; i < sArray.length; i++)
		{
			memo[sArray[i]] = -1;
		}

		// Notice that 'sArray.length - max' will save some time
		for (int lPointer = 0; lPointer < sArray.length - max; lPointer++)
		{
			while (rPointer < sArray.length && memo[sArray[rPointer]] == -1)
			{
				memo[sArray[rPointer++]] = 0;
			}

			len = rPointer - lPointer;

			if (len > max)
			{
				max = len;
			}

			memo[sArray[lPointer]] = -1;
		}

		return max;
	}

    // Recommended solution (linear time)
    // The idea is that traversing the string from left to right,
    // check the existence of current character and compare the length
    // of current substring with the distance between repeatedly
    // appeared characters.
    /*public int lengthOfLongestSubstring(String s)
    {
        // The temp array can be considered as a hashmap, where the
        // key is characters of the string and value is the index in
        // that string
        int[] temp = new int[255];
        int currLen = 0;
        int maxLen = 1;
        int preIndex;

        if (s == null || s.isEmpty())
        {
            return 0;
        }

        // Init array with string characters, set their index to -1
        for (int i = 0; i < s.length(); i++)
        {
            temp[s.charAt(i)] = -1;
        }

        for (int i = 0; i < s.length(); i++)
        {
            preIndex = temp[s.charAt(i)];

            // if (i - preIndex > currLen) means that the character is
            // within the currently considered substring
            if (preIndex == -1 || i - preIndex > currLen)
            {
                currLen++;
            }
            else
            {
                if (currLen > maxLen)
                {
                    maxLen = currLen;
                }

                currLen = i - preIndex;
            }

            temp[s.charAt(i)] = i;
        }

        if (currLen > maxLen)
        {
            maxLen = currLen;
        }

        return maxLen;
    }*/

    /* My first solution
    public int lengthOfLongestSubstring(String s)
    {
        HashMap<Character, Integer> temp = new HashMap<>();
        int max = 0;

        if (!s.isEmpty())
        {
            max = 1;
        }

        for (int i = 0; i < s.length(); i++)
        {
            temp.clear();
            temp.put(s.charAt(i), 0);

            for (int j = i + 1; j < s.length(); j++)
            {
                if (temp.put(s.charAt(j), 0) == null)
                {
                    if (temp.size() > max)
                    {
                        max = temp.size();
                    }
                }
                else
                {
                    break;
                }
            }
        }

        return max;
    }*/
}
