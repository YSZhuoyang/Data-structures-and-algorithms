package GoSolution

func twoSum(nums []int, target int) []int {
	if nums == nil || len(nums) < 2 {
		return nil
	}

	numIDM := make(map[int]int)

	for i, num := range nums {
		iPre, ok := numIDM[target-num]
		if ok {
			return []int{iPre, i}
		}

		numIDM[num] = i
	}

	return nil
}
