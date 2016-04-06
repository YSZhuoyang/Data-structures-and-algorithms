import java.util.HashMap;

/**
 * Created by oscar on 3/3/16.
 */
public class ClimbingStairs
{
    // My solution using dynamic programming
    private HashMap<Integer, Integer> memo = new HashMap<>();

    public int climbStairs(int n)
    {
        switch (n)
        {
            case 0:
                return 0;

            case 1:
                return 1;

            case 2:
                return 2;

            default:
                int numPathA;
                int numPathB;

                // There is no need to check for number of paths of n - 1 steps
                // since it is always computed in the first time so it won't be
                // found anyway.
                numPathA = climbStairs(n - 1);
                memo.put(n - 1, numPathA);

                if (memo.containsKey(n - 2))
                {
                    numPathB = memo.get(n - 2);
                }
                else
                {
                    numPathB = climbStairs(n - 2);
                    memo.put(n - 2, numPathB);
                }

                // There are two ways through path A and path B respectively:
                // 1. path A plus 1 step.
                // 2. path B plus 2 steps.
                return numPathA + numPathB;
        }
    }
}
