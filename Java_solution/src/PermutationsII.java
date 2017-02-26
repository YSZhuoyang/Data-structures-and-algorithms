import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 6/3/16.
 */
public class PermutationsII
{

	// A recommended solution that extended the idea of recursive swapping,
	// with a duplication check.
	public List<List<Integer>> permuteUnique(int[] nums)
	{
		List<List<Integer>> lists = new ArrayList<>();

		if (nums == null || nums.length == 0)
		{
			return lists;
		}

		buildTree(lists, nums, 0);

		return lists;
	}

	private void buildTree(List<List<Integer>> lists, int[] nums, int start)
	{
		if (start == nums.length - 1)
		{
			List<Integer> list = new ArrayList<>(nums.length);

			for (int ele : nums)
			{
				list.add(ele);
			}

			lists.add(list);

			return;
		}

		for (int i = start; i < nums.length; i++)
		{
			if (duplicated(nums, start, i))
			{
				continue;
			}

			swap(nums, start, i);
			buildTree(lists, nums, start + 1);
			swap(nums, i, start);
		}
	}

	private boolean duplicated(int nums[], int start, int current)
	{
		for (int i = start; i < current; i++)
		{
			if (nums[i] == nums[current])
			{
				return true;
			}
		}

		return false;
	}

	private void swap(int[] nums, int a, int b)
	{
		if (a == b)
		{
			return;
		}

		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	/*
	 * An extended version based on a DFS backtrack solution
	 * that recursively adding new elements.
	public List<List<Integer>> permuteUnique(int[] nums)
	{
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0)
            return res;

        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), res);

        return res;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> l, List<List<Integer>> res)
    {
        int size = l.size();

        if (size == nums.length)
        {
            res.add(new ArrayList<>(l));
            return;
        }

        for (int i = 0; i < nums.length; i++)
        {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]))
                continue;

            l.add(nums[i]);
            used[i] = true;
            backtrack(nums, used, l, res);
            l.remove(size);
            used[i] = false;
        }
    }
	 */
}
