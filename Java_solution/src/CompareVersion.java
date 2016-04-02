/**
 * Created by oscar on 4/2/16.
 */
public class CompareVersion
{

    // A recommended solution
    public int compareVersion(String version1, String version2)
    {
        int l1 = version1.length();
        int l2 = version2.length();
        int maxLen = Math.max(l1, l2);

        for (int i = 0, j = 0; i < l1 || j < l2; i++, j++)
        {
            int temp1 = 0;
            int temp2 = 0;

            while (i < l1 && version1.charAt(i) != '.')
            {
                temp1 = temp1 * 10 + version1.charAt(i++) - '0';
            }

            while (j < l2 && version2.charAt(j) != '.')
            {
                temp2 = temp2 * 10 + version2.charAt(j++) - '0';
            }

            if (temp1 > temp2)
            {
                return 1;
            }
            else if (temp1 < temp2)
            {
                return -1;
            }
        }

        return 0;
    }

    /* My solution
    public int compareVersion(String version1, String version2)
    {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        float temp1, temp2;
        int l1 = v1.length;
        int l2 = v2.length;
        int maxLen = Math.max(l1, l2);

        for (int i = 0; i < maxLen; i++)
        {
            if (i < l1)
            {
                temp1 = Float.parseFloat(v1[i]);
            }
            else
            {
                temp1 = 0;
            }

            if (i < l2)
            {
                temp2 = Float.parseFloat(v2[i]);
            }
            else
            {
                temp2 = 0;
            }

            if (temp1 > temp2)
            {
                return 1;
            }
            else if (temp1 < temp2)
            {
                return -1;
            }
        }

        return 0;
    }*/
}
