/**
 * Created by oscar on 8/03/17.
 */
class StringDiff
{
    public String solution(String S, String T)
    {
        // write your code in Java SE 8
        char[] sArr = S.toCharArray();
        char[] tArr = T.toCharArray();

        if (sArr.length == tArr.length)
        {
            boolean leftFound = false;
            boolean swapped = false;
            char c = '0';
            char d = '0';

            for (int i = 0; i < sArr.length; i++)
            {
                if (sArr[i] == tArr[i]) continue;

                if (!leftFound)
                {
                    leftFound = true;
                    c = sArr[i];
                    d = tArr[i];
                }
                else
                {
                    if (c == tArr[i] && !swapped)
                        swapped = true;
                    else
                        return "IMPOSSIBLE";
                }
            }

            if (leftFound && !swapped)
                return "IMPOSSIBLE";
            else if (!swapped)
                return "NOTHING";
            else
                return "SWAP " + c + " " + d;
        }
        else if (sArr.length == tArr.length + 1)
        {
            int is = 0;
            int it = 0;
            char c = '0';
            boolean found = false;

            while (it < tArr.length)
            {
                if (sArr[is] == tArr[it])
                {
                    is++;
                    it++;
                    continue;
                }

                if (found) return "IMPOSSIBLE";

                found = true;
                c = sArr[is];
                is++;
            }

            if (!found) return "DELETE " + sArr[sArr.length - 1];
            else return "DELETE " + c;
        }
        else if (tArr.length == sArr.length + 1)
        {
            int is = 0;
            int it = 0;
            char c = '0';
            boolean found = false;

            while (it < sArr.length)
            {
                if (sArr[is] == tArr[it])
                {
                    is++;
                    it++;
                    continue;
                }

                if (found) return "IMPOSSIBLE";

                found = true;
                c = tArr[is];
                it++;
            }

            if (!found) return "INSERT " + tArr[tArr.length - 1];
            return "INSERT " + c;
        }

        return "IMPOSSIBLE";
    }
}
