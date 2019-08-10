# From: https://leetcode.com/discuss/interview-question/341247/Facebook-and-Google-or-Phone-screen-or-Leftmost-column-index-of-1
#
# In a binary matrix (all elements are 0 and 1), every row is sorted
# in ascending order (0 to the left of 1). Find the leftmost column
# index with a 1 in it.
#
# Similar to: https://leetcode.com/problems/search-a-2d-matrix-ii/

# Example 1:

# Input:
# [[0, 0, 0, 1],
#  [0, 0, 1, 1],
#  [0, 1, 1, 1],
#  [0, 0, 0, 0]]

# Input range:

# Idea:
#   1. Start from top right corner, find
#      the left most 1 in the 1st row (or b search for it)
#   2. Go down to the next row, if its 0 then go down again,
#      otherwise go left (or bsearch for) the left most
#      position of 1 in this row.
#   3. Repeat step 2 until the end
#
# Time: O(M + N)
# Space: O(1) ignoring the space taken by the input


def findLeftMostCol(grid):
    nr, nc = len(grid), len(grid[0])
    currC = nc - 1
    for r in range(nr - 1, -1, -1):
        currC = findFirst1(grid[r], currC) if grid[r][currC] == 1 else currC

    return currC


def findFirst1(row, c):
    l = len(row)
    i = c
    while i >= 0 and row[i]:
        i -= 1

    return 0 if i == -1 else i + 1


print(findLeftMostCol([[0, 0, 0, 1], [0, 0, 1, 1], [0, 1, 1, 1], [0, 0, 0,
                                                                  0]]))
