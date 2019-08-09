# From: https://leetcode.com/discuss/interview-question/353827/google-onsite-shortest-path-breaking-through-walls

# Given a 2D grid of size r * c. 0 is walkable, and 1 is a wall.
# You can move up, down, left or right at a time. Now you are allowed
# to break at most 1 wall, what is the minimum steps to walk from
# the upper left corner (0, 0) to the lower right corner (r-1, c-1)?

# Example 1:

# Input:
# [[0, 1, 0, 0, 0],
#  [0, 0, 0, 1, 0],
#  [1, 1, 0, 1, 0],
#  [1, 1, 1, 1, 0]]

# Output: 7
# Explanation: Change `1` at (0, 1) to `0`, the shortest path is as follows:
# (0, 0) -> (0, 1) -> (0, 2) -> (0, 3) -> (0, 4) -> (1, 4) -> (2, 4) -> (3, 4)
# There are other options of length 7, not listed here.

# Input range:
# < r <
# < c <

# Ideas:
#
# 1. DFS backtrack with memorization (DP)
# Time: O(N * M) since the shorted distance from each node to the destination
#       (the most right bottom node) is calculated once
# Space: O(N * M)
#
# Note: without memorization, simple DFS takes O(3 ^ (N * M)) time given the graph
#       where the max number of children of a parent node is 3
#
#
# 2. Dij's algorithm:
#   with a counter of broken wall, due to the condition of breaking
#   at most 1 wall, a heap has to be used to keep track of the best candidate
#
# Time: O(N * M * log(N * M))
# Space: O(N * M)
#
# Note: without the breaking wall condition, a simple BFS can do the job with O(N * M) time

import sys


def breakThroughWall(grid):
    nr = len(grid)
    if nr == 0:
        return 0

    nc = len(grid[0])
    if nc == 0:
        return 0

    return dfs(grid, 0, 0, False, set(), {}) - 1


# DFS backtrack with memorization of shortest dist from each node
def dfs(g, r, c, broken, visited, memo):
    if outOfBound(r, c, g) or (r, c) in visited:
        return sys.maxsize
    elif g[r][c] and broken:
        # Its a wall and there's another wall broken before
        return sys.maxsize
    elif (r, c) in memo:
        return memo[(r, c)]
    if isDest(r, c, g):
        return 1

    # Add it to the visited path
    visited.add((r, c))

    broken != bool(g[r][c])

    up = dfs(g, r + 1, c, broken, visited, memo)
    down = dfs(g, r - 1, c, broken, visited, memo)
    right = dfs(g, r, c + 1, broken, visited, memo)
    left = dfs(g, r, c - 1, broken, visited, memo)

    # Remove it from the path for backtracking
    visited.remove((r, c))

    memo[(r, c)] = min(up, down, right, left) + 1

    return memo[(r, c)]


def isDest(r, c, g):
    nr, nc = len(g), len(g[0])
    return r == nr - 1 and c == nc - 1


def outOfBound(r, c, g):
    nr, nc = len(g), len(g[0])
    return r < 0 or r >= nr or c < 0 or c >= nc


print(
    breakThroughWall([[0, 1, 0, 0, 0], [0, 0, 0, 1, 0], [1, 1, 0, 1, 0],
                      [1, 1, 1, 1, 0]]))
