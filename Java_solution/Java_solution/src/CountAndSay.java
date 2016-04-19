/**
 * Created by oscar on 4/19/16.
 */
public class CountAndSay
{

	public String countAndSay(int n)
	{
		if (n <= 0)
		{
			return "";
		}

		String axiom = "1";

		for (int i = 0; i < n - 1; i++)
		{
			int count = 1;
			char number = axiom.charAt(0);
			String newStr = "";

			for (int j = 1; j < axiom.length(); j++)
			{
				// Count
				if (axiom.charAt(j) == axiom.charAt(j - 1))
				{
					count++;
				}
				// Say
				else
				{
					newStr = newStr + count + number;

					count = 1;
					number = axiom.charAt(j);
				}
			}

			newStr = newStr + count + number;

			axiom = newStr;
		}

		return axiom;
	}
}
