/**
 * Created by oscar on 4/5/16.
 */
public class PowerOfThree
{

    // A recommended solution
    public boolean isPowerOfThree(int n)
    {
        return ( n > 0 && 1162261467 % n == 0);
    }
}
