

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
    # Init memo with [numItems][cap + 1] 2 dimentional array
    memo = [[-1 for x in range(cap + 1)] for y in range(numItems)]

    return divide(items, 0, cap, memo)


def divide(items, itemId, cap, memo):
    if itemId >= len(items):
        return 0

    if memo[itemId][cap] != -1:
        return memo[itemId][cap]

    item = items[itemId]
    if item.w > cap:
        # Have to exclude above item.
        opt = divide(items, itemId + 1, cap, memo)
        memo[itemId][cap] = opt
        return opt

    # Exclude above item.
    choiceA = divide(items, itemId + 1, cap, memo)
    # Pick above item.
    choiceB = item.v + divide(items, itemId + 1, cap - item.w, memo)
    opt = max(choiceA, choiceB)
    memo[itemId][cap] = opt

    return opt


items, cap = readInputData("./knapsack1")
opt = knapsack(items, cap)
print(str(opt))
