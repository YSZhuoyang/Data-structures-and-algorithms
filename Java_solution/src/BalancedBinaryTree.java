import java.util.HashMap;

/**
 * Created by oscar on 3/18/16.
 */
public class BalancedBinaryTree
{
    HashMap<TreeNode, Integer> memo = new HashMap<>();

    // My optimized solution using dynamic programming
    public boolean isBalanced(TreeNode root)
    {
        if (root == null)
        {
            return true;
        }
        else if (isBalanced(root.left) && isBalanced(root.right))
        {
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);

            return Math.abs(leftHeight - rightHeight) <= 1;
        }

        return false;
    }

    private int getHeight(TreeNode node)
    {
        if (node == null)
        {
            return -1;
        }
        else if (memo.get(node) != null)
        {
            return memo.get(node);
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        int height = Math.max(leftHeight, rightHeight) + 1;
        memo.put(node, height);

        return height;
    }

    // A good one with clear and easy to understand idea
    // helper function to find the depth of a tree from a given node
    /*public Integer depthOfTree(TreeNode root)
    {
        if(root == null)
            return 0;

        return 1 + Math.max(depthOfTree(root.left), depthOfTree(root.right));
    }

    public boolean isBalanced(TreeNode root)
    {
        if(root == null)
            return true;

        // find the depth on the left and right sub trees
        int lDepth = depthOfTree(root.left);
        int rDepth = depthOfTree(root.right);

        // if the difference is greater than 1 return false
        if (Math.abs(lDepth - rDepth) > 1)
        {
            return false;
        }

        // recur on every node of the tree (however same path will be
        // computed more than once)
        return isBalanced(root.left) && isBalanced(root.right);
    }*/

    // A very fast one (using height of -1 to represent not balance,
    // thus there is no repeated computing on the same path)
    /*public boolean isBalanced(TreeNode root)
    {
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode root)
    {
        if(root == null)
        {
            return 0;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
        {
            return -1;
        }
        else
        {
            return Math.max(left, right) + 1;
        }
    }*/
}
