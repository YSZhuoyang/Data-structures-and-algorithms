/**
 * Created by oscar on 3/29/16.
 */
public class IsSameTree
{

    // My solution using DFT
    public boolean isSameTree(TreeNode p, TreeNode q)
    {
        if (p == null)
        {
            return q == null;
        }
        else
        {
            return q != null && p.val == q.val &&
                    isSameTree(p.left, q.left) &&
                    isSameTree(p.right, q.right);
        }

        /* Same as:
        if (p == null)
        {
            return q == null;
        }
        else if (q == null)
        {
            return p == null;
        }
        else
        {
            return p.val == q.val &&
                    isSameTree(p.left, q.left) &&
                    isSameTree(p.right, q.right);
        }*/
    }
}
