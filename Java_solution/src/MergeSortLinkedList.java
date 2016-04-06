/**
 * Created by oscar on 3/12/16.
 */
public class MergeSortLinkedList
{

    public ListNode sortList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        mergeSort(head);

        return head;
    }

    public void mergeSort(ListNode list)
    {
        ListNode leftHalf = new ListNode(0);
        ListNode rightHalf = new ListNode(0);

        if (list == null || list.next == null)
        {
            return;
        }

        split(list, leftHalf, rightHalf);

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(list, leftHalf, rightHalf);
    }

    // For debugging
    /*public void displayList(ListNode list)
    {
        while (list != null)
        {
            System.out.println("list: : " + list.val);
            list = list.next;
        }
    }*/

    public void split(ListNode origin, ListNode left, ListNode right)
    {
        ListNode slow = origin;
        ListNode fast = origin;

        while (fast != null)
        {
            fast = fast.next;

            if (fast != null)
            {
                fast = fast.next;
            }

            if (fast != null)
            {
                slow = slow.next;
            }
        }

        right.val = slow.next.val;
        right.next = slow.next.next;

        slow.next = null;   // Place this line before assignment of 'left' or after assignment of 'left'
                            // will cause errors
        left.val = origin.val;
        left.next = origin.next;
    }

    public void merge(ListNode merged, ListNode left, ListNode right)
    {
        ListNode leftRef = left;
        ListNode rightRef = right;
        ListNode sorted;

        if (leftRef.val < rightRef.val)
        {
            sorted = new ListNode(leftRef.val);
            leftRef = leftRef.next;
        }
        else
        {
            sorted = new ListNode(rightRef.val);
            rightRef = rightRef.next;
        }

        ListNode sortedRef = sorted;

        while (leftRef != null && rightRef != null)
        {
            if (leftRef.val < rightRef.val)
            {
                sortedRef.next = new ListNode(leftRef.val);
                leftRef = leftRef.next;
            }
            else
            {
                sortedRef.next = new ListNode(rightRef.val);
                rightRef = rightRef.next;
            }

            sortedRef = sortedRef.next;
        }

        if (leftRef != null)
        {
            sortedRef.next = leftRef;
        }
        else
        {
            sortedRef.next = rightRef;
        }

        merged.val = sorted.val;
        merged.next = sorted.next;
    }
}
