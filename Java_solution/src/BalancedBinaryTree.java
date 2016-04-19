import java.util.HashMap;

/**
 * Created by oscar on 3/18/16.
 */
public class BalancedBinaryTree
{
    HashMap<Integer, Integer> memo = new HashMap<>();

    // A good one with clear and easy to understand idea
    // helper function to find the depth of a tree from a given node
    /*public Integer depthOfTree (TreeNode root)
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

    // A very fast one (use height of -1 to represent not balance,
    // thus there is no repeated computing on the same path)
    /*public boolean isBalanced (TreeNode root)
    {
        return getHeight(root) != -1;
    }

    public int getHeight (TreeNode root)
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

    // My fucking slow solution
    public boolean isBalanced(TreeNode root)
    {
        boolean balanced;
        boolean leftBalance;
        boolean rightBalance;

        if (root == null)
        {
            return true;
        }

        //System.out.println("l: " + computeHeight(root.left));
        //System.out.println("r: " + computeHeight(root.right));

        balanced = Math.abs(computeHeight(root.left) - computeHeight(root.right)) <= 1;
        leftBalance = isBalanced(root.left);
        rightBalance = isBalanced(root.right);

        return balanced && leftBalance && rightBalance;
    }

    public int computeHeight(TreeNode node)
    {
        int left;
        int right;

        if (node == null)
        {
            return -2;
        }
        else
        {
            left = computeHeight(node.left) + 1;
            right = computeHeight(node.right) + 1;

            return Math.max(left, right);
        }
    }
}
