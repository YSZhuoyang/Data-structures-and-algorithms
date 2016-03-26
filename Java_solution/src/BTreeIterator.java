import java.util.ArrayDeque;
import java.util.Stack;

/**
 * Created by oscar on 3/26/16.
 */
public class BTreeIterator
{
    private Stack<TreeNode> stack;
    private ArrayDeque<TreeNode> queue;

    // My solution which beats 80.53% java submissions
    public BTreeIterator(TreeNode root)
    {
        queue = new ArrayDeque<>();

        buildList(root);
    }

    private void buildList(TreeNode node)
    {
        if (node != null)
        {
            buildList(node.left);
            queue.add(node);
            buildList(node.right);
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext()
    {
        return !queue.isEmpty();
    }

    /** @return the next smallest number */
    public int next()
    {
        return queue.pop().val;
    }

    // A common solution using a stack
    /*public BTreeIterator(TreeNode root)
    {
        stack = new Stack<>();

        while (root != null)
        {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext()
    {
        return !stack.isEmpty();
    }

    public int next()
    {
        TreeNode node = stack.pop();
        int result = node.val;

        if (node.right != null)
        {
            node = node.right;

            while (node != null)
            {
                stack.push(node);
                node = node.left;
            }
        }

        return result;
    }*/
}
