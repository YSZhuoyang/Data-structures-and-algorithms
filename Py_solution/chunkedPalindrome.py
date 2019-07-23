# Normal palindrome is defined as a string that reads the same backwards as forwards, for example "abccba".
# Chunked palindrome is defined as a string that you can split into chunks and it will form a palindrome.
# For example, "volvo". You can split it into (vo)(l)(vo). Let A = "vo", B = "l", so the original string is ABA which is a palindrome.

# Given a string str, find the maximum number of chunks we can split that string to get a chuncked palindrome.

# input range: len(str) > 0

# example:
# 'volvo'  3
# 'abc'    1
# 'mabcm'  3

# idea: if first n chars == last n chars of the given str, then do recursive check for the rest chars in between

# Time: O(N ^ 2)

def chunk(s):
    l = len(s)
    if l == 0:
        return 0

    for i in range(1, int(l / 2) + 1): # Time: O(N)
        if s[0:i] == s[-i:]:           # Time: O(N)
            return chunk(s[i:-i]) + 2

    return 1

print(chunk('volvo'))
print(chunk('abc'))
print(chunk('vovo'))
print(chunk('mabcm'))
print(chunk('mmabcmm'))
print(chunk('volvolvo'))