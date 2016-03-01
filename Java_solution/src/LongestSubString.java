import java.util.HashMap;

/**
 * Created by oscar on 3/1/16.
 */
public class LongestSubString
{

    // My solution
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
    }
}
