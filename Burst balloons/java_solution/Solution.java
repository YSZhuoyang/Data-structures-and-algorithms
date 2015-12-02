public class Solution
{
    public int maxCoins(int[] nums)
    {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] newNums = new int[n + 2];
        
        for (int i = 0; i < n; i++)
        {
            newNums[i + 1] = nums[i];
        }
        
        newNums[0] = newNums[n + 1] = 1;
        
        return divide(1, n, newNums, dp);
    }
    
    public int divide(int i, int j, int[] nums, int[][] dp)
    {
        if (dp[i][j] > 0)
        {
            return dp[i][j];
        }
        
        for (int x = i; x <= j; x++)
        {
            dp[i][j] = Math.max(dp[i][j], 
                divide(i, x - 1, nums, dp) + nums[i - 1] * nums[x] * nums[j + 1] + divide(x + 1, j, nums, dp));
        }
        
        return dp[i][j];
    }
}