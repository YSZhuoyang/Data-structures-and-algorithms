import java.util.HashMap;
import java.util.Map;

/**
 * Created by oscar on 2/20/16.
 */
public class TwoSum
{

    // A faster recommended solution
    public int[] twoSum(int[] nums, int target)
    {
        int[] result = {0, 0};
        Map<Integer, Integer> map = new HashMap<>();

        /*
         * For every entry in the array, store the (target - num) into the map with the index of the num
         * When the (target - num) appears in the array, we can return the values at that moment itself
         */
        for (int i = 0; i < nums.length; i++)
        {
            if (map.containsKey(nums[i]))
            {
                result[0] = map.get(nums[i]);
                result[1] = i;

                return result;
            }
            else
            {
                map.put(target - nums[i], i);
            }
        }

        return result;
    }

    /* My solution using naive backtracking
    public int[] twoSum(int[] nums, int target)
    {
        int[] solution = {0, 0};

        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                if (nums[i] + nums[j] == target)
                {
                    solution[0] = i;
                    solution[1] = j;

                    return solution;
                }
            }
        }

        return solution;
    }*/
}
