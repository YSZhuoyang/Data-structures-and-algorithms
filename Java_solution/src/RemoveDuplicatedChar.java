/**
 * Created by oscar on 6/25/16.
 *
 * Assumption: input array only contain lowercase letters
 */
public class RemoveDuplicatedChar
{

	public char[] removeDuplicates(char[] array)
	{
		if (array == null || array.length < 2)
		{
			return array;
		}

		// Memorize letters that have appeared before
		boolean[] appeared = new boolean[26];
		int iterator = 0;
		int index;

		for (int i = 0; i < array.length; i++)
		{
			index = array[i] - 'a';

			if (!appeared[index])
			{
				appeared[index] = true;
				array[iterator] = array[i];
				iterator++;
			}
		}

		// Replace the rest elements with space
		while (iterator < array.length)
		{
			array[iterator] = ' ';
			iterator++;
		}

		return array;
	}
}
