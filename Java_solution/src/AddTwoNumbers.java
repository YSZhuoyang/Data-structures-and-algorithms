// My solution
public class AddTwoNumbers
{

	// My second solution optimized using same idea of addBinaries solution
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        if (l1 == null || l2 == null)
        {
            if (l1 == null && l2 == null)
            {
                return new ListNode(0);
            }
            else if (l1 == null)
            {
                return l2;
            }
            else
            {
                return l1;
            }
        }

        ListNode res = new ListNode(0);
        ListNode rPointer = res;
        int temp = 0;

        while (l1 != null || l2 != null)
        {
            if (l1 != null)
            {
                temp += l1.val;
                l1 = l1.next;
            }

            if (l2 != null)
            {
                temp += l2.val;
                l2 = l2.next;
            }

            rPointer.next = new ListNode(temp % 10);
            rPointer = rPointer.next;
            temp /= 10;
        }

        if (temp > 0)
        {
            rPointer.next = new ListNode(temp);
        }

        return res.next;
    }

    // Two lists store the digits of each number in a reversed order.
	// Refer to 'addBinaries' solution to improve this solution.
    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2)
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
    }*/
}
