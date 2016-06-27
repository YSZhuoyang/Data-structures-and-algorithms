/**
 * Created by oscar on 6/27/16.
 */
public class RotateImage
{

	// A recommended solution
	// |-----  |
	// |****| *|
	// |*|**|**|
	// |  --
	// |* ** **|
	// |* ** **|
	// |* ** **|
	// Rotate the part rounded up four times
	public void rotate(int[][] matrix)
	{
		if (matrix == null || matrix.length < 2)
		{
			return;
		}

		int circles = matrix.length / 2;

		for (int i = 0; i < circles; ++i)
		{
			int index = matrix.length - i - 1;

			for (int j = i; j < index; ++j)
			{
				int t = matrix[j][index];
				matrix[j][index] = matrix[i][j];
				matrix[i][j] = matrix[matrix.length - j - 1][i];
				matrix[matrix.length - j - 1][i] = matrix[index][matrix.length - j - 1];
				matrix[index][matrix.length - j - 1] = t;
			}
		}
	}

	// My solution
	// |---   |
	// |**|***|
	// |**|***|
	// |**|***|
	// |---   |
	// |** ***|
	// |** ***|
	// Rotate the part rounded up four times
	/*public void rotate(int[][] matrix)
	{
		if (matrix == null || matrix.length < 2)
		{
			return;
		}

		int n = matrix.length;
        int temp;
        int max = n / 2;

		// If n is an even, we only need to consider i and j ranging from
		// 0 to n / 2 - 1.
        for (int i = 0; i < max; i++)
        {
            for (int j = 0; j < max; j++)
            {
                temp = matrix[i][j];

                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }

        // If n is an odd (say 3), we also need to rotate pixels of which
		// i == n / 2 and j ranging from 0 to n / 2 - 1
        if ((n & 1) != 0)
        {
            int i = n / 2;

            for (int j = 0; j < n / 2; j++)
            {
                temp = matrix[i][j];

                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
	}*/
}
