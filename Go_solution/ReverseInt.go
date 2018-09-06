package GoSolution

func reverse(x int) int {
	if x < 10 && x > -10 {
		return x
	}

	x2 := x
	if x < 0 {
		x2 *= -1
	}

	rev := 0
	for x2 > 0 {
		d := x2 % 10
		x2 /= 10
		if d == 0 && rev == 0 {
			continue
		}

		rev = rev*10 + d
		if rev > 2147483647 {
			return 0
		}
	}

	if x < 0 {
		return rev * -1
	}

	return rev
}
