/**
 * Created by oscar on 3/2/16.
 */
public class TestDriver
{
    private static void testSubsetsII()
    {
        SubsetsII s = new SubsetsII();
        int[] num = {0, 2, 1, 3, 4, 7, 4, 5, 9};

        s.subsetsWithDup(num);
    }

    private static void testAddTwoNumbers()
    {
        AddTwoNumbers atn = new AddTwoNumbers();
        atn.addTwoNumbers(new ListNode(0), new ListNode(0));
    }

    private static void testMedianOfTwoSortedArrays()
    {
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
        int[] a2 = {1, 2, 6, 7};
        int[] a1 = {3, 4, 5, 8};

        System.out.println("MedianOfTwoSortedArrays Result: " + m.findMedianSortedArrays(a1, a2));
    }

    public static void main(String[] args)
    {
        testMedianOfTwoSortedArrays();
    }
}
