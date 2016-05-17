/**
 * Created by oscar on 4/1/16.
 */
public class ReverseLinkedList
{

    // My solution using iteration
    public ListNode reverseList(ListNode head)
    {
        if (head == null)
        {
            return head;
        }

        ListNode left = head;
        ListNode right = head.next;
        ListNode tail;

        while (right != null)
        {
            tail = right.next;
            right.next = head;
            left.next = tail;
            head = right;
            right = tail;
        }

        return head;
    }

    /* My solution using recursion
    public ListNode reverseList(ListNode head)
    {
        if (head == null)
        {
            return head;
        }

        ListNode newHead = new ListNode(0);
        recReverse(head, newHead);

        return newHead.next;
    }

    private void recReverse(ListNode node, ListNode newHead)
    {
        if (node.next == null)
        {
            newHead.next = node;
            return;
        }

        recReverse(node.next, newHead);

        node.next.next = node;
        node.next = null;
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