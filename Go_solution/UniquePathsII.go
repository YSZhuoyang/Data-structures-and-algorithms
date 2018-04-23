package GoSolution

func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	if obstacleGrid == nil {
		return 0
	}

	nR := len(obstacleGrid)
	nC := len(obstacleGrid[0])

	if nR == 0 || nC == 0 {
		return 0
	}

	memo := make([][]int, nR)
	for i := range memo {
		memo[i] = make([]int, nC)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	return moveWithObstacles(0, 0, nC, nR, obstacleGrid, memo)
}

func moveWithObstacles(currC int, currR int, nC int, nR int, obstacleGrid [][]int, memo [][]int) int {
	if currC < 0 || currC >= nC || currR < 0 || currR >= nR || obstacleGrid[currR][currC] == 1 {
		return 0
	} else if currC == nC-1 && currR == nR-1 {
		return 1
	}

	cache := memo[currR][currC]
	if cache != -1 {
		return cache
	}

	origin := obstacleGrid[currR][currC]
	obstacleGrid[currR][currC] = 1
	numRight := moveWithObstacles(currC+1, currR, nC, nR, obstacleGrid, memo)
	numDown := moveWithObstacles(currC, currR+1, nC, nR, obstacleGrid, memo)
	obstacleGrid[currR][currC] = origin

	numTotal := numRight + numDown
	memo[currR][currC] = numTotal

	return numTotal
}
