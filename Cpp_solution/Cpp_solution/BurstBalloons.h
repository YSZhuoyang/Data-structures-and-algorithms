#pragma once
#include <iostream>
#include <vector>


using namespace std;

class BurstBalloons
{
public:
	int max = 0;

	int maxCoins( vector<int>& nums )
	{
		getTotalCoins( nums, 0, 0 );

		return max;
	}

	void getTotalCoins( vector<int>& nums, int coins, int i )
	{
		if (nums.size() == 1)
		{
			//cout << "max" << max << endl;

			if (max < coins + nums[0])
			{
				max = coins + nums[0];
			}
		}
		else
		{
			for (unsigned int i = 0; i < nums.size(); i++)
			{
				vector<int> copy( nums );

				int coins_copy = coins;
				coins_copy += getCoins( copy, i );
				getTotalCoins( copy, coins_copy, i );
			}
		}
	}

	int getCoins( vector<int>& nums, unsigned int index )
	{
		int left;
		int right;
		int middle;

		middle = nums[index];

		if (index == 0)
		{
			left = 1;
		}
		else
		{
			left = nums[index - 1];
		}

		if (index >= nums.size() - 1)
		{
			right = 1;
		}
		else
		{
			right = nums[index + 1];
		}

		//cout << "coins" << coins << endl;

		nums.erase( nums.begin() + index );

		return left * middle * right;
	}
};
