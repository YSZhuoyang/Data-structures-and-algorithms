import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 3/30/16.
 */
public class PathSumII
{

    // My solution
    public List<List<Integer>> pathSum(TreeNode root, int sum)
    {
        ArrayList<List<Integer>> results = new ArrayList<>();

        if (root != null)
        {
            ArrayList<Integer> newList = new ArrayList<>();
            depthTraversal(root, results, newList, 0, sum);
        }

        return results;
    }

    private void depthTraversal(TreeNode node, ArrayList<List<Integer>> lists, ArrayList<Integer> list, int sum, int target)
    {
        if (node.left == null & node.right == null)
        {
            if (sum + node.val == target)
            {
                list.add(node.val);
                lists.add(list);
            }
        }
        else if (node.left != null && node.right != null)
        {
            list.add(node.val);
            ArrayList<Integer> newList = (ArrayList<Integer>) list.clone();

            depthTraversal(node.right, lists, newList, sum + node.val, target);
            depthTraversal(node.left, lists, list, sum + node.val, target);
        }
        else if (node.left == null)
        {
            list.add(node.val);
            depthTraversal(node.right, lists, list, sum + node.val, target);
        }
        else
        {
            list.add(node.val);
            depthTraversal(node.left, lists, list, sum + node.val, target);
        }
    }
}
