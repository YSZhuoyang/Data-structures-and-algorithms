
import sys


class Item:
    def __init__(self, v, w):
        self.v = v
        self.w = w
    
    def __str__(self):
        return "V: " + str(self.v) + ", W: " + str(self.w)


def readInputData(fileName):
    items = None
    with open(fileName) as f:
        # Read total capacity of weight and number of items available
        firstRow = f.readline().split()
        cap = int(firstRow[0])
        numItems = int(firstRow[1])
        # Init vertex buffer
        items = [None] * numItems

        # Read undirected edge length and weight.
        # Note that i-j & j-i are equivalent and only
        # one of them will appear and be stored
        i = 0
        for line in f:
            rawData = line.split()
            v = int(rawData[0])
            w = int(rawData[1])
            items[i] = Item(v, w)
            i += 1
        f.close()

    return items, cap


def knapsack(items, cap):
    numItems = len(items)
    # Given that the capacity is too big to initialize a 2 dimensional array,
    # we initialize the memo with a [numItems] array of dictionaries, where
    # each entry of the dict is the result of sub-problem we solved.
    memo = [{} for itemId in range(numItems)]

    return divide(items, 0, cap, memo)


def divide(items, itemId, cap, memo):
    if itemId >= len(items):
        return 0

    memoOfItem = memo[itemId]
    if cap in memoOfItem:
        return memoOfItem[cap]

    item = items[itemId]
    if item.w > cap:
        # Have to exclude above item.
        opt = divide(items, itemId + 1, cap, memo)
        memoOfItem[cap] = opt
        return opt

    # Exclude above item.
    choiceA = divide(items, itemId + 1, cap, memo)
    # Pick above item.
    choiceB = item.v + divide(items, itemId + 1, cap - item.w, memo)
    opt = max(choiceA, choiceB)
    memoOfItem[cap] = opt

    return opt


sys.setrecursionlimit(3000)
items, cap = readInputData("./knapsack_big")
opt = knapsack(items, cap)
print(str(opt))
