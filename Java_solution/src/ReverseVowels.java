import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by oscar on 5/11/16.
 */
public class ReverseVowels
{

	// My solution which takes 19 ms on leetcode.
	public String reverseVowels(String s)
	{
		if (s == null || s.isEmpty())
		{
			return "";
		}

		ArrayDeque<Integer> originalVowels = new ArrayDeque<>();
		Stack<Character> reversedVowels = new Stack<>();
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < sb.length(); i++)
		{
			if (isVowel(sb.charAt(i)))
			{
				originalVowels.add(i);
				reversedVowels.push(sb.charAt(i));
			}
		}

		while (!originalVowels.isEmpty())
		{
			sb.setCharAt(originalVowels.pop(), reversedVowels.pop());
		}

		return sb.toString();
	}

	private boolean isVowel(char c)
	{
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
				c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
