# Given 2 strings s and t, determine if you can convert s into t. The rules are:
#   You can change 1 letter at a time.
#   Once you changed a letter you have to change all occurrences of that letter.
# Both s and t contain lowercase letters only.

# Input range:
# <= len <=

# Example:
# "aabbccdd" -> "aaxxccdd":
# True: by converting "b" -> "x"

# Idea:
# 1. Find out all dependencies of replacement.
# 1. Replacement such as a -> b and a -> c cannot both exist.
# 2. Whenever a replacement dependency cycle appears e.g. a -> b and b -> a,
#    use a letter does not occur in string s as a placeholder so that all of them
#    can be replaced successfully

# Steps:
# 1. Scan through string s to find all replacement dependencies used as a graph.
# 2. Count number of placeholders can be used for replacing dependency cycles.
# 3. Build replacing dependency graph, whenever a cycle is detected,
#    deduce the counter to use a placeholder, return False if all placeholders are used up

# Time: O(N)
# Space: O(N)


def convert(s, t):
    l = len(s)
    if l == 0:
        return True

    # Validate condition 1:
    rules = {}
    for i in range(l):
        c1, c2 = s[i], t[i]
        if c1 not in rules:
            rules[c1] = c2
        elif rules[c1] != c2:
            # Condition 1 violated
            return False

    # Build up rules with Time: O(N)
    rules = {}
    for i in range(l):
        c1, c2 = s[i], t[i]
        if c1 != c2:
            rules[c1] = c2

    exist = set(s)
    numPlaceholders = 26 - len(set(t))

    # Detect cycles, by visiting each node once, Time: O(N)
    numCycles = 0
    visitedGlobal = set()
    for c in exist:
        if c in visitedGlobal:
            continue

        # DFS
        stack = [c]
        visited = set()
        while stack:
            curr = stack.pop()
            if curr in visited:
                numCycles += 1
                break

            if curr in visitedGlobal:
                break

            visited.add(curr)
            visitedGlobal.add(curr)
            if curr in rules:
                stack.append(rules[curr])

    return numCycles <= numPlaceholders


print(convert("aabbccdd", "aaxxccxx"))
print(convert("abca", "dced"))
print(convert("ab", "ba"))
print(convert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"))
print(convert("aa", "cd"))
print(convert("abcdefghijklmnopqrstuvwxyz", "bbcdefghijklmnopqrstuvwxyz"))
