import java.util.*;

/**
 * Created by oscar on 3/13/16.
 */
public class FourSum
{
    // My solution
    public List<List<Integer>> fourSum(int[] nums, int target)
    {
        // Each element has a pair of numbers and their sum
        // total number of pairs: (1 + (n - 1)) * n / 2
        int[][] pairs = new int[nums.length * (nums.length - 1) / 2][3];
        int index = 0;
        ArrayList<List<Integer>> results = new ArrayList<>();

        if(nums.length < 4)
        {
            return results;
        }

        Arrays.sort(nums);

        // Compute and initialize the pairs of sum of two array
        for (int i = 0; i < nums.length - 1; i++)
        {
            if (i > 1 && nums[i] == nums[i - 1])
            {
                continue;
            }

            // Avoid duplication
            for (int j = i + 1; j < nums.length; j++)
            {
                if (j > i + 1 && nums[j] == nums[j - 1])
                {
                    continue;
                }

                pairs[index][0] = i;
                pairs[index][1] = j;
                pairs[index][2] = nums[i] + nums[j];

                index++;
            }
        }

        // Find the two elements from the pairs array
        for (int i = 0; i < index - 1; i++)
        {
            for (int j = i + 1; j < index; j++)
            {
                if (pairs[i][2] + pairs[j][2] == target && pairs[i][1] < pairs[j][0])
                {
                    results.add(Arrays.asList(nums[pairs[i][0]], nums[pairs[i][1]], nums[pairs[j][0]], nums[pairs[j][1]]));
                }
            }
        }

        return results;
    }

    // A good solution
    /*public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> abcd = new ArrayList<>();
        if(nums.length<4) return abcd;
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-3;i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            for(int j = i+1; j<nums.length-2;j++){
                if(j>i+1 && nums[j]== nums[j-1]) continue;
                int low = j+1, high = nums.length-1;
                while(low<high){
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if(sum==target){
                        abcd.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while(low<high&&nums[low]==nums[low+1])low++;
                        while(low<high&&nums[high]==nums[high-1])high--;
                        low++;
                        high--;
                    }
                    else if(sum<target){
                        low++;
                    }
                    else high--;
                }
            }
        }
        return abcd;
    }*/
}
