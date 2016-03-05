/**
 * Created by oscar on 3/5/16.
 */
public class WordSearch
{
    // My solution (time limit exceeded)
    private boolean[][] testedPos;

    public boolean exist(char[][] board, String word)
    {
        if (board == null || board.length * board[0].length < word.length())
        {
            return false;
        }

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                testedPos = new boolean[board.length][board[0].length];

                if (backTracking(board, i, j, word, 0))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean backTracking(char[][] board, int r, int c, String word, int i)
    {
        if (board[r][c] == word.charAt(i))
        {
            if (i == word.length() - 1)
            {
                return true;
            }

            testedPos[r][c] = true;

            if (c > 0 && !testedPos[r][c - 1] && backTracking(board, r, c - 1, word, i + 1))
            {
                return true;
            }
            else if (c < (board[r].length - 1) && !testedPos[r][c + 1] && backTracking(board, r, c + 1, word, i + 1))
            {
                return true;
            }
            else if (r < (board.length - 1) && !testedPos[r + 1][c] && backTracking(board, r + 1, c, word, i + 1))
            {
                return true;
            }
            else
            {
                return r > 0 && !testedPos[r - 1][c] && backTracking(board, r - 1, c, word, i + 1);
            }
        }
        else
        {
            return false;
        }
    }
}
