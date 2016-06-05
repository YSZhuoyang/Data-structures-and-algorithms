import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by oscar on 6/4/16.
 */
public class WordSearchII
{

	private class TrieNode
	{
		boolean added;
		String word;
		TrieNode[] children;

		TrieNode()
		{
			children = new TrieNode[26];
		}
	}

	// An optimized solution that solves the two limitations mentioned
	// below.
	public List<String> findWords(char[][] board, String[] words)
	{
		List<String> list = new ArrayList<>();

		if (board == null || words == null)
		{
			return list;
		}

		TrieNode root = buildTrie(words);

		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				dfs(board, root, list, i, j);
			}
		}

		return list;
	}

	private void dfs(char[][] board, TrieNode node, List<String> list, int r, int c)
	{
		if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '.')
		{
			return;
		}

		char temp = board[r][c];
		int i = temp - 'a';
		node = node.children[i];

		if (node == null)
		{
			return;
		}
		else if (!node.added && node.word != null)
		{
			node.added = true;
			list.add(node.word);
		}

		board[r][c] = '.';

		dfs(board, node, list, r + 1, c);
		dfs(board, node, list, r - 1, c);
		dfs(board, node, list, r, c + 1);
		dfs(board, node, list, r, c - 1);

		board[r][c] = temp;
	}

	private TrieNode buildTrie(String[] words)
	{
		TrieNode root = new TrieNode();
		TrieNode pointer;
		char[] cArray;
		int index;

		for (String word : words)
		{
			pointer = root;
			cArray = word.toCharArray();

			for (int i = 0; i < cArray.length; i++)
			{
				index = cArray[i] - 'a';

				if (pointer.children[index] == null)
				{
					pointer.children[index] = new TrieNode();
				}

				pointer = pointer.children[index];

				if (i == cArray.length - 1)
				{
					pointer.word = word;
				}
			}
		}

		return root;
	}

	// A popular solution, there are two limitations:
	// 1. Duplicated word will be added into the list thus requires
	// a hash set or implementation of a remove method in the Trie.
	// 2. Always start from the root node in every search call.
	/*public List<String> findWords(char[][] board, String[] words)
	{
		HashSet<String> list = new HashSet<>();
		List<String> res = new ArrayList<>();

		if (board == null || words == null)
		{
			return res;
		}

		Trie trie = new Trie();
		StringBuilder sb;

		for (String word : words)
		{
			trie.insert(word);
		}

		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				sb = new StringBuilder();
				backTracking(trie, sb, board, list, i, j);
			}
		}

		res.addAll(list);

		return res;
	}

	private void backTracking(Trie trie, StringBuilder sb, char[][] board, HashSet<String> list, int r, int c)
	{
		if (r < 0 || r >= board.length || c < 0 || c >= board[0].length ||
				board[r][c] == '.')
		{
			return;
		}

		StringBuilder newSb = new StringBuilder(sb);
		newSb.append(board[r][c]);

		if (!trie.startsWith(sb.toString()))
		{
			return;
		}

		if (trie.search(sb.toString()))
		{
			list.add(sb.toString());

			return;
		}

		char temp = board[r][c];
		board[r][c] = '.';

		backTracking(trie, newSb, board, list, r + 1, c);
		backTracking(trie, newSb, board, list, r - 1, c);
		backTracking(trie, newSb, board, list, r, c + 1);
		backTracking(trie, newSb, board, list, r, c - 1);

		board[r][c] = temp;
	}*/
}
