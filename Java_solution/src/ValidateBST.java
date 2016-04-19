import java.util.Stack;

/**
 * Created by oscar on 4/6/16.
 */
public class ValidateBST
{
    // A fast, recommended solution
    private Integer previous = null;

    public boolean isValidBST(TreeNode root)
    {
        if (root == null)
        {
            return true;
        }

        if (!isValidBST(root.left))
        {
            return false;
        }

        if (previous != null && previous >= root.val)
        {
            return false;
        }

        previous = root.val;

        return isValidBST(root.right);
    }

    /* My solution
    public boolean isValidBST(TreeNode root)
    {
        return inOrderTraversal(root, new Stack<>());
    }

    private boolean inOrderTraversal(TreeNode node, Stack<Integer> stack)
    {
        if (node == null)
        {
            return true;
        }

        boolean leftCompare = inOrderTraversal(node.left, stack);
        boolean compare = stack.isEmpty() || stack.pop() < node.val;
        stack.push(node.val);
        boolean rightCompare = inOrderTraversal(node.right, stack);

        return leftCompare && compare && rightCompare;
    }*/
}
