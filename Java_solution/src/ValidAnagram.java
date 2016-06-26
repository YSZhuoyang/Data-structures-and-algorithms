import java.util.Arrays;

/**
 * Created by oscar on 4/5/16.
 */
public class ValidAnagram
{

    // My solution which takes constant extra space but n time complexity
    // Beat 91.93% java submissions
    // Suits ascII characters
    public boolean isAnagram(String s, String t)
    {
        if (s == null || t == null || s.length() != t.length())
        {
            return false;
        }

        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int[] hashTable1 = new int[128];
        int[] hashTable2 = new int[128];

        for (int i = 0; i < arr1.length; i++)
        {
            hashTable1[arr1[i]]++;
            hashTable2[arr2[i]]++;
        }

        for (int i = 0; i < hashTable1.length; i++)
        {
            if (hashTable1[i] != hashTable2[i])
            {
                return false;
            }
        }

        return true;
    }

    // Another solution with nlog(n) time complexity but no extra space used
    // Beat 83.14% java submissions
    // Suits unicode characters
    /*public boolean isAnagram(String s, String t)
    {
        if (s.isEmpty() && t.isEmpty())
        {
            return true;
        }

        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();

        Arrays.sort(chars);
        Arrays.sort(chart);

        if (chars.length != chart.length)
        {
            return false;
        }

        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] != chart[i])
            {
                return false;
            }
        }

        return true;
    }*/
}
