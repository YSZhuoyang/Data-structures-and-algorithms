/**
 * Created by oscar on 4/4/16.
 */
public class PowerOfTwo
{

    // Fastest solution is to build a lookup table

    // Count number of bit 1 using bit manipulation
    public boolean isPowerOfTwo(int n)
    {
        int count = 0;

        // Be careful, n must be greater than 0
        while (n > 0)
        {
            n = n & (n - 1);
            count++;
        }

        return count == 1;
    }

    /* A very good alternative
    public boolean isPowerOfTwo(int n)
    {
        return n > 0 && 1073741824 % n == 0;
    }*/

    /* An alternative using bit manipulation
    public boolean isPowerOfTwo(int n)
    {
        while (n > 1 && (n & 1) == 0)
        {
            n /= 2;
        }

        return n == 1;
    }*/

    /* An alternative
    public boolean isPowerOfTwo(int n)
    {
        while (n > 1 && n % 2 == 0)
        {
            n /= 2;
        }

        return n == 1;
    }*/
}
