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
        if (root == null)
            return new ArrayList<List<Integer>>();
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        dfs(root, res, p, 0, sum);
        
        return res;
    }

    private void dfs(TreeNode n, List<List<Integer>> res, List<Integer> p, int v, int sum)
    {
        if (n.left == null && n.right == null)
        {
            if (v + n.val == sum)
            {
                p.add(n.val);
                res.add(p);
            }
            
            return;
        }
        else
        {
            v += n.val;
            p.add(n.val);
            
            if (n.right != null)
            {
                List<Integer> p2 = new ArrayList<>(p);
                dfs(n.right, res, p2, v, sum);
            }
            
            if (n.left != null)
                dfs(n.left, res, p, v, sum);
        }
    }
}
