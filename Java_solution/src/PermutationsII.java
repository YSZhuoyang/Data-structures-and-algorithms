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
}
