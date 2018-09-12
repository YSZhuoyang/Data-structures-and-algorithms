"""This version implements quicksort with a faster partition subroutine which requires less swaps."""


def readIntegers(fileName):
    array = []

    with open(fileName) as f:
        for line in f:
            array.append(int(line))
        f.close()

    return array


def swap(i, j, array):
    temp = array[i]
    array[i] = array[j]
    array[j] = temp


def midOfThree(first, last, mid, array):
    f = array[first]
    l = array[last]
    m = array[mid]

    if (f >= l):
        if (l >= m):
            return last
        elif f >= m:
            return mid
        else:
            return first
    else:
        if f >= m:
            return first
        elif l >= m:
            return mid
        else:
            return last


def quicksort(start, end, array):
    if (start >= end):
        return 0

    if (end - start == 1):
        if (array[start] > array[end]):
            swap(start, end, array)
        return 1

    # Choose the first element as pivot
    # pivotPtr = start
    # pivot = array[pivotPtr]

    # Choose the last element as pivot, move it to the left
    # swap(start, end, array)
    # pivotPtr = start
    # pivot = array[pivotPtr]

    # Choose the median-of-three as pivot
    mid = int((start + end) / 2)
    pivotPtr = midOfThree(start, end, mid, array)
    pivot = array[pivotPtr]
    swap(start, pivotPtr, array)

    leftPtr = start + 1
    rightPtr = end
    while (leftPtr <= rightPtr):
        while (leftPtr <= end and array[leftPtr] <= pivot):
            leftPtr += 1

        while (rightPtr >= start + 1 and array[rightPtr] > pivot):
            rightPtr -= 1

        if (leftPtr < rightPtr):
            swap(leftPtr, rightPtr, array)
            leftPtr += 1
            rightPtr -= 1

    # Swap the last ele of the left partition with pivot
    swap(start, rightPtr, array)

    # Partition
    numComparesLeft = quicksort(start, rightPtr - 1, array)
    numComparesRight = quicksort(rightPtr + 1, end, array)

    return numComparesLeft + numComparesRight + end - start


array = readIntegers('./QuickSort')
numComparison = quicksort(0, len(array) - 1, array)
print(array)
print(numComparison)
