import java.util.ArrayDeque;

/**
 * Created by oscar on 5/19/16.
 *
 * Assumption: input m and n are always correct
 */
public class MergeSortedArray
{
	// A very fast solution
	// Note that although we can put those if else into while
	// loops to reduce lines of code and make it easier to read,
	// it will also slow down the performance since there will be
	// to many jump operations after compiling the code, which will
	// cost more time and make it harder for compiler to perform
	// optimizations since compiler is like to loose information
	// every time it hits conditional statements.
	public void merge(int[] nums1, int m, int[] nums2, int n)
	{
		if (nums1 == null || nums2 == null || n == 0)
		{
			return;
		}
		else if (m == 0)
		{
			for (int i = 0; i < n; i++)
			{
				nums1[i] = nums2[i];
			}

			return;
		}

		int index = m + n - 1;
		int indexArr1 = m - 1;
		int indexArr2 = n - 1;

		while (index >= 0)
		{
			if (indexArr2 < 0 || indexArr1 < 0)
			{
				break;
			}

			if (nums1[indexArr1] > nums2[indexArr2])
			{
				nums1[index--] = nums1[indexArr1--];
			}
			else
			{
				nums1[index--] = nums2[indexArr2--];
			}
		}

		if (indexArr2 < 0)
		{
			while (index >= 0)
			{
				nums1[index--] = nums1[indexArr1--];
			}
		}
		else
		{
			while (index >= 0)
			{
				nums1[index--] = nums2[indexArr2--];
			}
		}
	}

	/* My solution: insert array2 into array1
	public void merge(int[] nums1, int m, int[] nums2, int n)
	{
		if (nums1 == null || nums2 == null || n == 0)
		{
			return;
		}

		int indexNums2 = 0;
		int numInsertion;
		int newLen = m;
		ArrayDeque<Integer> toBeInserted = new ArrayDeque<>();

		// Insert elements smaller than largest in nums1
		for (int i = 0; i < newLen; i++)
		{
			while (indexNums2 < n && i < newLen && nums2[indexNums2] > nums1[i])
			{
				i++;
			}

			if (i == newLen)
			{
				break;
			}

			// Find elements to be inserted in nums2
			for (; indexNums2 < n; indexNums2++)
			{
				if (nums2[indexNums2] <= nums1[i])
				{
					toBeInserted.add(nums2[indexNums2]);
				}
				else
				{
					break;
				}
			}

			// Insert into nums1
			numInsertion = toBeInserted.size();

			if (numInsertion == 0)
			{
				continue;
			}

			// Right shift the right part of the array
			for (int j = newLen - 1; j >= i; j--)
			{
				nums1[j + numInsertion] = nums1[j];
			}

			// Insert new elements
			for (int j = 0; j < numInsertion; j++)
			{
				nums1[i + j] = toBeInserted.pop();
			}

			i += numInsertion;
			newLen += numInsertion;
			toBeInserted.clear();
		}

		// Insert elements greater than largest in nums1
		for (int j = 0; indexNums2 < n; indexNums2++, j++)
		{
			nums1[newLen + j] = nums2[indexNums2];
		}
	}*/
}
