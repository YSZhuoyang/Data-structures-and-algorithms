import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic idea is that each array (a1, a2 ... an) can be divided
 * into two sub arrays a1 (a1), and a2 (a2 ... an)
 */
public class SubsetsII
{

	// My second solution using BTree construction (DFS),
	// which beats 83% java submissions
	public List<List<Integer>> subsetsWithDup(int[] nums)
	{
		List<List<Integer>> res = new ArrayList<>();

		if (nums == null || nums.length == 0)
		{
			return res;
		}

		Arrays.sort(nums);

		buildTree(res, new ArrayList<>(), nums, 0, 0);

		return res;
	}

	// The attribute level is equal to the length of lists added in this level of the tree,
	// right is the start index for adding new elements
	private void buildTree(List<List<Integer>> lists, List<Integer> list, int[] nums, int level, int right)
	{
		if (level == 0)
		{
			lists.add(list);
			buildTree(lists, list, nums, 1, 0);

			return;
		}

		for (int i = right; i < nums.length; i++)
		{
			if (i > right && nums[i] == nums[i - 1])
			{
				continue;
			}

			// Duplicate old elements into a new list
			List<Integer> newList = new ArrayList<>();
			newList.addAll(list);
			newList.add(nums[i]);
			lists.add(newList);

			buildTree(lists, newList, nums, level + 1, i + 1);
		}
	}

    /* My first solution, which exceeds time limit
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
    }*/

    /* Accepted solution as a good reference
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
    }*/
}
