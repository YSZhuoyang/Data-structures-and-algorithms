# Given an array of roses. roses[i] means rose i will bloom on day roses[i].
# Also given an int k, which is the minimum number of adjacent bloom rose
# required for a bouquet, and an int n, which is the number of bouquets we need.
# Return the earliest day that we can get n bouquets of roses.

# e.g.
# input: [1, 2, 4, 9, 3, 4, 1], k = 2, n = 2
# output: 4

#input range:
# 1 <= k < 
# 1 <= n <

# 1.
# Step:
# 2 for loops:
# 1. min(roses) -> max(roses)
# 2. go through roses and count number of continues roses bloomed

# Time: O(N ^ 2) in the worst case
# Space: O(1) ignoring memo used by input data

# Note: the outer loop can be improved with B-search so that time complexity can be reduced to O(N * log(N))

def minDaysBloom(roses, k, n):
    for i in range(min(roses), max(roses)):
        c = 0
        numB = 0
        if roses[0] <= i:
            c += 1
        for j in range(len(roses)):
            if roses[j - 1] <= i and roses[j] <= i:
                c += 1
                if c == k:
                    numB += 1
                    c == 0
            else:
                c = 0
    
        if numB >= n:
            return i

    return -1

print(minDaysBloom([1, 2, 4, 9, 3, 4, 1], k = 2, n = 2))

# 2.
# Step:
# 1. sort array of [(bloom day, id)]
# 2. scan through sorted array, for each element, merge it into the correct group and inc group sizes (a dict is used to store groups where value is (1st rose id, last rose id, group size), and key is the 1st rose boomed in the group or the last in the group, as every time a new rose is boomed and merged into the group, only the head or tail of the group is checked)
# 3. when group number reaches n, return

# Time: O(N * log(N))
# Space: O(N)

def minDaysBloom2(roses, k, n):
    l = len(roses)
    numB = 0
    groups = [None] * l # { head id or tail id: (head, tail, size) }
    sortedArr = sorted([(roses[i], i) for i in range(l)])
    for d, i in sortedArr:
        # Look for adjacent boomed roses
        if i - 1 >= 0 and i + 1 < l and groups[i - 1] != None and groups[i + 1] != None:
            # Connect left and right groups
            leftG = groups[i - 1]
            rightG = groups[i + 1]
            preNumB = int(leftG[2] / k) + int(rightG[2] / k)
            newSize = leftG[2] + 1 + rightG[2]
            groups[leftG[0]] = (leftG[0], rightG[1], newSize)
            groups[rightG[1]] = (leftG[0], rightG[1], newSize)
            numB += int(newSize / k) - preNumB
        else:
            if i - 1 >= 0 and groups[i - 1] != None:
                # Merge into left group
                head, tail, s = groups[i - 1]
                groups[head] = (head, i, s + 1)
                groups[i] = (head, i, s + 1)
            elif i + 1 < l and groups[i + 1] != None:
                # Merge into right group
                head, tail, s = groups[i + 1]
                groups[i] = (i, tail, s + 1)
                groups[tail] = (i, tail, s + 1)
            else:
                groups[i] = (i, i, 1)

            if groups[i][2] % k == 0:
                numB += 1

        if numB >= n:
            return d

    return -1

print(minDaysBloom2([1, 2, 4, 9, 3, 4, 1], k = 2, n = 2))
