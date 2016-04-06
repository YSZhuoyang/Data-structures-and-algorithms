/**
 * Created by oscar on 3/10/16.
 */
public class SwapNodesInPairs
{

    public ListNode swapPairs(ListNode head)
    {
        ListNode first = head;  // First node to be swapped
        ListNode second;        // Second node to be swapped
        ListNode beforeFirst;   // The node before the first node
        ListNode temp;          // A temp node used during the swap

        // When the length is smaller than 3
        if (head == null || head.next == null)
        {
            return head;
        }

        // Swap the first two nodes
        second = head.next;
        temp = second.next;
        first.next = temp;
        second.next = first;
        head = second;

        // Swap the rest nodes behind the first two nodes
        first = second;
        second = first.next;

        while (second.next != null && second.next.next != null)
        {
            beforeFirst = second;
            first = beforeFirst.next;
            second = beforeFirst.next.next;

            temp = second.next;
            first.next = temp;
            second.next = first;
            beforeFirst.next = second;

            first = second;
            second = first.next;
        }

        return head;
    }
}


