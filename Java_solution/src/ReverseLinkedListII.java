/**
 * Created by oscar on 14/03/17.
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || n <= m)
            return head;

        ListNode leftEnd = null;
        ListNode mStart = head;

        int i = 1;
        if (m > 1)
        {
            leftEnd = head;

            while (i + 1 < m && leftEnd != null)
            {
                leftEnd = leftEnd.next;
                i++;
            }

            // m >= list size
            if (leftEnd == null || leftEnd.next == null)
                return head;

            mStart = leftEnd.next;
            leftEnd.next = null;
            i++;
        }

        ListNode mEnd = mStart;

        while (i < n && mEnd != null)
        {
            mEnd = mEnd.next;
            i++;
        }

        ListNode right = null;
        if (mEnd != null)
        {
            right = mEnd.next;
            mEnd.next = null;
        }

        // Reverse middle part
        ListNode reversed = reverseList(mStart);

        // Connect middle list with right list
        mStart.next = right;

        if (leftEnd == null) return reversed;
        // Connect left list with middle list
        else leftEnd.next = reversed;

        return head;
    }

    private ListNode reverseList(ListNode head)
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
}

