/**
 * Created by oscar on 4/3/16.
 */
public class IsPalindrome
{

    // My solution which beats 44% java submissions
    public boolean isPalindrome(String s)
    {
        if (s.isEmpty())
        {
            return true;
        }

        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;

        while (left < right)
        {
            while (left <= right && !isAlphanumeric(s.charAt(left))) left++;
            while (right >= left && !isAlphanumeric(s.charAt(right))) right--;

            if (left <= right && right >= 0 && s.charAt(left) != s.charAt(right)) return false;

            left++;
            right--;
        }

        return true;
    }

    private boolean isAlphanumeric(char c)
    {
        return c <= 'z' && c >= 'a' || c <= '9' && c >= '0';
    }
}
