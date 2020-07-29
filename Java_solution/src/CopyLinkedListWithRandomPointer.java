import java.util.HashMap;

/**
 * Given a linked list where each node contains a next
 * pointer, and a random pointer pointing to any node in
 * the list or null. Write a function that returns a copy
 * of the given list. (Assume that given list never have
 * cycles)
 */
public class CopyLinkedListWithRandomPointer
{
	HashMap<RandomListNode,RandomListNode> memo = new HashMap<>();

	public RandomListNode copyRandomList(RandomListNode node)
	{
		if (node == null)
		{
			return null;
		}

		if (memo.containsKey(node))
		{
			return memo.get(node);
		}

		RandomListNode copy = new RandomListNode(node.label);
		memo.put(node, copy);

		copy.next = copyRandomList(node.next);
		copy.random = copyRandomList(node.random);

		return copy;
	}
}
