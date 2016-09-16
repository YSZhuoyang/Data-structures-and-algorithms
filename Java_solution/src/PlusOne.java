/**
 * Created by oscar on 6/23/16.
 */
public class PlusOne
{

	// My solution
	public int[] plusOne(int[] digits)
	{
		if (digits == null || digits.length == 0)
		{
			return null;
		}

		int[] res = new int[digits.length];
		int carry = (digits[digits.length - 1] + 1) / 10;
		res[digits.length - 1] = (digits[digits.length - 1] + 1) % 10;

		for (int i = digits.length - 2; i >= 0; i--)
		{
			res[i] = (digits[i] + carry) % 10;
			carry = (digits[i] + carry) / 10;
		}

		if (carry > 0)
		{
			res = new int[digits.length + 1];
			res[0] = carry;

			return res;
		}
		else
		{
			return res;
		}
	}
}
