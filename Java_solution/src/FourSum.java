import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oscar on 3/13/16.
 */
public class FourSum
{
    public List<List<Integer>> fourSum(int[] nums, int target)
    {
        // Each element has a pair of numbers and their sum
        int[][] pairs = new int[nums.length * (nums.length - 1) / 2][3];
        LinkedList<List<Integer>> results = new LinkedList<>();
        int index = 0;

        // Compute and initialize the pairs of sum of two array
        for (int i = 0; i < nums.length; i++)
        {
            // Avoid duplication
            for (int j = i + 1; j < nums.length; j++)
            {
                pairs[index][0] = nums[i];
                pairs[index][1] = nums[j];
                pairs[index][2] = nums[i] + nums[j];

                index++;
            }
        }

        // Sort pairs array


        // Find the two elements from the pairs array
        HashMap<Integer, Integer> map = new HashMap<>();
        int indexInPairs;
        LinkedList<Integer> result;

        for (int i = 0; i < pairs.length; i++)
        {
            if (map.containsKey(pairs[i][2]))
            {
                indexInPairs = map.get(pairs[i][2]);

                result = new LinkedList<>();
                result.add(pairs[indexInPairs][0]);
                result.add(pairs[indexInPairs][1]);
                result.add(pairs[i][0]);
                result.add(pairs[i][1]);

                // Sort result


                results.add(result);
            }
            else
            {
                map.put(target - pairs[i][2], i);
            }
        }

        return results;
    }

    public void sortArray(int[][] pairs)
    {
        int[][] left;
        int[][] right;


    }
}
