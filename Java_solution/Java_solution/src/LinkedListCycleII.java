/**
 * Created by oscar on 4/16/16.
 */
public class LinkedListCycleII
{

	// A recommended solution without using extra space, with
	// two pointers
	public ListNode detectCycle(ListNode head)
	{
		if(head == null || head.next == null)
		{
			return null;
		}

		ListNode fast = head;
		ListNode slow = head;

		// Find out whether there is a cycle, if it does, find the meeting point
		while (fast != null && fast.next != null)
		{
			fast = fast.next.next;
			slow = slow.next;

			if (fast == slow)
			{
				break;
			}
		}

		if (fast != slow)
		{
			return null;
		}

		slow = head;

		// The distance from the meeting point to cycle start point is
		// equal to the distance between the head of list and the cycle start point
		while (fast != slow)
		{
			fast = fast.next;
			slow = slow.next;
		}

		return fast;
	}
}
