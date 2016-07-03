import java.util.HashMap;

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
        int[][] board = {
                {1, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 1, 1}
        };

	    int n = 3;
	    int m = 4;

	    TwoDimensionalPathSearch twoDPathSearch = new TwoDimensionalPathSearch();

        if (twoDPathSearch.pathSearch(board, n, m))
        {
            System.out.println("Path found");
        }
        else
        {
            System.out.println("No path exist");
        }
    }

    public static void testUniqueCharStr()
    {
        UniqueCharStr uniqueCharStr = new UniqueCharStr();
        String testFalse = "asdfa";
        String testTrue = "asdf";

        if (!uniqueCharStr.uniqueCharStr(testFalse) && uniqueCharStr.uniqueCharStr(testTrue))
        {
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }
    }

    public static void testRemoveDuplicatedChar()
    {
        RemoveDuplicatedChar removeDuplicatedChar = new RemoveDuplicatedChar();
        char[] array = {'c', 'b', 'b', 'a', 'c', 'a', 'b'};

        removeDuplicatedChar.removeDuplicates(array);
        System.out.println(new String(array));
    }

    public static void testReplaceSpaces()
    {
        ReplaceSpaces replaceSpaces = new ReplaceSpaces();
        String s = "asdf\tasdf\rasdf ";
        String expected = "asdf%20asdf%20asdf%20";
        char[] arr = s.toCharArray();
        char[] expArr = expected.toCharArray();
        char[] res = replaceSpaces.replaceSpaces(arr);

        boolean equal = true;

        for (int i = 0; i < res.length; i++)
        {
            if (res[i] != expArr[i])
            {
                equal = false;
                break;
            }
        }

        if (equal)
        {
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }
    }

    public static void main(String[] args)
    {
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
        //testMedianOfTwoSortedArrays();
        //testUniqueCharStr();
        //testRemoveDuplicatedChar();
        //testReplaceSpaces();
    }
}
