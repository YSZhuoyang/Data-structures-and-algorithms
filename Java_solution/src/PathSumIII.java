import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by oscar on 12/03/17.
 */
public class PathSumIII
{
    // My solution (slow)
    public int pathSum(TreeNode root, int sum)
    {
        if (root == null) return 0;
        List<Integer> l = new ArrayList<>();
        return dfs(root, 0, sum, l);
    }

    private int dfs(TreeNode n, int ps, int sum, List<Integer> l)
    {
        if (n == null) return ps;
        else
        {
            for (final ListIterator<Integer> i = l.listIterator(); i.hasNext();)
            {
                int e = i.next();
                e += n.val;
                if (e == sum) ps++;
                i.set(e);
            }

            if (n.val == sum) ps++;
            l.add(n.val);
            List<Integer> l2 = new ArrayList<>(l);

            return dfs(n.left, ps, sum, l) + dfs(n.right, 0, sum, l2);
        }
    }
}
