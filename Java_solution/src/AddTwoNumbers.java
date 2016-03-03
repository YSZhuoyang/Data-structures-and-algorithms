/**
 * Definition for singly-linked list.
 */
class ListNode
{
    int val;
    ListNode next;

    ListNode(int x)
    {
        val = x;
    }
}

// My solution
public class AddTwoNumbers
{

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode res = new ListNode(0);
        ListNode first = res;
        boolean carry = false;

        while (l1 != null || l2 != null || carry)
        {
            if (l1 == null && l2 != null)
            {
                if (carry)
                {
                    res.val = l2.val + 1;
                    carry = false;

                    if (res.val > 9)
                    {
                        carry = true;
                        res.val = res.val % 10;
                    }
                }
                else
                {
                    res.val = l2.val;
                }

                l2 = l2.next;
            }
            else if (l2 == null && l1 != null)
            {
                if (carry)
                {
                    res.val = l1.val + 1;
                    carry = false;

                    if (res.val > 9)
                    {
                        carry = true;
                        res.val = res.val % 10;
                    }
                }
                else
                {
                    res.val = l1.val;
                }

                l1 = l1.next;
            }
            else if (l1 == null && l2 == null && carry)
            {
                res.val = 1;
                carry = false;
            }
            else
            {
                if (carry)
                {
                    res.val = l1.val + l2.val + 1;
                    carry = false;

                    if (res.val > 9)
                    {
                        carry = true;

                        res.val = res.val % 10;
                    }
                }
                else
                {
                    res.val = l1.val + l2.val;

                    if (res.val > 9)
                    {
                        carry = true;

                        res.val = res.val % 10;
                    }
                }

                l1 = l1.next;
                l2 = l2.next;
            }

            if (l1 != null || l2 != null || carry)
            {
                res.next = new ListNode(0);
                res = res.next;
            }
        }

        return first;
    }
}
