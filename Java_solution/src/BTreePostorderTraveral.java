import java.util.ArrayList;
import java.util.List;

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
}
