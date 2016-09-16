/**
 * Created by oscar on 4/4/16.
 */
public class NumberOfOneBits
{

    // A recommended solution
    // Idea:
    // Consider the expression 'n & (n - 1)' through
    // an example where:
    // n = abcde1000, then n - 1 = abcde0111,
    // thus we are able to know two things:
    // 1. n & (n - 1) == 0 means abcde must all be 0s thus n is a power of 2.
    // 2. By doing 'n & (n - 1)', we are able to eliminate one 1 bit (the above
    // example n becomes abcde0000)
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
