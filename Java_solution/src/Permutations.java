import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 6/2/16.
 */
public class Permutations
{

	// A faster solution using recursive swapping (DFS)
	// Details see: http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
	public List<List<Integer>> permute(int[] nums)
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
			swap(nums, start, i);
			buildTree(lists, nums, start + 1);
			swap(nums, i, start);
		}
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

	/* My solution using recursive adding new element (DFS)
	public List<List<Integer>> permute(int[] nums)
	{
		List<List<Integer>> lists = new ArrayList<>();

		if (nums == null || nums.length == 0)
		{
			return lists;
		}

		List<Integer> list;

		for (int i = 0; i < nums.length; i++)
		{
			list = new ArrayList<>(nums.length);
			list.add(nums[i]);
			addNumber(lists, list, nums);
		}

		return lists;
	}

	private void addNumber(List<List<Integer>> lists, List<Integer> list, int[] nums)
	{
		if (list.size() == nums.length)
		{
			lists.add(list);

			return;
		}

		for (int i = 0; i < nums.length; i++)
		{
			if (list.contains(nums[i]))
			{
				continue;
			}

			List<Integer> newList = new ArrayList<>(list);
			newList.add(nums[i]);
			addNumber(lists, newList, nums);
		}
	}

	// Another DFS backtrack solution that recursively adding new elements
	public List<List<Integer>> permute(int[] nums)
	 {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0)
            return res;

        boolean[] used = new boolean[nums.length];
        buildTree(nums, used, new ArrayList<Integer>(), res);

        return res;
    }

    private void buildTree(int[] nums, boolean[] used, List<Integer> l, List<List<Integer>> res)
    {
        int size = l.size();

        if (size == nums.length)
        {
            res.add(new ArrayList<Integer>(l));
            return;
        }

        for (int i = 0; i < nums.length; i++)
        {
            if (used[i]) continue;

            l.add(nums[i]);
            used[i] = true;
            buildTree(nums, used, l, res);
            l.remove(size);
            used[i] = false;
        }
    }*/
}
