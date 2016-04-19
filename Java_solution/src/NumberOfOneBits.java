/**
 * Created by oscar on 4/4/16.
 */
public class NumberOfOneBits
{

    // A recommended solution
    public int hammingWeight(int n)
    {
        int total_ones = 0;

        while (n != 0)
        {
            n = n & (n - 1);
            total_ones++;
        }

        return total_ones;
    }
}
