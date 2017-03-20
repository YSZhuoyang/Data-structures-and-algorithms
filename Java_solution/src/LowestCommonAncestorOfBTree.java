/**
 * Created by oscar on 6/1/16.
 *
 * Idea: do a post order traversal.
 * Two steps optimization:
 * 1. Cache at each node during the traversal, but to do that requires
 *    O(N) extra space.
 * 2. As we do post-order DFS, we only need to keep the cache at
 *    its directly connected children of the current node. To do
 *    that, we keep updating the references to the parent of P and
 *    the parent of Q.
 */
public class LowestCommonAncestorOfBTree
{
	TreeNode parentOfP;
	TreeNode parentOfQ;

	// My second solution using DFS and dynamic programming with O(N)
	// time complexity and O(1) space complexity. (Beat 76% java submissions)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null || p == null || q == null)
		{
			return null;
		}

		TreeNode lca = lowestCommonAncestor(root.left, p, q);

		if (lca != null)
		{
			return lca;
		}

		lca = lowestCommonAncestor(root.right, p, q);

		if (lca != null)
		{
			return lca;
		}

		if ((parentOfP != null && (parentOfP == root.left || parentOfP == root.right)) || root == p)
		{
			parentOfP = root;
		}

		if ((parentOfQ != null && (parentOfQ == root.left || parentOfQ == root.right)) || root == q)
		{
			parentOfQ = root;
		}

		if (parentOfP == root && parentOfQ == root)
		{
			return root;
		}
		else
		{
			return null;
		}
	}

	// My first solution using naive DFS
	/*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) return null;

		TreeNode l = lowestCommonAncestor(root.left, p, q);
		if (l != null) return l;

		TreeNode r = lowestCommonAncestor(root.right, p, q);
		if (r != null) return r;

		int numFound = dfs(root, p, q);
		if (numFound == 2) return root;

		return null;
	}

	private int dfs(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null) return 0;

		int numFound = 0;
		if (root == p || root == q) numFound++;
		numFound += dfs(root.left, p, q);
		if (numFound < 2)
		    numFound += dfs(root.right, p, q);

		return numFound;
	}*/

	/* Similar idea to my second solution, except it assumes that both p and q
	must exist in the tree.
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		if(root == null)
		{
			return null;
		}

		if(root == p || root == q)
		{
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		return left != null && right != null ? root : left == null ? right : left;
	}*/
}
