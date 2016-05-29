/**
 * Created by oscar on 5/29/16.
 *
 * Received from Microsoft interview.
 */
public class TwoDimensionalPathSearch
{

	// Given a n x m 2D board where each cell contains either 1 or 0.
	// Find a path from top left to bottom right (can move horizontally, vertically
	// or diagonally) and only move to the cells containing 1.
	// This is a naive solution, with a time complexity of 2 to n by m
	public boolean pathSearch(int[][] board)
	{
		if (board == null || board.length == 0 || board[0].length == 0)
		{
			return false;
		}

		return move(board, 0, 0);
	}

	private boolean move(int[][]board, int r, int c)
	{
		if (r == board.length - 1 && c == board[0].length - 1)
		{
			return true;
		}

		board[r][c] = 0;

		int lowerBoundary = (r - 1 > 0)? r - 1 : 0;
		int upperBoundary = (r + 1 < board.length)? r + 1 : board.length - 1;
		int leftBoundary = (c - 1 > 0)? c - 1 : 0;
		int rightBoundary = (c + 1 < board[0].length)? c + 1 : board[0].length - 1;

		for (int i = lowerBoundary; i <= upperBoundary; i++)
		{
			for (int j = leftBoundary; j <= rightBoundary; j++)
			{
				if (board[i][j] == 1) //(i != r || j != c) &&
				{
					if (move(board, i, j))
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
