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
    if len(s) != len(t):
        return False

    # Count number of placeholders can be used for replacement dependency cycles
    numUniChars = set(s)
    numPlaceholders = 26 - len(numUniChars)

    dep = [None] * 26
    # Build replacement dependency graph
    for i in range(len(s)):
        cs, ct = ord(s[i]) - ord('a'), ord(t[i]) - ord('a')
        if cs != ct:
            if dep[cs] != None and dep[cs] != ct:
                # Dependency conflicts
                return False

            dep[cs] = ct

    # Count dependency cycles and deduce placeholder counter
    visited = [False] * 26
    for c in numUniChars:
        source = ord(c) - ord('a')
        path = [False] * 26
        path[source] = True
        while not visited[source] and dep[source] != None:
            visited[source] = True
            dest = dep[source]
            if path[dest]:
                # Cycle detected
                numPlaceholders -= 1
                if numPlaceholders < 0:
                    # All placeholders are used up
                    return False
            else:
                path[dest] = True
            
            source = dest

    return True


print(convert("aabbccdd", "aaxxccxx"))
print(convert("abca", "dced"))
print(convert("ab", "ba"))
print(convert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"))
print(convert("aa", "cd"))
print(convert("abcdefghijklmnopqrstuvwxyz", "bbcdefghijklmnopqrstuvwxyz"))
