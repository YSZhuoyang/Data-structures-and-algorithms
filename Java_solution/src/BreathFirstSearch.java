import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 3/22/16.
 */
public class BreathFirstSearch
{

    // My solution
    public List<List<Integer>> levelOrder(TreeNode root)
    {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        if (root != null)
        {
            queue.add(root);
            breadFirstTraversal(lists, queue);
        }

        return lists;
    }

    public void breadFirstTraversal(ArrayList<List<Integer>> lists, ArrayDeque<TreeNode> queue)
    {
        if (!queue.isEmpty())
        {
            ArrayList<Integer> list = new ArrayList<>();
            ArrayDeque<TreeNode> newQueue = new ArrayDeque<>();

            while (!queue.isEmpty())
            {
                TreeNode node = queue.pop();

                list.add(node.val);

                if (node.left != null)
                {
                    newQueue.add(node.left);
                }

                if (node.right != null)
                {
                    newQueue.add(node.right);
                }
            }

            lists.add(list);
            breadFirstTraversal(lists, newQueue);
        }
    }
}
