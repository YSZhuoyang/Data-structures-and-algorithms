/**
 * Created by oscar on 4/5/16.
 */
public class InsersionSortList
{

    // A recommended solution which beats 90% java submissions
    // The idea is to create another sorted list, and continue
    // taking the first node of the list to be sorted, adding
    // it to the sorted list by inserting into the correct position
    public ListNode insertionSortList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode sortedHead = head;
        ListNode sortedTail = head;
        ListNode toBeSorted;
        ListNode sortedPointer;

        head = head.next;
        sortedHead.next = null;

        while (head != null)
        {
            toBeSorted = head;
            head = head.next;
            toBeSorted.next = null;

            if (toBeSorted.val <= sortedHead.val)
            {
                toBeSorted.next = sortedHead;
                sortedHead = toBeSorted;
            }
            else if (toBeSorted.val >= sortedTail.val)
            {
                sortedTail.next = toBeSorted;
                sortedTail = toBeSorted;
            }
            else
            {
                sortedPointer = sortedHead;

                while (sortedPointer.next != null && toBeSorted.val > sortedPointer.next.val)
                {
                    sortedPointer = sortedPointer.next;
                }

                toBeSorted.next = sortedPointer.next;
                sortedPointer.next = toBeSorted;
            }
        }

        return sortedHead;
    }
}
