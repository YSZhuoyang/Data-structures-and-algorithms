/**
 * Created by oscar on 6/27/16.
 */
public class SetMatrixZeros
{

	// My solution
	public void setZeroes(int[][] matrix)
	{
		if (matrix == null || matrix.length == 0)
		{
			return;
		}

		// Record which rows and columns need to be set to 0
		boolean[] rows = new boolean[matrix.length];
		boolean[] columns = new boolean[matrix[0].length];

		// Find all cells with 0
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[0].length; j++)
			{
				if (matrix[i][j] == 0)
				{
					rows[i] = true;
					columns[j] = true;
				}
			}
		}

		// Set rows to 0
		for (int i = 0; i < rows.length; i++)
		{
			if (rows[i])
			{
				for (int j = 0; j < columns.length; j++)
				{
					matrix[i][j] = 0;
				}
			}
		}

		// Set columns to 0
		for (int j = 0; j < columns.length; j++)
		{
			if (columns[j])
			{
				for (int i = 0; i < rows.length; i++)
				{
					matrix[i][j] = 0;
				}
			}
		}
	}
}
