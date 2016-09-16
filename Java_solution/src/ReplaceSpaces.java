/**
 * Created by oscar on 6/26/16.
 */
public class ReplaceSpaces
{

	// My solution with n time complexity
	public char[] replaceSpaces(char[] arr)
	{
		if (arr == null || arr.length == 0)
		{
			return null;
		}

		int counter = 0;

		// Count number of whitespaces
		for (char c : arr)
		{
			if (c == ' ' || c == '\r' || c == '\t' || c == '\n')
			{
				counter++;
			}
		}

		char[] res = new char[arr.length + counter * 2];
		counter = 0;

		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] == ' ' || arr[i] == '\r' || arr[i] == '\t' || arr[i] == '\n')
			{
				res[counter++] = '%';
				res[counter++] = '2';
				res[counter++] = '0';
			}
			else
			{
				res[counter++] = arr[i];
			}
		}

		return res;
	}
}
