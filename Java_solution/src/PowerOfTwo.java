//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 * Created by oscar on 4/4/16.
 */
public class PowerOfTwo
{

    // The fastest solution is to build a lookup table

    // A fast solution using bit manipulation
    public boolean isPowerOfTwo(int n)
    {
        // Be careful, n must be greater than 0
        return n > 0 && (n & (n - 1)) == 0;
    }

    /* A very good alternative
    public boolean isPowerOfTwo(int n)
    {
        return n > 0 && 1073741824 % n == 0;
    }*/

    /* An alternative using bit manipulation and iteration
    public boolean isPowerOfTwo(int n)
    {
        while (n > 1 && (n & 1) == 0)
        {
            n /= 2;
        }

        return n == 1;
    }*/

    /* An interesting solution using regular expression
    public boolean isPowerOfFour(int num)
    {
        if (num > 0)
        {
            String bString = Integer.toBinaryString(num);
            String pattern = "^1(0)*$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(bString);

            return m.find();
        }

        return false;
    }*/

    /* An alternative using iteration
    public boolean isPowerOfTwo(int n)
    {
        while (n > 1 && n % 2 == 0)
        {
            n /= 2;
        }

        return n == 1;
    }*/
}
