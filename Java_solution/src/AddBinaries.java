/**
 * Created by oscar on 4/2/16.
 */
public class AddBinaries
{

    // A very fast, extendable solution (suitable for any carry system)
    public String addBinary(String a, String b/* , int carry */)
    {
        int c = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0;)
        {
            if (i >= 0) c += a.charAt(i--) - '0';
            if (j >= 0) c += b.charAt(j--) - '0';

            sb.insert(0, (char)((c % 2) + '0'));
            c /= 2;

            /*
            sb.insert(0, (char)((c % carry) + '0'));
            c /= carry;
            */
        }

        if (c == 1) sb.insert(0, "1");

        return sb.toString();
    }

    /* My solution which beats 65.01% java submissions
    public String addBinary(String a, String b)
    {
        int al = a.length();
        int bl = b.length();
        int minLen = Math.min(al, bl);
        int maxLen = Math.max(al, bl);
        boolean carry = false;
        String maxStr = (al > bl)? a : b;
        StringBuilder result = new StringBuilder();

        if (a == "0" && b == "0")
        {
            return "";
        }
        else if (a == "0")
        {
            return b;
        }
        else if (b == "0")
        {
            return a;
        }

        for (int i = al - 1, j = bl - 1; i >= 0 && j >= 0; i--, j--)
        {
            if (a.charAt(i) != b.charAt(j))
            {
                if (carry)
                {
                    result.insert(0, '0');
                }
                else
                {
                    result.insert(0, '1');
                }
            }
            else if (a.charAt(i) == '0') // Both a and b is 0
            {
                if (carry)
                {
                    result.insert(0, '1');
                    carry = false;
                }
                else
                {
                    result.insert(0, '0');
                }
            }
            else // Both a and b is 1
            {
                if (carry)
                {
                    result.insert(0, '1');
                }
                else
                {
                    result.insert(0, '0');
                    carry = true;
                }
            }
        }

        int index = minLen;

        while (index < maxLen)
        {
            if (maxStr.charAt(maxLen - 1 - index) == '0')
            {
                if (carry)
                {
                    result.insert(0, '1');
                    carry = false;
                }
                else
                {
                    result.insert(0, maxStr.substring(0, maxLen - index));
                    break;
                }
            }
            else
            {
                if (carry)
                {
                    result.insert(0, '0');
                }
                else
                {
                    result.insert(0, maxStr.substring(0, maxLen - index));
                    break;
                }
            }

            index++;
        }

        if (carry)
        {
            result.insert(0, '1');
        }

        return result.toString();
    }*/

    /* A short and clean alternative
    public String addBinary(String a, String b)
    {
        int m = a.length();
        int n = b.length();
        int carry = 0;
        String res = "";
        // the final length of the result depends on the bigger length between a and b,
        // (also the value of carry, if carry = 1, add "1" at the head of result, otherwise)
        int maxLen = Math.max(m, n);

        for (int i = 0; i < maxLen; i++)
        {
            // start from last char of a and b
            // notice that left side is int and right side is char
            // so we need to  minus the decimal value of '0'
            int p = i < m ? a.charAt(m - 1 - i) - '0' : 0;
            int q = i < n ? b.charAt(n - 1 - i) - '0' : 0;
            int tmp = p + q + carry;
            carry = tmp / 2;
            res = tmp % 2 + res;
        }

        return (carry == 0) ? res : "1" + res;
    }*/
}
