
def readIntegers(fileName):
    array = []

    with open(fileName) as f:
        for line in f:
            array.append(int(line))
        f.close()

    return array


def countInversions(array):
    length = len(array)
    if (length <= 1):
        return array, 0

    lenLeft = int(length / 2)
    lenRight = length - lenLeft
    leftArr, rightArr = array[:lenLeft], array[lenLeft:]

    # Count inversions in left half
    sortedLeft, numInvLeft = countInversions(leftArr)
    # Count inversions in right half
    sortedRight, numInvRight = countInversions(rightArr)

    # Count spit inversions one of which in left half and another in right half
    leftId = 0
    rightId = 0
    numInvSplit = 0
    sortedArray = []
    while (leftId < lenLeft and rightId < lenRight):
        if (sortedLeft[leftId] > sortedRight[rightId]):
            numInvSplit += lenLeft - leftId
            sortedArray.append(sortedRight[rightId])
            rightId += 1
        else:
            sortedArray.append(sortedLeft[leftId])
            leftId += 1

    if (leftId < lenLeft):
        sortedArray += sortedLeft[leftId:]
    else:
        sortedArray += sortedRight[rightId:]

    return sortedArray, numInvLeft + numInvRight + numInvSplit


# Read integers
array = readIntegers('./IntegerArray')
_, numInv = countInversions(array)
print(numInv)
