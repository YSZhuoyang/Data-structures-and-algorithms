import java.util.HashMap;

/**
 * Created by oscar on 5/8/16.
 */
public class HouseRobber
{
	HashMap<Integer, Integer> memo = new HashMap<>();

	// My solution using dynamic programming (note: when using dynamic programming,
	// the graph must be acyclic, check base cases like 0, 1, or 2 sometimes,
	// and check null, empty cases in the beginning)
	public int rob(int[] nums)
	{
		if (nums == null || nums.length == 0)
		{
			return 0;
		}
		else if (nums.length == 1)
		{
			return nums[0];
		}

		return rob(nums, nums.length - 1);
	}

	private int rob(int[] nums, int end)
	{
		if (end == 0)
		{
			return nums[0];
		}
		else if (end == 1)
		{
			return nums[1] > nums[0] ? nums[1] : nums[0];
		}
		else if (end == 2)
		{
			int resultA = nums[1];
			int resultB = nums[0] + nums[2];

			return resultA > resultB ? resultA : resultB;
		}
		else
		{
			int resultA;
			int resultB;

			int optionA = end;
			int optionB = end - 1;

			if (memo.containsKey(optionA))
			{
				resultA = memo.get(optionA);
			}
			else
			{
				resultA = rob(nums, optionA - 2) + nums[optionA];

				memo.put(optionA, resultA);
			}

			if (memo.containsKey(optionB))
			{
				resultB = memo.get(optionB);
			}
			else
			{
				resultB = rob(nums, optionB - 2) + nums[optionB];

				memo.put(optionB, resultB);
			}

			return resultA > resultB ? resultA : resultB;
		}
	}
}
