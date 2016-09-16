/**
 * Created by oscar on 5/11/16.
 */
public class QuickSortLinkedList
{

	// Quick sort turned out to be one of the fastest solutions for sorting
	// linked lists in terms of performance.
	// An elegant solution which beats 98% java submissions
	public ListNode sortList(ListNode head)
	{
		if (head == null || head.next == null)
		{
			return head;
		}

		ListNode pivot = head;
		ListNode pointer = head.next;

		// Divide the list into three parts:
		// a list where values of nodes are smaller than pivot value,
		// a list where values of nodes are greater than pivot value and
		// a list where values of nodes are equal to pivot value
		ListNode small = new ListNode(-1);
		ListNode large = new ListNode(-1);
		ListNode medium = pivot;

		ListNode sPointer = small;
		ListNode lPointer = large;
		ListNode mPointer = medium;

		while (pointer != null)
		{
			if (pointer.val < pivot.val)
			{
				sPointer.next = pointer;
				sPointer = sPointer.next;
			}
			else if (pointer.val > pivot.val)
			{
				lPointer.next = pointer;
				lPointer = lPointer.next;
			}
			else
			{
				mPointer.next = pointer;
				mPointer = mPointer.next;
			}

			pointer = pointer.next;
		}

		sPointer.next = null;
		lPointer.next = null;
		mPointer.next = null;

		// Sort small value list and large value list
		small = sortList(small.next);
		large = sortList(large.next);

		// Re-connect lists
		mPointer.next = large;

		if (small != null)
		{
			// Get the tail of sorted small value list
			sPointer = small;

			while (sPointer.next != null)
			{
				sPointer = sPointer.next;
			}

			sPointer.next = medium;

			return small;
		}
		else
		{
			return medium;
		}
	}
}
