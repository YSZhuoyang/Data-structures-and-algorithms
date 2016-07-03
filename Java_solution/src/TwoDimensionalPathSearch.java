/**
 * Created by oscar on 5/29/16.
 *
 * Received from Microsoft interview.
 */
public class TwoDimensionalPathSearch
{

	// Given a n x m 2D board where each cell contains either 1 or 0.
	// Find a path from top left to bottom right (can move horizontally, vertically
	// or diagonally) and can only move to the cells containing 1.
	// Assume params n and m are always equal to number of rows and columns.
	// This is a naive solution, with a time complexity of n x m.
	public boolean pathSearch(int[][] board, int n, int m)
	{
		if (board == null || n <= 0 || m <= 0 || board[n - 1][m - 1] == 0)
		{
			return false;
		}

		return move(board, 0, 0, n, m);
	}

	private boolean move(int[][]board, int r, int c, int n, int m)
	{
		if (r < 0 || r >= n || c < 0 || c >= m || board[r][c] == 0)
		{
			return false;
		}
		else if (r == n - 1 && c == m - 1)
		{
			return true;
		}

		board[r][c] = 0;

		for (int i = r - 1; i <= r + 1; i++)
		{
			for (int j = c - 1; j <= c + 1; j++)
			{
				if (move(board, i, j, n, m))
				{
					return true;
				}
			}
		}

		board[r][c] = 1;

		return false;
	}
}
