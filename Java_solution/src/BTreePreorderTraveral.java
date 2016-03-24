import java.util.ArrayList;
import java.util.List;

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
}
