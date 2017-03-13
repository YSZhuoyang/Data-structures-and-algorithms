/**
 * Created by oscar on 4/1/16.
 */
public class ReverseLinkedList
{

    // My solution using recursion.
    public ListNode reverseList(ListNode head)
    {
        if (head == null || head.next == null)
            return head;

        // Iterate through until the end of the list.
        // Get the tail, and return as a new head.
        ListNode n = reverseList(head.next);
        // Establish reversed connection.
        head.next.next = head;
        // Cut original connection, to prevent forming a circle.
        head.next = null;

        return n;
    }

    /* My solution using iteration
    public ListNode reverseList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode left = head;
        ListNode move;
        ListNode right;

        while (left.next != null)
        {
            move = left.next;
            right = move.next;

            move.next = head;
            head = move;
            left.next = right;
        }

        return head;
    }*/

    /* An alternative using stack, slow, not recommended
    public ListNode reverseList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode iter = head;

        while (iter != null)
        {
            stack.push(iter);
            iter = iter.next;
        }

        head = stack.pop();
        iter = head;

        while (!stack.isEmpty())
        {
            iter.next = stack.pop();
            iter = iter.next;
        }

        iter.next = null;

        return head;
    }
    */
}
