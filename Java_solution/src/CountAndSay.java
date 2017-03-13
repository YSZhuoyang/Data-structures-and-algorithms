/**
 * Created by oscar on 4/19/16.
 */
public class CountAndSay
{
	// My recursive solution beating 87% java submissions
	public String countAndSay(int n) {
		String s = "1";

		for (int i = 1; i < n; i++)
		    s = countAndSay(s);

		return s;
	    }

	    public String countAndSay(String s)
	    {
		if (s == null || s.isEmpty()) return s;

		StringBuilder sb = new StringBuilder();
		char[] cArr = s.toCharArray();

		int count = 1;
		char say = cArr[0];

		for (int i = 1; i < cArr.length; i++)
		{
		    if (cArr[i] == cArr[i - 1])
			count++;
		    else
		    {
			sb.append(count);
			sb.append(say);

			say = cArr[i];
			count = 1;
		    }
		}

		sb.append(count);
		sb.append(say);

		return sb.toString();
	}

	// My iterative solution beating 48% java submissions
	public String countAndSay2(int n)
	{
		if (n <= 0)
		{
			return "";
		}

		String axiom = "1";
		int iterCount = n - 1;
		int len;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < iterCount; i++)
		{
			int count = 1;
			char number = axiom.charAt(0);
			len = axiom.length();
			// Re-initialize string builder
			sb.setLength(0);

			for (int j = 1; j < len; j++)
			{
				// Count
				if (axiom.charAt(j) == axiom.charAt(j - 1))
				{
					count++;
				}
				// Say
				else
				{
					sb.append(count);
					sb.append(number);

					count = 1;
					number = axiom.charAt(j);
				}
			}

			sb.append(count);
			sb.append(number);

			axiom = sb.toString();
		}

		return axiom;
	}
}
