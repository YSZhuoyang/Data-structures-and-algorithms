/**
 * Created by oscar on 4/2/16.
 */
public class RemoveLastNNode
{

    // My first solution using two pointers
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        ListNode left = head;
        ListNode right = head;

        for (int i = 0; i < n; i++)
        {
            if (right != null)
            {
                right = right.next;
            }

            // n is larger than or equal to the size of list
            if (right == null)
            {
                return head.next;
            }
        }

        right = right.next;

        while (right != null)
        {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;

        return head;
    }

    /* My second solution
    // 1. Compute the length of the list
    // 2. target = len - n
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        if (head == null)
        {
            return head;
        }

        ListNode iter = head;
        int len = 0;

        while (iter != null)
        {
            iter = iter.next;
            len++;
        }

        if (len < n)
        {
            return head;
        }
        else if (len == n)
        {
            return head.next;
        }

        int target = len - n - 1;
        iter = head;

        for (int i = 0; i < target; i++)
        {
            iter = iter.next;
        }

        iter.next = iter.next.next;

        return head;
    }*/

    /* A recommended solution
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        ListNode start = new ListNode(0);
        ListNode left = start, right = start;
        start.next = head;

        for(int i = 0; i < n + 1; i++)
        {
            right = right.next; // Create a gap between two pointers
        }

        while (right != null)
        {
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;

        return start.next;
    }*/
}
