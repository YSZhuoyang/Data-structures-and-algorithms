/**
 * Created by oscar on 4/3/16.
 */
public class LengthOfLastWord
{

    // My solution
    public int lengthOfLastWord(String s)
    {
        if (s == null)
        {
            return 0;
        }

        s = s.trim();

        if (s.isEmpty())
        {
            return 0;
        }

        int len = s.length();
        int i = len - 1;

        while (i >= 0 && !Character.isWhitespace(s.charAt(i)))
        {
            i--;
        }

        return len - 1 - i;
    }

	private boolean isWhitespace(char c)
	{
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
}
