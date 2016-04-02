/**
 * Created by oscar on 4/2/16.
 */
public class RemoveNodeFromList
{

    // My solution which beats 86.61% java submissions
    public ListNode removeElements(ListNode head, int val)
    {
        while (head != null && head.val == val)
        {
            head = head.next;
        }

        if (head == null)
        {
            return head;
        }

        ListNode node = head;

        while (node.next != null)
        {
            if (node.next.val == val)
            {
                node.next = node.next.next;
            }
            else    // Be carefull here
            {
                node = node.next;
            }
        }

        return head;
    }
}
