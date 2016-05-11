import java.util.ArrayList;

/**
 * Created by oscar on 5/11/16.
 */
public class ReverseVowels
{
	public String reverseVowels(String s)
	{
		if (s == null || s.isEmpty())
		{
			return "";
		}

		char[] charArray = s.toCharArray();
		ArrayList<Integer> originalVowels = new ArrayList<>();
		ArrayList<Character> reversedVowels = new ArrayList<>();
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < s.length(); i++)
		{
			if (isVowel(charArray[i]))
			{
				originalVowels.add(i);
				reversedVowels.add(0, charArray[i]);
			}
		}

		for (int i = 0; i < originalVowels.size(); i++)
		{
			sb.setCharAt(originalVowels.get(i), reversedVowels.get(i));
		}

		return sb.toString();
	}

	private boolean isVowel(char c)
	{
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
				c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
