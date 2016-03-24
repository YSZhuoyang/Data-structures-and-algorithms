import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 3/24/16.
 */
public class BTreeInorderTraversal
{

    public List<Integer> inorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();

        inorderTraversal(result, root);

        return result;
    }

    private void inorderTraversal(List<Integer> list, TreeNode node)
    {
        if (node != null)
        {
            inorderTraversal(list, node.left);
            list.add(node.val);
            inorderTraversal(list, node.right);
        }
    }
}
