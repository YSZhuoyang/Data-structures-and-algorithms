import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by oscar on 5/20/16.
 */
public class IntersectionOfTwoArrays
{

	// My solution
	public int[] intersection(int[] nums1, int[] nums2)
	{
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
		{
			return new int[0];
		}

		HashMap<Integer, Integer> memo = new HashMap<>();
		ArrayDeque<Integer> intersection = new ArrayDeque<>();

		// Storing the first array into memo
		for (int element : nums1)
		{
			memo.put(element, 0);
		}

		// Check whether memo contains elements in the second array
		// And store them into a queue
		for (int element : nums2)
		{
			if (memo.containsKey(element) && memo.get(element) == 0)
			{
				memo.put(element, 1);
				intersection.add(element);
			}
		}

		// Construct an integer array as output
		int[] res = new int[intersection.size()];

		for (int i = 0; i < res.length; i++)
		{
			res[i] = intersection.pop();
		}

		return res;
	}
}
