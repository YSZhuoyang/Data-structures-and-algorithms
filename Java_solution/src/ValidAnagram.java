import java.util.Arrays;

/**
 * Created by oscar on 4/5/16.
 */
public class ValidAnagram
{

    public boolean isAnagram(String s, String t)
    {
        if (s.isEmpty() && t.isEmpty())
        {
            return true;
        }

        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();

        Arrays.sort(chars);
        Arrays.sort(chart);

        if (chars.length != chart.length)
        {
            return false;
        }

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] != chart[i])
            {
                return false;
            }
        }

        return true;
    }
}
