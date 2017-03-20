/**
 * Created by oscar on 20/03/17.
 */
public class LongestValidPass
{

    public int solution(String S)
    {
        int max = -1;
        int start = 0;
        boolean next = false;
        boolean valid = false;
        char cArr[] = S.toCharArray();

        if (cArr.length == 1)
            return isUpper(cArr[0]) ? 1 : 0;

        for (int i = 0; i < cArr.length; i++)
        {
            if (!next && !isDigit(cArr[i]))
            {
                next = true;
                valid = false;
                start = i;

                if (isUpper(cArr[i]))
                    valid = true;
            }
            else if (next)
            {
                if (!valid && isUpper(cArr[i]))
                    valid = true;

                if (!isDigit(cArr[i]) && i == cArr.length - 1 &&
                    valid)
                {
                    int len = i - start + 1;
                    if (len > max)
                        max = len;
                }
                else if (isDigit(cArr[i]))
                {
                    if (valid)
                    {
                        next = false;
                        int len = i - start;
                        if (len > max)
                            max = len;
                    }
                    else
                        next = false;
                }
            }
        }

        return max;
    }

    private boolean isUpper(char c)
    {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isDigit(char c)
    {
        return c >= '0' && c <= '9';
    }
}
