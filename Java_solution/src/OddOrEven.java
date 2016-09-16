/**
 * Created by oscar on 4/4/16.
 */
public class OddOrEven
{

    // Not a Leetcode solution, but solve an common problem using bit manipulation,
    // It can also be used to check if a bit is set by giving the position
    // Return true if input is an odd integer, otherwise return false
    public boolean isOddOrEven(int n)
    {
        return (n & 1) != 0;
    }
}
