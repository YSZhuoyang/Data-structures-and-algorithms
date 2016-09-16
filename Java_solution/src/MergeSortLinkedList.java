/**
 * Created by oscar on 3/12/16.
 */
public class MergeSortLinkedList
{

    // Disadvantages of merge sort for sorting a linked list:
    // 1. Need to find the middle point, which takes more time.
	// 2. Involves more list pointer manipulations and function calls.
	// 3. More memory consumption (regardless of sorting a linked list or an array).
    public ListNode sortList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

	    ListNode leftHalf = new ListNode(0);
	    ListNode rightHalf = new ListNode(0);

        split(head, leftHalf, rightHalf);

	    leftHalf = sortList(leftHalf.next);
	    rightHalf = sortList(rightHalf.next);

	    return merge(leftHalf, rightHalf);
    }

    private void split(ListNode origin, ListNode left, ListNode right)
    {
        ListNode slow = origin;
        ListNode fast = origin;

        while (fast != null)
        {
            fast = fast.next;

            if (fast != null)
            {
                fast = fast.next;
            }

            if (fast != null)
            {
                slow = slow.next;
            }
        }

	    // Get the right half of the list
	    right.next = slow.next;

	    // Get the left half of the list
	    slow.next = null;
	    left.next = origin;
    }

	// Note that in merge sort, the merge part does not need to
	// consider the case where left and right list are empty.
    public ListNode merge(ListNode left, ListNode right)
    {
	    ListNode mergedPointer = new ListNode(0);
	    ListNode merged = mergedPointer;

	    while (left != null || right != null)
	    {
		    if (right == null || (left != null && left.val < right.val))
		    {
			    mergedPointer.next = left;
			    left = left.next;
		    }
		    else
		    {
			    mergedPointer.next = right;
			    right = right.next;
		    }

		    mergedPointer = mergedPointer.next;
	    }

	    return merged.next;
    }
}
