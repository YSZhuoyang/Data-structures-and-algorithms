import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic idea is that each array (a1, a2 ... an) can be divided
 * into two sub arrays a1 (a1), and a2 (a2 ... an)
 */
public class SubsetsII
{
    // My solution, exceeded time limit
    public List<List<Integer>> subsetsWithDup(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> self = new ArrayList<>();

        Arrays.sort(nums);

        for (int num : nums)
        {
            self.add(num);
        }

        // Add itself and an empty set
        result.add(self);
        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++)
        {
            recursivelyEnumerate(result, self, i);
        }

        return result;
    }

    public void recursivelyEnumerate(List<List<Integer>> result, ArrayList<Integer> nums, int index)
    {
        ArrayList<Integer> newNumsB = new ArrayList<>(1);
        newNumsB.add(nums.get(index));

        ArrayList<Integer> newNumsA = new ArrayList<>(nums);
        newNumsA.remove(index);

        // Check duplicates
        if (!newNumsA.isEmpty() && !containSubset(result, newNumsA))
        {
            result.add(newNumsA);
        }

        if (!newNumsA.isEmpty() && !containSubset(result, newNumsB))
        {
            result.add(newNumsB);
        }

        for (int i = 0; i < newNumsA.size(); i++)
        {
            recursivelyEnumerate(result, newNumsA, i);
        }
    }

    public boolean containSubset(List<List<Integer>> result, ArrayList<Integer> newNums)
    {
        for (List<Integer> sub : result)
        {
            if (sub.size() == newNums.size())
            {
                for (int i = 0; i < sub.size(); i++)
                {
                    if (sub.get(i) != newNums.get(i))
                    {
                        break;
                    }
                    else if (i == sub.size() - 1)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Accepted solution as a good reference
    private class SubsetsIIRef
    {
        private List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsetsWithDup(int[] nums)
        {
            Arrays.sort(nums);
            addSubset(nums, 0, new ArrayList<>());

            return res;
        }

        private void addSubset(int[] nums, int i, List<Integer> b)
        {
            res.add(b);

            for (int j = i; j < nums.length; j++)
            {
                List<Integer> c = new ArrayList<>(b);

                if (j == i || (j > i && nums[j] != nums[j - 1]))
                {
                    c.add(nums[j]);
                    addSubset(nums, j + 1, c);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        SubsetsII s = new SubsetsII();
        int[] num = {0, 2, 1, 3, 4, 7, 4, 5, 9};

        s.subsetsWithDup(num);
    }
}
