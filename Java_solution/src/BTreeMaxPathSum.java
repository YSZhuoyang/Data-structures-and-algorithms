/**
 * Created by oscar on 7/11/16.
 *
 * Idea:
 * 1. Finding all paths within the tree, using Post-Order DFS.
 * 2. See example below:
 *
 *               ....
 *              /   \
 *         subRoot
 *         /    \
 *     nodeA    nodeB
 *     /  \     /  \
 *   ...  ... ...  ...
 *
 * If we want to find the maximum path of the above sub-tree (starting
 * from the subRoot), we only need to compare the below 4 cases:
 * 1. PathA with the maximum sum starting from nodeA.
 * 2. PathB with the maximum sum starting from nodeB.
 * 3. PathC that only contains the subRoot node.
 * 4. PathD composed of PathA - root - PathB.
 *
 * Thus during the traversal, we only need to choose the biggest one
 * from PathA, PathB and PathC and return it.
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

		int lPathSum = dfs(node.left);
		int rPathSum = dfs(node.right);

		int leftSum = lPathSum + node.val;
		int rightSum = rPathSum + node.val;
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
