/**
 * Created by oscar on 4/16/16.
 */
public class IntersectionOfTwoLinkedList
{

	// A recommended solution
	public ListNode getIntersectionNode(ListNode headA, ListNode headB)
	{
		if (headA == null || headB == null)
		{
			return null;
		}

		int len1 = 0;
		int len2 = 0;

		ListNode p1 = headA;
		ListNode p2 = headB;

		// Get length of list A
		while (p1 != null)
		{
			p1 = p1.next;
			len1++;
		}

		// Get length of list B
		while (p2 != null)
		{
			p2 = p2.next;
			len2++;
		}

		p1 = headA;
		p2 = headB;

		int diff = (len1 > len2)? len1 - len2 : len2 - len1;

		if (len1 > len2)
		{
			for (int i = 0; i < diff; i++)
			{
				p1 = p1.next;
			}
		}
		else
		{
			for (int i = 0; i < diff; i++)
			{
				p2 = p2.next;
			}
		}

		while (p1 != null && p1 != p2)
		{
			p1 = p1.next;
			p2 = p2.next;
		}

		return p1;
	}
}
