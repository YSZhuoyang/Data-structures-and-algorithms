/**
 * Created by oscar on 4/10/16.
 */
public class MergeList
{

    // My solution
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        if (l1 == null && l2 == null)
        {
            return null;
        }
        else if (l1 == null)
        {
            return l2;
        }
        else if (l2 == null)
        {
            return l1;
        }

        ListNode mergedPointer = new ListNode(0);
        ListNode merged = mergedPointer;

        while (l1 != null || l2 != null)
        {
            if (l2 == null || (l1 != null && l2 != null && l1.val < l2.val))
            {
                mergedPointer.next = l1;
                l1 = l1.next;
            }
            else
            {
                mergedPointer.next = l2;
                l2 = l2.next;
            }

            mergedPointer = mergedPointer.next;
        }

        return merged.next;
    }
}
