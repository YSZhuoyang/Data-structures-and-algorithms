/**
 * Created by oscar on 3/12/16.
 */
public class RotateLinkedList
{

    // My solution using two pointers
    public ListNode rotateRight(ListNode head, int k)
    {
        if (head == null)
        {
            return head;
        }

        k = k % getLength(head);

        if (k == 0)
        {
            return head;
        }

        ListNode rightRef = head;
        ListNode leftTail = head;
        int i = 0;

        while (i < k)
        {
            rightRef = rightRef.next;
            i++;
        }

        while (rightRef.next != null)
        {
            rightRef = rightRef.next;
            leftTail = leftTail.next;
        }

	    // Swap left part and right part
        ListNode rightHead = leftTail.next;
        leftTail.next = null;
        rightRef.next = head;

        return rightHead;
    }

    private int getLength(ListNode list)
    {
        int count = 0;

        while (list != null)
        {
            list = list.next;
            count++;
        }

        return count;
    }

    // For degugging
    public void displayList(ListNode list)
    {
        while (list != null)
        {
            System.out.println(list.val);
            list = list.next;
        }
    }
}
