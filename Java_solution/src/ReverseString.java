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
}
