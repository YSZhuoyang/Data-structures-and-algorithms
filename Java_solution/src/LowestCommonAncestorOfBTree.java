/**
 * Created by oscar on 6/1/16.
 */
public class LowestCommonAncestorOfBTree
{

	// My solution
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null || p == null || q == null)
		{
			return null;
		}

		TreeNode leftFound = lowestCommonAncestor(root.left, p, q);
		TreeNode rightFound = lowestCommonAncestor(root.right, p, q);

		if (leftFound != null)
		{
			return leftFound;
		}
		else if (rightFound != null)
		{
			return rightFound;
		}
		else if (hasChild(root, p) && hasChild(root, q))
		{
			return root;
		}

		return null;
	}

	private boolean hasChild(TreeNode root, TreeNode target)
	{
		if (root == null)
		{
			return false;
		}

		if (root == target)
		{
			return true;
		}
		else if (hasChild(root.left, target))
		{
			return true;
		}
		else if (hasChild(root.right, target))
		{
			return true;
		}

		return false;
	}

	/* Assuming p and q must exist in the tree (not a good idea)
	// A faster solution.
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
