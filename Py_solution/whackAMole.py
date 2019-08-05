# From: https://leetcode.com/discuss/interview-question/350139/google-phone-screen-whac-a-mole

# Given an int array holes where 1 means there is a mole, 0 means no mole. Find out the max number of moles you can hit with a mallet of width w.

# Input Range:
#
#

# Idea: sliding window

# Time: O(N)

# Space: O(1) ignoring the space consumption of given array


# With 1 mallet
def whackAMole(moles, mallet):
    w = mallet
    n = len(moles)
    if n <= w:
        return sum(moles)

    # Init a sliding window
    total = sum(moles[:w])
    maxHit = total
    # Move the window to the right
    for i in range(w, n):
        total = total + moles[i] - moles[i - w]
        maxHit = max(maxHit, total)

    return maxHit


# With 2 mallets
#
# Idea:
#   1. Divide the problem by spliting the array into 2
#      and reuse whackAMole solution to solve each of them
#   2. Memorize results of subproblems
#
# Time: O(N)
#
# Space:
def whackAMole2(moles, mallet):
    w = mallet
    n = len(moles)
    maxH = 0
    for i in range(w, n - w + 1):
        left = whackAMole(moles[:i], mallet)
        right = whackAMole(moles[i:], mallet)
        maxH = max(maxH, left + right)

    return maxH


print(whackAMole([0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0], 5))
print(whackAMole2([0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0], 5))