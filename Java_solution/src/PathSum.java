/**
 * Created by oscar on 3/29/16.
 */
public class PathSum
{

    // My solution using DFT
    public boolean hasPathSum(TreeNode root, int sum)
    {
        if (root == null) return false;
        
        return dfs(root, 0, sum);
    }

    private boolean dfs(TreeNode n, int v, int s)
    {
        if (n.left == null && n.right == null)
            return v + n.val == s;
        
        v += n.val;
        
        return n.left != null && dfs(n.left, v, s) ||
            n.right != null && dfs(n.right, v, s);
    }
}
