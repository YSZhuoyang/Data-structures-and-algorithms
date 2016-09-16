import java.util.EmptyStackException;

/**
 * Created by oscar on 6/29/16.
 *
 * variations: storing the index of the minimum node so that we are able to remove it!!
 */
public class MinStack
{
	Node head;

	private class Node
	{
		int val;
		int currMin;
		Node next;

		public Node(int v, int m)
		{
			val = v;
			currMin = m;
		}
	}

	public MinStack()
	{
		head = null;
	}

	public void push(int x)
	{
		if (head == null)
		{
			head = new Node(x, x);
		}
		else
		{
			int min = Math.min(head.currMin, x);
			Node newHead = new Node(x, min);
			newHead.next = head;
			head = newHead;
		}
	}

	public void pop()
	{
		if (head != null)
		{
			head = head.next;
		}
		else
		{
			throw new EmptyStackException();
		}
	}

	public int top()
	{
		if (head != null)
		{
			return head.val;
		}

		throw new EmptyStackException();
	}

	public int getMin()
	{
		if (head != null)
		{
			return head.currMin;
		}

		throw new EmptyStackException();
	}
}
