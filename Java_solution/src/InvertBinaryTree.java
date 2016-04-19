/**
 * Created by oscar on 3/24/16.
 */
public class InvertBinaryTree
{
    public TreeNode invertTree(TreeNode root)
    {
        invert(root);

        return root;
    }

    private void invert(TreeNode node)
    {
        if (node != null)
        {
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            invert(node.left);
            invert(node.right);
        }
    }
}
