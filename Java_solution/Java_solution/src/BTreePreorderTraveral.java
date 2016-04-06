import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by oscar on 3/24/16.
 */
public class BTreePreorderTraveral
{

    // My solution which beats 70% java submissions
    public List<Integer> preorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();

        preorderTraversal(result, root);

        return result;
    }

    private void preorderTraversal(List<Integer> list, TreeNode node)
    {
        if (node != null)
        {
            list.add(node.val);

            preorderTraversal(list, node.left);
            preorderTraversal(list, node.right);
        }
    }

    // My solution using a stack
    /*public List<Integer> preorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root != null)
        {
            stack.push(root);
            preorderTraversal(result, stack);
        }

        return result;
    }

    private void preorderTraversal(List<Integer> list, Stack<TreeNode> stack)
    {
        if (!stack.isEmpty())
        {
            TreeNode node = stack.pop();

            if (node.right != null)
            {
                stack.push(node.right);
            }

            if (node.left != null)
            {
                stack.push(node.left);
            }

            list.add(node.val);
            preorderTraversal(list, stack);
        }
    }*/
}
