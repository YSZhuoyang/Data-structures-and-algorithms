# A naive solution (using a hashmap) for 2 sum problem:
# find number of values between -10000 and 10000
# that are the sum of distinct combinations of the
# given input array.


def readIntegers(fileName):
    array = []

    with open(fileName) as f:
        for line in f:
            array.append(int(line))
        f.close()

    return array


def twoSum(array):
    numAvail = 0
    memo = {}
    for x in array:
        memo[x] = True

    for t in range(-10000, 10001):
        for y in memo:
            x = t - y
            if x in memo and x != y:
                numAvail += 1
                break
        print(str(t))

    return numAvail


array = readIntegers("./2sum")
numAvail = twoSum(array)
print(str(numAvail))
