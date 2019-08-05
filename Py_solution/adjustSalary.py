# From: https://leetcode.com/discuss/interview-question/351341/google-onsite-squares-of-a-sorted-array
#
# Give an array of salaries. The total salary has a budget. At the beginning,
# the total salary of employees is larger than the budget. It is required to
# find the number k, and reduce all the salaries larger than k to k, such
# that the total salary is exactly equal to the budget.
#
# E.g.
# Input: salaries = [100, 300, 200, 400], budget = 800
# Output: 250
# Explanation: k should be 250, so the total salary after the reduction 100 + 250 + 200 + 250 is exactly equal to 800.

# Input range:
# < budget <
# < salary <
# < len(salaries) <

# Idea:
# Binary search:
#   min: budget / n
#   max: max(salaries)

# Time: O(N * log(M - A)) where:
#   M is max salary in the given array
#   A is average salary of the given array
#
# Space: O(1) if do the reduction in place modifying the input,
#        ignoring the space consumption of the input

# Assume k, salaries and budget are all integers

# Similar to below problems (see notes):
#   Capacity to ship packages within D days
#   Split Array Largest Sum
#   Min Days to Bloom


def adjustSalary(salaries, budget):
    n = len(salaries)
    minS = int(budget / n)
    maxS = max(salaries)
    # Bsearch for value of k so that total salaries becomes
    # less or equal to budget
    l, u = minS, maxS
    while l < u:
        m = int((l + u) / 2)
        total = sum([min(m, s) for s in salaries])
        if total == budget:
            return m
        elif total > budget:
            u = m
        else:
            l = m + 1

    return l


print(adjustSalary([100, 300, 200, 400], 800))
