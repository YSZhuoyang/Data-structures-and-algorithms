import java.util.TreeMap;

/**
 * Created by oscar on 7/03/17.
 */
public class NailingPlanks
{
    public int solution(int[] A, int[] B, int[] C)
    {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        // Number of nails used
        int count = 0;

        for (int i = 0; i < A.length; i++)
        {
            Integer usedNail = map.ceilingKey(A[i]);

            // A same nail can be used.
            if (usedNail != null && usedNail <= B[i])
                continue;

            // Need to find another nail.
            int j = count;

            for (; j < C.length; j++)
            {
                map.put(C[j], j);
                count++;

                // Another suitable nail found.
                if (C[j] >= A[i] && C[j] <= B[i])
                    break;
            }

            if (j == C.length) return -1;
        }

        return count;
    }
}
