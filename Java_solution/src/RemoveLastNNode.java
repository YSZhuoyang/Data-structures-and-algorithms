/**
 * Created by oscar on 4/2/16.
 */
public class RemoveLastNNode
{

    // My solution using two pointers
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
}
