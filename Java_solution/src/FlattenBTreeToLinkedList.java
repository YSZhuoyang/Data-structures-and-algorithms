import java.util.Stack;

/**
 * Created by oscar on 6/1/16.
 *
 * Assumption: elements within the tree are sorted in pre-order.
 */
public class FlattenBTreeToLinkedList
{

	// My third solution using DFS which is simpler and faster, with no extra space used
	public void flatten(TreeNode root)
	{
		if (root == null)
		{
			return;
		}

		flatten(root.left);
		flatten(root.right);

		// Re-connect current node with the flattened left subtree as its right
		// child and cut off the connection with its left child
		TreeNode rightSubtree = root.right;
		root.right = root.left;
		root.left = null;

		// Go to the right most node
		TreeNode rightMost = root;

		while (rightMost.right != null)
		{
			rightMost = rightMost.right;
		}

		// Re-connect the right most node with the flattened right subtree
		rightMost.right = rightSubtree;
	}

	// My first solution using recursive DFS which is a lot faster,
	// but requires extra space
	/*public void flatten(TreeNode root)
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
	}*/

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
