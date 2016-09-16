/**
 * Created by oscar on 4/3/16.
 */
public class LongestCommenPrefix
{

	// A faster recommended solution
	public String longestCommonPrefix(String[] strs)
	{
		if (strs == null || strs.length == 0)
		{
			return "";
		}

		for (int i = 0; i < strs[0].length(); i++)
		{
			char c = strs[0].charAt(i);

			for (String s : strs)
			{
				if (s.length() <= i || s.charAt(i) != c)
				{
					return strs[0].substring(0,i);
				}
			}
		}

		return strs[0];
	}

    // My optimized solution
    /*public String longestCommonPrefix(String[] strs)
    {
        if (strs == null || strs.length == 0)
        {
            return "";
        }

        int len = strs.length;
        int minLen = strs[0].length();

        for (int i = 1; i < len; ++i)
        {
            minLen = Math.min(minLen, strs[i].length());
        }

        for (int j = 0; j < minLen; ++j)
        {
            for (int i = 1; i < len; ++i)
            {
                if (strs[0].charAt(j) != strs[i].charAt(j))
                {
                    return strs[0].substring(0, j);
                }
            }
        }

        return strs[0].substring(0, minLen);
    }*/
}
