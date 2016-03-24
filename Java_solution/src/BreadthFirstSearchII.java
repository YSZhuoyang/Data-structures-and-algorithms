import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oscar on 3/22/16.
 */
public class BreadthFirstSearchII
{

    // My solution, store values of each level in bottom up order
    public List<List<Integer>> levelOrderBottom(TreeNode root)
    {
        LinkedList<List<Integer>> lists = new LinkedList<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        if (root != null)
        {
            queue.add(root);
            breadthFirstTraversal(lists, queue);
        }

        return lists;
    }

    private void breadthFirstTraversal(LinkedList<List<Integer>> lists, ArrayDeque<TreeNode> queue)
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

            lists.push(list);
            breadthFirstTraversal(lists, newQueue);
        }
    }
}
