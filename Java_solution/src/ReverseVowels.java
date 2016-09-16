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

		// Perform two traverses.
		// First traverse: go through the given string, store index of
		// each vowel into a queue, store each vowel character into a stack.
		// Second traverse: go through vowels using their indices, and
		// pop out vowel characters in a reversed order using the stack.
		ArrayDeque<Integer> originalVowelIndices = new ArrayDeque<>();
		Stack<Character> reversedVowels = new Stack<>();
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < sb.length(); i++)
		{
			if (isVowel(sb.charAt(i)))
			{
				originalVowelIndices.add(i);
				reversedVowels.push(sb.charAt(i));
			}
		}

		while (!originalVowelIndices.isEmpty())
		{
			sb.setCharAt(originalVowelIndices.pop(), reversedVowels.pop());
		}

		return sb.toString();
	}

	private boolean isVowel(char c)
	{
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
				c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
