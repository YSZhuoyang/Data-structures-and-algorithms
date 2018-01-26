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
	} else {
		return p1
	}
}
