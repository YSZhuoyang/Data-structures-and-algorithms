/**
 * Created by oscar on 4/12/16.
 */
public class PalindromeLinkedList
{

    // A recommended solution
    public boolean isPalindrome(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return true;
        }

        // Find the node in the middle with two pointers
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null)
        {
            fast = fast.next;
            slow = slow.next;

            if (fast.next != null)
            {
                fast = fast.next;
            }
        }

        // Reverse right half list
        ListNode right = slow.next;
        ListNode left = slow;
        ListNode tail;

        while (right != null)
        {
            tail = right.next;
            right.next = slow;
            left.next = tail;
            slow = right;
            right = tail;
        }

        // Compare reversed right half and left half
        while (slow != null)
        {
            if (slow.val != head.val)
            {
                return false;
            }
            else
            {
                slow = slow.next;
                head = head.next;
            }
        }

        return true;
    }
}
