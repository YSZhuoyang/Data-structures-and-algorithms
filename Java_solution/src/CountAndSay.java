/**
 * Created by oscar on 4/19/16.
 */
public class CountAndSay
{

	// My solution beats 48% java submissions
	public String countAndSay(int n)
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
