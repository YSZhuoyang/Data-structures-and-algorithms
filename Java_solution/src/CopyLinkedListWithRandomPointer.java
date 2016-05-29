import java.util.HashMap;

/**
 * Created by oscar on 5/29/16.
 *
 * Given a linked list where each node contains a next
 * pointer, and a random pointer pointing to any node in
 * the list or null. Write a function that returns a copy
 * of the given list. (Assume that given list never have
 * cycles)
 *
 * Received from Microsoft interview.
 */
public class CopyLinkedListWithRandomPointer
{

	// My solution using iteration
	public RandomListNode copyList(RandomListNode head)
	{
		if (head == null)
		{
			return null;
		}

		RandomListNode copyHead = new RandomListNode(0);
		HashMap<RandomListNode, Integer> pointerIndexHash = new HashMap<>();
		HashMap<Integer, RandomListNode> indexPointerHash = new HashMap<>();

		RandomListNode iterator = head;
		RandomListNode copyIterator = copyHead;
		int index = 0;

		// First while loop which stores each node and and index (starts from 0)
		// into the pointerIndex hash map
		while (iterator != null)
		{
			copyIterator.next = new RandomListNode(iterator.label);

			pointerIndexHash.put(iterator, index);
			indexPointerHash.put(index, copyIterator.next);

			iterator = iterator.next;
			copyIterator = copyIterator.next;
			index++;
		}

		iterator = head;
		copyIterator = copyHead.next;

		// Second while loop which stores each index of the node that the random pointer
		// is pointing to and the node containing that random pointer into the
		// indexPointer hash map
		while (iterator != null)
		{
			if (iterator.random != null)
			{
				index = pointerIndexHash.get(iterator.random);
				copyIterator.random = indexPointerHash.get(index);
			}

			iterator = iterator.next;
			copyIterator = copyIterator.next;
		}

		return copyHead.next;
	}

	/* A faster and clean solution using recursion
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
	}*/
}
