# Given an array of n distinct elements, find the minimum number
# of swaps required to sort the array.


def minSwap(arr):
    n = len(arr)
    ids = {}
    for i, e in enumerate(arr):
        # Store value - unsorted index in a dict
        ids[e] = i

    count = 0
    sortedArr = sorted(arr)
    # Scan through sorted array
    for sortedId, e in enumerate(sortedArr):
        if sortedId == ids[e]:
            continue

        swap(arr, sortedId, ids[e], ids)
        count += 1

    return count


def swap(arr, i1, i2, ids):
    temp = arr[i1]
    arr[i1] = arr[i2]
    arr[i2] = temp

    temp = ids[arr[i1]]
    ids[arr[i1]] = ids[arr[i2]]
    ids[arr[i2]] = temp


print(minSwap([5, 4, 2, 7, 1, 3]))
print(minSwap([5, 1, 3, 2]))
print(minSwap([1, 3, 2]))