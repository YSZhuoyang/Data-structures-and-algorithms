/**
 * Created by oscar on 7/6/16.
 */
public class ConvertSortedArraytoBalancedBST
{

	// My DFS solution with a time complexity of O(N)
	public TreeNode sortedArrayToBST(int[] nums)
	{
		if (nums == null || nums.length == 0)
		{
			return null;
		}

		return binaryInsert(nums, 0, nums.length - 1);
	}

	private TreeNode binaryInsert(int[] nums, int start, int end)
	{
		if (end < start)
		{
			return null;
		}
		else if (end == start)
		{
			return new TreeNode(nums[start]);
		}

		int middle = (start + end) / 2;

		TreeNode node = new TreeNode(nums[middle]);
		node.left = binaryInsert(nums, start, middle - 1);
		node.right = binaryInsert(nums, middle + 1, end);

		return node;
	}
}
