#
# Given an array of (source - log message) pairs, and a max_size, truncate
# the array in a way so that the number of log messages of each source is
# limited by a max number and the total number of pairs after truncate does
# not exceed the given max_size.
#
# input range:
# 0 <= len(pairs) <=
#
# Idea: binary search to try out
#
# Time: O(N * log(min(N, M))) where:
#       N is size of given array
#       M is the given max_size


def truncateLogs(pairs, max_size):
    if len(pairs) <= max_size:
        return pairs

    # Count the number of msgs for each source
    count = {}
    for p in pairs:
        s, _ = p
        count[s] = count.get(s, 0) + 1

    # Init lower bound and upper bound of limit of number of log msgs for
    # each source
    l, u = 1, min(max_size, max(count.values()))
    # Time: O(log(min(N, M)))
    while l < u:
        m = int((l + u + 1) / 2)
        truncatedSize = getTruncatedSize(count, m)
        if truncatedSize == max_size:
            return truncate(pairs, m)
        elif truncatedSize > max_size:
            u = m - 1
        else:
            l = m

    return truncate(pairs, l)


def getTruncatedSize(count, limit):
    # Time: O(N) in the worst case
    return sum([min(limit, v) for v in count.values()])


def truncate(pairs, limit):
    # Time: O(N) in the worst case
    res = []
    count = {}
    for p in pairs:
        s, m = p
        count[s] = count.get(s, 0) + 1
        if count[s] <= limit:
            res.append(p)

    return res


print(
    truncateLogs([['s1', 'msg'], ['s1', 'msg'], ['s2', 'msg'], ['s2', 'msg'],
                  ['s3', 'msg'], ['s3', 'msg']], 5))
