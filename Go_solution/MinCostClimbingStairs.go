package GoSolution

// Idea:
// 1. lowestCost(x) = lowest(x - 1) + cost[x - 1] OR lowest(x - 2) + cost[x - 2]
// 2. Use a hash map or array to store lowest costs

func minCostClimbingStairs(cost []int) int {
	if cost == nil {
		return 0
	}

	l := len(cost)
	if l < 2 || l > 1000 {
		panic("Given input 'cost' must be a list with length in range [2, 1000]")
	}

	costMemo := make(map[int]int)
	return climb(l, cost, costMemo)
}

// Solution 1: Linear space, linear time
// By top down recursion
func climb(i int, cost []int, costMemo map[int]int) int {
	if i < 2 {
		return 0
	}

	p1, ok := costMemo[i-1]
	if !ok {
		p1 = climb(i-1, cost, costMemo) + cost[i-1]
		costMemo[i-1] = p1
	}

	p2, ok := costMemo[i-2]
	if !ok {
		p2 = climb(i-2, cost, costMemo) + cost[i-2]
		costMemo[i-2] = p2
	}

	if p1 > p2 {
		return p2
	}

	return p1
}

// Solution 2: constant space, linear time
// By bottom up topological sort
func climb2(cost []int) int {
	var memo [2]int
	memo[0] = 0
	memo[1] = 0
	l := len(cost)
	for i := 1; i < l; i++ {
		// Idea:
		// TotalCost1(n) = TotalCost(n - 1) + Cost(n - 1)
		// TotalCost2(n) = TotalCost(n - 2) + Cost(n - 2)
		// TotalCost(n) = min(TotalCost1(n), TotalCost2(n))

		t1 := memo[1] + cost[i]
		t2 := memo[0] + cost[i-1]
		memo[0] = memo[1]
		if t1 < t2 {
			memo[1] = t1
		} else {
			memo[1] = t2
		}
	}

	return memo[1]
}
