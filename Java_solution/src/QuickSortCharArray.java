/**
 * Created by oscar on 6/25/16.
 *
 * An in space quick sort solution.
 * There is a bug that need to be fixed.
 */
public class QuickSortCharArray
{

	private void quicksort(char[] array, int left, int right)
	{
		if (right - left <= 1)
		{
			if (array[left] > array[right])
			{
				swap(array, left, right);
			}

			return;
		}

		int middle = (left + right) / 2;
		int lIter = left;
		int rIter = right;
		char pivot = array[middle];

		while (lIter < rIter)
		{
			while (array[lIter] < pivot)
			{
				lIter++;
			}

			while (array[rIter] > pivot)
			{
				rIter--;
			}

			swap(array, lIter, rIter);
			lIter++;
			rIter--;
		}

		quicksort(array, left, middle);
		quicksort(array, middle, right);
	}

	private void swap(char[] s, int l, int r)
	{
		char temp = s[l];
		s[l] = s[r];
		s[r] = temp;
	}
}
