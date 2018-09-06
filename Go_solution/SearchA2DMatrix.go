package GoSolution

func searchMatrix(matrix [][]int, target int) bool {
	if matrix == nil || len(matrix) == 0 || len(matrix[0]) == 0 {
		return false
	}

	return binaryRows(matrix, target, 0, len(matrix)-1)
}

func binaryRows(matrix [][]int, target int, start int, end int) bool {
	rowLen := len(matrix[0])

	if start == end {
		return binaryRow(matrix[start], 0, rowLen-1, target)
	} else if start > end {
		return false
	}

	middle := (start + end) / 2

	if matrix[middle][rowLen-1] < target {
		return binaryRows(matrix, target, middle+1, end)
	} else if matrix[middle][0] > target {
		return binaryRows(matrix, target, start, middle-1)
	} else {
		return binaryRow(matrix[middle], 0, rowLen-1, target)
	}
}

func binaryRow(row []int, start int, end int, target int) bool {
	if start == end {
		return row[start] == target
	} else if start > end {
		return false
	}

	middle := (start + end) / 2

	if row[middle] > target {
		return binaryRow(row, start, middle-1, target)
	} else if row[middle] < target {
		return binaryRow(row, middle+1, end, target)
	} else {
		return true
	}
}
