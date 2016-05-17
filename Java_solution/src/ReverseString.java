/**
 * Created by oscar on 5/13/16.
 */
public class ReverseString
{

	// My solution
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
	}

	// A faster recommended solution
	/*public String reverseString(String s)
	{
		if (s == null || s.isEmpty() || s.length() == 1)
		{
			return s;
		}

		char[] cArray = new char[s.length()];

		int head = 0;
		int tail = s.length() - 1;
		int middle = s.length() / 2;

		while (head <= middle)
		{
			cArray[head] = s.charAt(tail);
			cArray[tail] = s.charAt(head);

			head++;
			tail--;
		}

		return new String(cArray);
	}*/
}
