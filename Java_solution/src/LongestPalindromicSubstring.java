/**
 * Created by oscar on 3/9/16.
 */
public class LongestPalindromicSubstring
{
    public String longestPalindrome(String s)
    {
        int max = 1;
        int start = 0;
        int end = 1;
        int len;

        if (s.length() == 1)
        {
            return s;
        }

        for (int i = 0; i < s.length(); i++)
        {
            for (int j = s.length() - 1; j > i; j--)
            {
                len = j - i + 1;

                if (len > max && s.charAt(i) == s.charAt(j) && testPalindromic(i + 1, j - 1, s))
                {
                    max = len;
                    start = i;
                    end = j;

                    break;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    public boolean testPalindromic(int start, int end, String s)
    {
        int len = end - start + 1;

        for (int i = start; i <= start + len / 2; i++)
        {
            if (s.charAt(i) != s.charAt(end - i + start))
            {
                return false;
            }
        }

        return true;
    }
}
