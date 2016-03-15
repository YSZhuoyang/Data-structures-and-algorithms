/**
 * Created by oscar on 3/16/16.
 */
public class RemoveDuplicatesFromSortedArray
{
    // My solution
    public int removeDuplicates(int[] nums)
    {
        if (nums == null || nums.length < 1)
        {
            return 0;
        }
        else if (nums.length == 1)
        {
            return 1;
        }

        int temp = nums[0] - 1;
        int count = 0;
        int left = 0;
        int right;

        // Find all duplicates and assign them with temp var
        while (left < nums.length)
        {
            right = left + 1;

            while (right < nums.length && nums[left] == nums[right])
            {
                nums[right] = temp;
                right++;
            }

            left = right;
            count++;
        }

        // Delete all duplicates
        left = 1;

        while (left < count)
        {
            right = left + 1;

            if (nums[left] == temp)
            {
                while (right < nums.length && nums[right] == temp)
                {
                    right++;
                }

                if (right < nums.length)
                {
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
            }

            left++;
        }

        return count;
    }
}
