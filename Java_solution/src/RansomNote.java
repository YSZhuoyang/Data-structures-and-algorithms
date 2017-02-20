import java.util.Arrays;


/**
 * Created by oscar on 20/02/17.
 */
public class RansomNote
{
    /**
     * Time: O(n)
     * 1. Array storing frequencies.
     */
    public boolean canConstruct1(String ransomNote, String magazine)
    {
        int[] table = new int[128];
        for (char c : magazine.toCharArray()) table[c]++;
        for (char c : ransomNote.toCharArray())
            if (--table[c] < 0) return false;

        return true;
    }

    /**
     * My solution:
     * Time: O(nlog(n))
     * 1. Sort.
     * 2. Use two pointers.
     */
    public boolean canConstruct2(String ransomNote, String magazine)
    {
        if (ransomNote == null || magazine == null ||
                magazine.length() < ransomNote.length())
            return false;
        else if (ransomNote.isEmpty()) return true;

        char[] ranArr = ransomNote.toCharArray();
        char[] magaArr = magazine.toCharArray();

        // Sort arr1
        Arrays.sort(ranArr);

        // Sort arr2
        Arrays.sort(magaArr);

        int j = 0;
        int magLen = magazine.length();
        int ranLen = ransomNote.length();
        boolean found = false;

        for (int i = 0; i < ranLen; i++)
        {
            found = false;
            // System.out.println(i);
            // System.out.println("ran: " + ranArr[i]);

            while (j < magLen && ranArr[i] >= magaArr[j])
            {
                // System.out.println("mag: " + magaArr[j]);

                if (magaArr[j] == ranArr[i])
                {
                    found = true;
                    j++;
                    break;
                }

                j++;
            }

            if (!found) break;
        }

        return found;
    }
}
