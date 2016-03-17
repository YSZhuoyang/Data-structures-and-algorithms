/**
 * Created by oscar on 3/17/16.
 */
public class RemoveElement
{
    public int removeElement(int[] nums, int val)
    {
        int left = 0;
        int right = 0;

        if (nums == null || nums.length == 0)
        {
            return 0;
        }

        while (right < nums.length)
        {
            if (nums[right] != val)
            {
                nums[left] = nums[right];
                left++;
            }

            right++;
        }

        return left;
    }
}
