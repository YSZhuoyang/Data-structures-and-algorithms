# From: https://leetcode.com/discuss/interview-question/349416/goolge-onsite-guess-pin-code
#
# Given 2 strings pin and guess. Write a function to provide a hint that indicates how digits in guess match the pin. Use:
# * - to indicate a number in the correct possition.
# o - to indicate that a number is present in the pin code but in a different possition.
# _ - to indicate that there's no such number in the pin code.
#
# E.g.:
#
# Input: pin = "1432", guess = "1246"
# Output: "*oo_"
# Explanation:
# 1 is in the correct position so `*`
# 2 and 4 are present in the pin code but in different positions thus `oo`
# There's no 6 in the pin code thus `_`
#
# Similar to: https://leetcode.com/problems/bulls-and-cows
#
# Input range:
# 0 <= d <= 9
#  <= l <=
#
# Idea:
#   1. Init res with '_'s, scan through guess and pin:
#       1. find all correct numbers in correct possitions and put '*'.
#       2. store frequencies excluding the perfect matches
#          of all occurred digits in a dict.
#   2. Scan through the guess code, for all digits that
#      are not perfect matches, count down the frequency and put 'o'
#
# Time: O(N)
#
# Space: O(N)


def guessPin(guess, pin):
    # Assume lp > 0
    lp, lg = len(pin), len(guess)

    # Store frequencies of occured digits in pin not perfect match
    freq = [0] * 10
    res = ['_'] * lg
    for i in range(lp):
        if i >= lg:
            break

        cp, cg = int(pin[i]), int(guess[i])
        if cp == cg:
            # Perfect match
            res[i] = '*'
        else:
            freq[cp] += 1

    # Find imperfect matches
    for i in range(lg):
        cg = int(guess[i])
        if freq[cg] > 0 and res[i] != '*':
            freq[cg] -= 1
            res[i] = 'o'

    return ''.join(res)


print(guessPin('1246', '1432'))
print(guessPin('1234', '5678'))
print(guessPin('1224', '5242'))
print(guessPin('1234', '1224'))
print(guessPin('2124', '1224'))
