/**
 * Created by oscar on 3/28/16.
 */
public class BTreeIsSymmetric
{

    // A recommended solution
    public boolean isSymmetric(TreeNode root)
    {
        return isSymmetric(root,root);
    }

    private boolean isSymmetric(TreeNode p, TreeNode q)
    {
        if (p==null && q==null)
        {
            return true;
        }

        if (p==null || q==null)
        {
            return false;
        }

        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }
}
