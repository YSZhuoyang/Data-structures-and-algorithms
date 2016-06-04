/**
 * Created by oscar on 6/4/16.
 *
 * Assuming that all insertions and searching are done given strings
 * containing only letters 'a' to 'z'.
 */
class TrieNode
{
	boolean isWord;
	TrieNode[] children;

	public TrieNode()
	{
		children = new TrieNode[26];
	}
}

public class Trie
{
	TrieNode root;

	public Trie()
	{
		root = new TrieNode();
	}

	// Insert a new word
	public void insert(String word)
	{
		if (word == null || word.isEmpty())
		{
			return;
		}

		TrieNode pointer = root;
		int index;

		for (int i = 0; i < word.length(); i++)
		{
			char c = word.charAt(i);
			index = c - 'a';

			if (pointer.children[index] == null)
			{
				pointer.children[index] = new TrieNode();
			}

			pointer = pointer.children[index];

			if (i == word.length() - 1)
			{
				pointer.isWord = true;
			}
		}
	}

	// Returns if the word is in the trie.
	public boolean search(String word)
	{
		if (word == null)
		{
			return false;
		}

		TrieNode pointer = root;
		int index;

		for (int i = 0; i < word.length(); i++)
		{
			char c = word.charAt(i);
			index = c - 'a';

			if (pointer.children[index] == null)
			{
				return false;
			}

			pointer = pointer.children[index];
		}

		return pointer.isWord;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix)
	{
		if (prefix == null)
		{
			return false;
		}

		TrieNode pointer = root;
		int index;

		for (int i = 0; i < prefix.length(); i++)
		{
			char c = prefix.charAt(i);
			index = c - 'a';

			if (pointer.children[index] == null)
			{
				return false;
			}

			pointer = pointer.children[index];
		}

		return true;
	}
}
