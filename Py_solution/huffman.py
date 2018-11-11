

class TreeNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.depth = 0


def readAlphabetFrequencies(fileName):
    freq = None
    with open(fileName) as f:
        # Read number of symbols
        numSym = int(f.readline())
        # Init vertex buffer
        freq = [None] * numSym

        # Read undirected edge length and weight.
        # Note that i-j & j-i are equivalent and only
        # one of them will appear and be stored
        i = 0
        for line in f:
            frequency = int(line)
            freq[i] = frequency
            i += 1
        f.close()

    return freq


def extractMin(sortedQueue, mergedQueue):
    sortedMin = None
    mergedMin = None
    if sortedQueue != None and len(sortedQueue) > 0:
        sortedMin = sortedQueue[0]
    if mergedQueue != None and len(mergedQueue) > 0:
        mergedMin = mergedQueue[0]
    
    if sortedMin == None and mergedMin == None:
        return None
    elif sortedMin == None:
        return mergedQueue.pop(0)
    elif mergedMin == None:
        return sortedQueue.pop(0)

    if sortedMin.value < mergedMin.value:
        return sortedQueue.pop(0)

    return mergedQueue.pop(0)


def testExtractMin():
    sorted = [TreeNode(v) for v in [1,4,5,6,7,100,102]]
    merged = [TreeNode(v) for v in [3,5,6,8,12,13,24,43,45,466,5432,5555]]
    while len(sorted) > 0 or len(merged) > 0:
        min = extractMin(sorted, merged)
        print(str(min.value))


# testExtractMin()


def bfs(root):
    minLen = None
    maxLen = None
    queue = [root]
    while len(queue) > 0:
        node = queue.pop(0)
        if node.left == None and node.right == None:
            if minLen == None:
                minLen = node.depth

            maxLen = node.depth
            continue

        if node.left != None:
            node.left.depth = node.depth + 1
            queue.append(node.left)
        if node.right != None:
            node.right.depth = node.depth + 1
            queue.append(node.right)
    
    return minLen, maxLen


def huffmanCoding(freq):
    sortedFreq = sorted(freq)
    sortedNodes = [TreeNode(v) for v in sortedFreq]
    mergedNodes = []
    while len(sortedNodes) > 0 or len(mergedNodes) > 0:
        x = extractMin(sortedNodes, mergedNodes)
        y = extractMin(sortedNodes, mergedNodes)
        if y == None:
            return x

        mergedNode = TreeNode(x.value + y.value)
        mergedNode.left = x
        mergedNode.right = y
        mergedNodes.append(mergedNode)


freq = readAlphabetFrequencies("./huffman")
huffmanTree = huffmanCoding(freq)
minLen, maxLen = bfs(huffmanTree)
print(str(minLen))
print(str(maxLen))
