/**
 * Created by oscar on 4/3/16.
 */
public class IsPalindrome
{

    // My solution which beats 65.54% java submissions
    public boolean isPalindrome(String s)
    {
        if (s == null)
        {
            return false;
        }

        s = s.trim();

        if (s.isEmpty())
        {
            return true;
        }

        char[] cArray = s.toLowerCase().toCharArray();
        int left = 0;
        int right = cArray.length - 1;

        while (left < right)
        {
            while (left <= right && !isAlphanumeric(cArray[left]))
            {
                left++;
            }

            while (right >= left && !isAlphanumeric(cArray[right]))
            {
                right--;
            }

            if (left <= right && right >= 0 && cArray[left] != cArray[right])
            {
                return false;
            }

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
