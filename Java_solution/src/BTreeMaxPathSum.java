/**
 * Created by oscar on 7/11/16.
 *
 * Idea:
 * 1. Finding all paths within the tree, using Post-Order DFS.
 * 2. See example below:
 *
 *               ...
 *              /   \
 *         subRoot
 *         /    \
 *     nodeA    nodeB
 *     /  \     /  \
 *   ...  ... ...  ...
 *
 * If we want to find the maximum path sum of the above sub-tree (starting
 * from the subRoot), we only need to consider the below 4 cases:
 * 1. Sum of pathA with the maximum sum starting from nodeA plus the value of subRoot.
 * 2. Sum of pathB with the maximum sum starting from nodeB plus the value of subRoot.
 * 3. Sum of pathD composed of PathA - subRoot - PathB.
 * 4. Value of the subRoot node.
 *
 * Thus during the DFS,
 * 1. recursively choose the biggest one from case 1, 2 and 3 as the maximum path
 * sum starting from the sub-root node and return it.
 * 2. compare case 1, 2, 3, 4 and choose the biggest one as the maximum path sum.
 */
public class BTreeMaxPathSum
{
	private int max = -2147483648;

	// My second solution with O(N) time complexity and
	// O(1) extra space
	public int maxPathSum(TreeNode root)
	{
		if (root == null)
		{
			return 0;
		}

		dfs(root);

		return max;
	}

	private int dfs(TreeNode node)
	{
		if (node == null)
		{
			return 0;
		}

		// Path A with the maximum sum
		int lPathSum = dfs(node.left);
		// Path B with the maximum sum
		int rPathSum = dfs(node.right);

		int leftSum = lPathSum + node.val;
		int rightSum = rPathSum + node.val;
		// Path A - root - PathB
		int leftAndRight = lPathSum + node.val + rPathSum;

		int returnSum = Math.max(Math.max(leftSum, rightSum), node.val);
		max = Math.max(Math.max(returnSum, leftAndRight), max);

		return returnSum;
	}

	/* My first solution enumerating all paths within the tree.
	public int maxPathSum(TreeNode root)
    {
        if (root == null)
        {
            return 0;
        }

        dfs(root);

        return max;
    }

    private ArrayDeque<Integer> dfs(TreeNode node)
    {
        ArrayDeque<Integer> pathSet = new ArrayDeque<>();

        if (node == null)
        {
            return pathSet;
        }

        ArrayDeque<Integer> lPathSet = dfs(node.left);
        ArrayDeque<Integer> rPathSet = dfs(node.right);
        int sum;

        for (int l : lPathSet)
        {
            // Update left
            sum = l + node.val;
            findMax(sum);
            pathSet.add(sum);

            // Connect left paths and right paths
            for (int r : rPathSet)
            {
                sum = l + node.val + r;
                findMax(sum);
            }
        }

        // Update right
        for (int r : rPathSet)
        {
            sum = r + node.val;
            findMax(sum);
            pathSet.add(sum);
        }

        findMax(node.val);
        pathSet.add(node.val);

        return pathSet;
    }

    private void findMax(int sum)
    {
        if (sum > max)
        {
            max = sum;
        }
    }*/
}
