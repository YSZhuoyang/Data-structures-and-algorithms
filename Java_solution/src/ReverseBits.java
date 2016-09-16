/**
 * Created by oscar on 7/19/16.
 *
 * n is treated as an unsigned value.
 */
public class ReverseBits
{

	// A recommended solution
	public int reverseBits(int n)
	{
		n = (n >> 16) | (n << 16);
		n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
		n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
		n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
		n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);

		return n;
	}

	// My solution which takes O(N) time
	/*public int reverseBits(int n)
	{
		int reverse = 0;

		for (int i = 0; i < 32; i++)
		{
			if ((n & 1) == 1)
			{
				reverse += 1 << (31 - i);
			}

			// Do a unsigned shift
			n >>>= 1;
		}

		return reverse;
	}*/
}
