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
# 1. Scan s from left to right, replace letters if unmatch is found and put
#    replacement rules in 1 hashmap / array
# 2. Count number of placeholders used for opposite replacing e.g. a -> b and then b -> a.
# 3. For each iteration during scanning:
#    1. Check if the letter can be replaced with existing replacement rule
#       if not then check if an opposite rule exists, if true then use a placeholder
#       which can be any letter other than the letter appeared in s.
#    2. If all placeholders are used up, or if another rule with
#       the same source letter exists, then return False

# Time: O(N)
# Space: O(N)


def convert(s, t):
    if len(s) != len(t):
        return False

    # Replacement rules where key is char replaced and value is its replacement
    rules = [None] * 26
    numUniChars = set(s)
    numPlaceholders = 26 - len(numUniChars)

    # Scan s
    for i in range(len(s)):
        cs, ct = s[i], t[i]
        if cs != ct:
            if rules[ord(cs) - ord('a')] == ct:
                # Replace cs with ct if such a rule exists
                continue

            if rules[ord(cs) - ord('a')] and rules[ord(cs) - ord('a')] != ct:
                # Another replacement rule with target isn't ct already exist
                return False

            if rules[ord(ct) - ord('a')]:
                if rules[ord(ct) - ord('a')] == cs:
                    # if b -> a replacement already exist when we want to replace a -> b,
                    # then use a placeholder which can be any letter other than those appeared
                    # in s
                    numPlaceholders -= 1
                    if numPlaceholders < 0:
                        return False
                else:
                    return False

            rules[ord(cs) - ord('a')] = ct

    return True


print(convert("aabbccdd", "aaxxccxx"))
print(convert("abca", "dced"))
print(convert("ab", "ba"))
print(convert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"))
print(convert("aa", "cd"))
print(convert("abcdefghijklmnopqrstuvwxyz", "bbcdefghijklmnopqrstuvwxyz"))
