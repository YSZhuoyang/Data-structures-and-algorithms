/**
 * Created by oscar on 3/30/16.
 */
public class MinDepth
{

    // My solution with DFT
    public int minDepth(TreeNode root)
    {
        if (root == null)
        {
            return 0;
        }

        return depthTraversal(root, 0);
    }

    private int depthTraversal(TreeNode node, int level)
    {
        level++;
        int left = level;
        int right = level;

        if (node.left != null)
        {
            left = depthTraversal(node.left, level);
        }

        if (node.right != null)
        {
            right = depthTraversal(node.right, level);
        }

        if (node.left == null && node.right == null)
        {
            return level;
        }
        else if (node.left == null)
        {
            return right;
        }
        else if (node.right == null)
        {
            return left;
        }
        else
        {
            if (left < right)
            {
                return left;
            }
            else
            {
                return right;
            }
        }
    }
}
