/**
 * Created by oscar on 7/14/16.
 *
 * Set all bits between index i (starting from 0 at right most) and j in N equal to M.
 */
public class UpdateBits
{
	int max = 2147483647;

	// My solution.
	public int updateBits(int n, int m, int i, int j)
	{
		// Compute mask
		int left = max - (1 << (j + 1) - 1);
		int right = 1 << i - 1;
		int mask = left | right;

		// Turn bits to 0
		n = n & mask;

		// Update bits
		n = n | (m << i);

		return n;
	}
}
