package GoSolution

func uniquePaths(m int, n int) int {
	if n <= 0 || m <= 0 {
		return 0
	}

	memo := make([][]int, n)
	for i := 0; i < n; i++ {
		memo[i] = make([]int, m)
	}

	return move(m, n, 0, 0, memo)
}

func move(m int, n int, currC int, currR int, memo [][]int) int {
	if currC >= m || currC < 0 || currR >= n || currR < 0 {
		return 0
	} else if currC == m-1 && currR == n-1 {
		return 1
	} else if memo[currR][currC] != 0 {
		return memo[currR][currC]
	}

	// Try moving down
	downNumPath := move(m, n, currC, currR+1, memo)
	// Try moving right
	rightNumPath := move(m, n, currC+1, currR, memo)
	numPath := downNumPath + rightNumPath
	memo[currR][currC] = numPath

	return numPath
}
