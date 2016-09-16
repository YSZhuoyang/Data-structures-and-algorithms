import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oscar on 5/17/16.
 */
public class PowerOfFour
{

	// An interesting solution using regular expression
	public boolean isPowerOfFour(int num)
	{
		if (num > 0)
		{
			String bString = Integer.toBinaryString(num);
			System.out.println(bString);

			String pattern = "^1(00)*$";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(bString);

			return m.find();
		}

		return false;
	}

	/* An alternative
	public boolean isPowerOfFour(int num)
	{
		int comp = 1;

		while (num > comp)
		{
			if (num % comp != 0)
			{
				return false;
			}
			else
			{
				comp = comp << 2;
			}
		}

		return num == comp;
	}*/
}
