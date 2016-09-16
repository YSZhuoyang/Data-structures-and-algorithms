import java.util.HashSet;
import java.util.Set;

/**
 * Created by oscar on 4/2/16.
 */
public class LinkedListHasCycle
{

    // A recommended solution using a hash set
    public boolean hasCycle(ListNode head)
    {
        Set<ListNode> nodesSeen = new HashSet<>();

        while (head != null)
        {
            if (nodesSeen.contains(head))
            {
                return true;
            }
            else
            {
                nodesSeen.add(head);
            }

            head = head.next;
        }

        return false;
    }

    // A recommended solution using two pointers
    /*public boolean hasCycle(ListNode head)
    {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow)
            {
                return true;
            }
        }

        return false;
    }*/
}
