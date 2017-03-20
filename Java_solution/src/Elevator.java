import java.util.*;

/**
 * Created by oscar on 20/03/17.
 */
public class Elevator
{
    public int solution(int[] A, int[] B, int M, int X, int Y)
    {
        HashMap<Integer, Integer> f = new HashMap<>();
        int pSum = 0;
        int sSum = 0;
        int wSum = 0;

        for (int i = 0; i < A.length; i++)
        {
            if (wSum + A[i] > Y || pSum + 1 > X)
            {
                sSum += f.size() + 1;

                pSum = 0;
                wSum = 0;
                f.clear();
                i--;
            }
            else
            {
                f.put(B[i], 0);
                wSum += A[i];
                pSum++;

                if (i == A.length - 1)
                    sSum += f.size() + 1;
            }
        }

        return sSum;
    }
}
