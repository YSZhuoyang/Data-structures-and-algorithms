import java.text.Collator;
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
        // total number of pairs: (1 + (n - 1)) * n / 2
        int[][] pairs = new int[nums.length * (nums.length - 1) / 2][3];
        LinkedList<List<Integer>> results = new LinkedList<>();
        int index = 0;

        // Compute and initialize the pairs of sum of two array
        for (int i = 0; i < nums.length; i++)
        {
            // Avoid duplication
            for (int j = i + 1; j < nums.length; j++)
            {
                pairs[index][0] = i;
                pairs[index][1] = j;
                pairs[index][2] = nums[i] + nums[j];

                index++;
            }
        }

        // Sort pairs array


        // Find the two elements from the pairs array
        HashMap<Integer, Integer> map = new HashMap<>();
        int indexInPairs;
        LinkedList<Integer> result;

        for (int i = 0; i < pairs.length - 1; i++)
        {
            for (int j = i + 1; j < pairs.length; j++)
            {
                if (pairs[i][2] + pairs[j][2] == target)
                {
                    result = new LinkedList<>();

                    if (pairs[j][0] != pairs[i][0] &&
                            pairs[j][0] != pairs[i][1] &&
                            pairs[j][1] != pairs[i][0] &&
                            pairs[j][1] != pairs[i][1])
                    {
                        result.add(nums[pairs[j][0]]);
                        result.add(nums[pairs[j][1]]);
                        result.add(nums[pairs[i][0]]);
                        result.add(nums[pairs[i][1]]);

                        // Sort result
                        result.sort((o1, o2) ->
                        {
                            if (o1 > o2)
                            {
                                return 1;
                            }
                            else if (o1 < o2)
                            {
                                return -1;
                            }
                            else
                            {
                                return 0;
                            }
                        });

                        // Remove duplicates
                        int x = 0;

                        while (x < results.size() &&
                                (results.get(x).get(0) != result.get(0) ||
                                results.get(x).get(1) != result.get(1) ||
                                results.get(x).get(2) != result.get(2) ||
                                results.get(x).get(3) != result.get(3)))
                        {
                            x++;
                        }

                        if (x == results.size())
                        {
                            results.add(result);
                        }
                    }
                }
            }
        }

        /*for (int i = 0; i < pairs.length; i++)
        {
            if (map.containsKey(pairs[i][2]))
            {
                indexInPairs = map.get(pairs[i][2]);

                result = new LinkedList<>();

                if (pairs[indexInPairs][0] != pairs[i][0] &&
                    pairs[indexInPairs][0] != pairs[i][1] &&
                    pairs[indexInPairs][1] != pairs[i][0] &&
                    pairs[indexInPairs][1] != pairs[i][1])
                {
                    result.add(nums[pairs[indexInPairs][0]]);
                    result.add(nums[pairs[indexInPairs][1]]);
                    result.add(nums[pairs[i][0]]);
                    result.add(nums[pairs[i][1]]);

                    // Sort result
                    result.sort((o1, o2) ->
                    {
                        if (o1 > o2)
                        {
                            return 1;
                        }
                        else if (o1 < o2)
                        {
                            return -1;
                        }
                        else
                        {
                            return 0;
                        }
                    });

                    // Remove duplicates
                    int x = 0;

                    while (x < results.size() &&
                            (results.get(x).get(0) != result.get(0) ||
                            results.get(x).get(1) != result.get(1) ||
                            results.get(x).get(2) != result.get(2) ||
                            results.get(x).get(3) != result.get(3)))
                    {
                        x++;
                    }

                    if (x == results.size())
                    {
                        results.add(result);
                    }
                }
            }
            else
            {
                map.put(target - pairs[i][2], i);
            }
        }*/

        return results;
    }
}