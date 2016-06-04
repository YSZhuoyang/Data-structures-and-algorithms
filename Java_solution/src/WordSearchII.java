import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by oscar on 6/4/16.
 */
public class WordSearchII
{

	// A popular solution, a limitation is that duplicated word will
	// be added into the list thus requires a hash set or implementation
	// of a remove method in the Trie.
	public List<String> findWords(char[][] board, String[] words)
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
	}
}
