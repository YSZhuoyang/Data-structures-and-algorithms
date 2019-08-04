# From: https://leetcode.com/discuss/interview-question/350312/google-onsite-interesting-string
#
# Given a string s consisting of digits 0-9 and lowercase English letters a-z.
#
# A string is interesting if you can split it into several substrings,
# such that each substring starts with a number and this number represents
# the number of characters after it. Retrun true if string s is intersting, otherwise false.
#
# Similar to Leetcode question: https://leetcode.com/problems/word-break
#
# e.g.:
#
# Input: "4g12y6hunter"
# Output: true
# Explanation: We can split it into "4g12y" and "6hunter".


def interestingStr(s):
    l = len(s)
    if l == 0:
        return True

    if not s[0].isdigit():
        return False

    numAfter = int(s[0])
    sub = s[:numAfter + 1]
    if len(sub) < numAfter + 1:
        return False

    return True and interestingStr(s[numAfter + 1:])


print(interestingStr("4g12y6hunter"))
print(interestingStr("124gray6hunter"))
print(interestingStr("31ba2a"))
