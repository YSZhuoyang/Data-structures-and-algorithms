# Given an array A that is a permutation of n numbers [1-n]. Find the number of subarrarys S that meets the following condition max(S) - min(S) = length(S) - 1.
#
# Example:
#
# Input: [4, 3, 1, 2, 5]
# Output: 10
#
# Idea: memorization with substructure using 2 2D arrays of starting & ending index for min & max respectively, where:
#       mins[s][e] = min(mins[s][e - 1], A[e - 1])
#       maxs[s][e] = max(maxs[s][e - 1], A[e - 1])
#
# Time: O(N ^ 2)


def findSubarrays(A):
    l = len(A)
    mins = [[None] * l for _ in range(l)]
    maxs = [[None] * l for _ in range(l)]

    numSubs = 0
    # Base cases:
    for i in range(l):
        mins[i][i] = A[i]
        maxs[i][i] = A[i]
        numSubs += 1 if maxs[i][i] - mins[i][i] == 0 else 0
    # General cases:
    for s in range(0, l - 1):
        for e in range(s + 1, l):
            mins[s][e] = min(mins[s][e - 1], A[e])
            maxs[s][e] = max(maxs[s][e - 1], A[e])
            numSubs += 1 if maxs[s][e] - mins[s][e] == e - s else 0

    return numSubs


# Test
print(findSubarrays([4, 3, 1, 2, 5]))
