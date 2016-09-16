/**
 * Created by oscar on 3/29/16.
 */
public class PathSum
{

    // My solution using DFT
    public boolean hasPathSum(TreeNode root, int sum)
    {
        return depthTraversal(root, 0, sum);
    }

    private boolean depthTraversal(TreeNode node, int sum, int target)
    {
        if (node != null && (node.left != null || node.right != null))
        {
            return depthTraversal(node.left, sum + node.val, target) ||
                    depthTraversal(node.right, sum + node.val, target);
        }
        else
        {
            return node != null && node.val + sum == target;
        }
    }
}
