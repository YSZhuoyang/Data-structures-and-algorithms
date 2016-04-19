/**
 * Created by oscar on 3/12/16.
 */
public class RotateLinkedList
{
    /**
     * Definition for singly-linked list.
     */
    public class ListNode
    {
        int val;
        ListNode next;

        ListNode(int x)
        {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k)
    {
        ListNode rightRef = head;
        ListNode leftTail = head;
        int i = 0;

        if (head == null)
        {
            return head;
        }

        k = k % count(head);

        if (k == 0)
        {
            return head;
        }

        while (i < k && rightRef != null)
        {
            rightRef = rightRef.next;
            i++;
        }

        while (rightRef.next != null)
        {
            rightRef = rightRef.next;
            leftTail = leftTail.next;
        }

        ListNode rightHead = leftTail.next;
        leftTail.next = null;
        rightRef.next = head;

        return rightHead;
    }

    public int count(ListNode list)
    {
        int len = 0;

        while (list != null)
        {
            list = list.next;
            len++;
        }

        return len;
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
