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

    private static void test2DPathSearch()
    {
        TwoDimensionalPathSearch twoDPathSearch = new TwoDimensionalPathSearch();
        int[][] board = {
                {1, 0, 1},
                {0, 0, 0},
                {0, 0, 1}
        };

        if (twoDPathSearch.pathSearch(board))
        {
            System.out.println("Path found");
        }
        else
        {
            System.out.println("No path exist");
        }
    }

    public static void main(String[] args)
    {
        //testMedianOfTwoSortedArrays();

        /*OddOrEven oe = new OddOrEven();

        if (oe.isOddOrEven(2))
        {
            System.out.println("Odd");
        }
        else
        {
            System.out.println("Even");
        }*/

        test2DPathSearch();
    }
}
