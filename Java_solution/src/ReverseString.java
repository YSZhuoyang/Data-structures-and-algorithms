/**
 * Created by oscar on 5/13/16.
 */
public class ReverseString
{

	// A faster recommended solution using two pointers
	public String reverseString(String s)
	{
		if (s == null || s.isEmpty() || s.length() == 1)
		{
			return s;
		}

		char[] cArray = new char[s.length()];

		int lPointer = 0;
		int rPointer = s.length() - 1;

		while (lPointer < rPointer)
		{
			cArray[lPointer] = s.charAt(rPointer);
			cArray[rPointer] = s.charAt(lPointer);

			lPointer++;
			rPointer--;
		}

		return new String(cArray);
	}

	/* My solution
	public String reverseString(String s)
	{
		if (s == null || s.isEmpty() || s.length() == 1)
		{
			return s;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = s.length() - 1; i >= 0; i--)
		{
			sb.append(s.charAt(i));
		}

		return sb.toString();
	}*/
}
