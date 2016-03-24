import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 3/23/16.
 */
public class BinaryTreePath
{

    // My solution which beats 74.79% java submissions
    public List<String> binaryTreePaths(TreeNode root)
    {
        ArrayList<String> results = new ArrayList<>();

        if (root == null)
        {
            return results;
        }

        String path = "";
        depthFirstTraversal(root, path, results);

        return results;
    }

    public void depthFirstTraversal(TreeNode node, String path, List<String> results)
    {
        if (!path.isEmpty())
        {
            path += "->" + node.val;
        }
        else
        {
            path += node.val;
        }

        if (node.left != null)
        {
            depthFirstTraversal(node.left, path, results);
        }

        if (node.right != null)
        {
            depthFirstTraversal(node.right, path, results);
        }

        if (node.left == null && node.right == null)
        {
            results.add(path);
        }
    }
}


