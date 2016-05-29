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
	// This is a naive solution, with a time complexity of 2 to n by m.
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
		if (r == n - 1 && c == m - 1)
		{
			return true;
		}

		board[r][c] = 0;

		int lowerBoundary = (r - 1 > 0)? r - 1 : 0;
		int upperBoundary = (r + 1 < n)? r + 1 : n - 1;
		int leftBoundary = (c - 1 > 0)? c - 1 : 0;
		int rightBoundary = (c + 1 < m)? c + 1 : m - 1;

		for (int i = lowerBoundary; i <= upperBoundary; i++)
		{
			for (int j = leftBoundary; j <= rightBoundary; j++)
			{
				if (board[i][j] == 1)
				{
					if (move(board, i, j, n, m))
					{
						return true;
					}
				}
			}
		}

		board[r][c] = 1;

		return false;
	}
}
