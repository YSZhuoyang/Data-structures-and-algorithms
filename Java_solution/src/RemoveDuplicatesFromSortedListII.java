/**
 * Created by oscar on 6/28/16.
 */
public class RemoveDuplicatesFromSortedListII
{

	// My solution using two pointers
	// E.g. newHead --> 5 --> 2 (current tail node) --> 2 (skipped) --> 2 (skipped) --> 3 (skipped)
	// --> 3 (skipped) --> 4 (current iter node) --> ...
	public ListNode deleteDuplicates(ListNode head)
	{
		if (head == null || head.next == null)
		{
			return head;
		}

		ListNode newHead = new ListNode(0);
		ListNode iter = head;
		ListNode tail = newHead;

		while (iter != null)
		{
			// Check whether the current node has duplicates
			if (iter.next != null && iter.val == iter.next.val)
			{
				while (iter.next != null && iter.val == iter.next.val)
				{
					iter = iter.next;
				}

				// We have reached the end, remove the rest after the tail node
				// and return
				if (iter.next == null)
				{
					tail.next = null;

					return newHead.next;
				}
			}
			// If not, assign the next pointer of the tail node with the current node
			// and make it new tail node
			else
			{
				tail.next = iter;
				tail = tail.next;
			}

			iter = iter.next;
		}

		return newHead.next;
	}
}
