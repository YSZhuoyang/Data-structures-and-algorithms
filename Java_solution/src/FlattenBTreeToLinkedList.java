import java.util.Stack;

/**
 * Created by oscar on 6/1/16.
 */
public class FlattenBTreeToLinkedList
{

	// My first solution using recursive DFS which is a lot faster
	public void flatten(TreeNode root)
	{
		if (root == null || (root.left == null && root.right == null))
		{
			return;
		}

		TreeNode flattened = new TreeNode(0);
		buildList(flattened, root);

		root.val = flattened.right.val;
		root.right = flattened.right.right;
		root.left = null;
	}

	private void buildList(TreeNode flattened, TreeNode node)
	{
		if (node == null)
		{
			return;
		}

		flattened.right = new TreeNode(node.val);
		buildList(flattened.right, node.left);

		// Find the tail of the flattened tree
		TreeNode pointer = flattened.right;

		while (pointer.right != null)
		{
			pointer = pointer.right;
		}

		buildList(pointer, node.right);
	}

	// My second solution using iterative DFS with a stack
	/*public void flatten(TreeNode root)
	{
		if (root == null || (root.left == null && root.right == null))
		{
			return;
		}

		TreeNode flattened = new TreeNode(0);

		TreeNode flattenedIterator = flattened;
		TreeNode treeIterator = root;

		Stack<TreeNode> stack = new Stack<>();
		stack.push(treeIterator);

		while (!stack.isEmpty())
		{
			treeIterator = stack.pop();
			flattenedIterator.right = new TreeNode(treeIterator.val);
			flattenedIterator = flattenedIterator.right;

			if (treeIterator.right != null)
			{
				stack.push(treeIterator.right);
			}

			if (treeIterator.left != null)
			{
				stack.push(treeIterator.left);
			}
		}

		root.val = flattened.right.val;
		root.right = flattened.right.right;
		root.left = null;
	}*/
}
