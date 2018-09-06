/**
 * Created by oscar on 17/02/17.
 */
public class RepeatedSubstring
{
    public boolean repeatedSubstringPattern(String str)
    {
        if (str == null || str.isEmpty() || str.length() == 1) return false;

        int len = str.length();
        boolean found;

        for (int i = 1; i < len; i++)
        {
            if (len % i > 0) continue;

            found = true;

            for (int j = 1; j < len / i; j++)
            {
                // System.out.println(str.substring(i * (j - 1), i * j));
                // System.out.println(str.substring(i * j, i * (j + 1)) + "\n");

                if (!str.substring(i * (j - 1), i * j).equals(str.substring(i * j, i * (j + 1))))
                {
                    found = false;
                    break;
                }
            }

            if (found) return true;
        }

        return false;
    }
}
