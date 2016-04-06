import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by oscar on 3/24/16.
 */
public class BTreePostorderTraveral
{

    // My solution which beats 70% java submissions
    public List<Integer> postorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();

        postorderTraversal(result, root);

        return result;
    }

    private void postorderTraversal(List<Integer> list, TreeNode node)
    {
        if (node != null)
        {
            postorderTraversal(list, node.left);
            postorderTraversal(list, node.right);

            list.add(node.val);
        }
    }

    // My solution using a stack
    /*public List<Integer> postorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root != null)
        {
            stack.push(root);
            postorderTraversal(result, stack);
        }

        return result;
    }

    private void postorderTraversal(List<Integer> list, Stack<TreeNode> stack)
    {
        if (!stack.isEmpty())
        {
            TreeNode node = stack.pop();

            if (node.left != null)
            {
                stack.push(node.left);
            }

            if (node.right != null)
            {
                stack.push(node.right);
            }

            postorderTraversal(list, stack);
            list.add(node.val);
        }
    }*/
}
