

from sys import maxsize
from copy import deepcopy, copy


class Vertex:
    def __init__(self, id, value=maxsize):
        self.id = id
        # Store the smallest edge length out of all edges
        # from visited set to this vertex.
        self.value = value
        self.adjVertices = []

    def __str__(self):
        return str(self.id) + ": " + str(self.value)


class Heap:
    def __init__(self):
        self.buffer = []
        # Mapping between vertex id and its index in the buffer array
        self.posDict = {}

    def getSize(self):
        return len(self.buffer)

    def get(self, nodeId):
        if nodeId not in self.posDict:
            return None

        pos = self.posDict[nodeId]
        return copy(self.buffer[pos])

    def extractMin(self):
        currSize = len(self.buffer)
        if currSize == 0:
            return None

        if currSize == 1:
            self.posDict = {}
            return self.buffer.pop(0)

        min = self.buffer[0]
        self.posDict.pop(min.id)
        self.buffer[0] = self.buffer.pop(currSize - 1)
        newTop = self.buffer[0]
        self.posDict[newTop.id] = 0
        self.__heapifyDown(newTop)

        return min

    def insert(self, node):
        if node.id in self.posDict:
            raise Exception("Element being inserted already exists")

        clone = copy(node)
        self.posDict[clone.id] = len(self.buffer)
        self.buffer.append(clone)
        self.__heapifyUp(clone)

    def update(self, node):
        if node.id not in self.posDict:
            raise Exception("Element being updated does not exist")

        clone = copy(node)
        pos = self.posDict[clone.id]
        exist = self.buffer[pos]
        self.buffer[pos] = clone
        if clone.value > exist.value:
            self.__heapifyDown(clone)
        elif clone.value < exist.value:
            self.__heapifyUp(clone)

    def __getParent(self, childPos):
        if childPos == 0:
            return None

        return self.buffer[int((childPos - 1) / 2)]

    def __getChildren(self, parentPos):
        leftPos = parentPos * 2 + 1
        rightPos = leftPos + 1
        numNodes = len(self.buffer)
        if leftPos >= numNodes:
            return None, None
        elif rightPos >= numNodes:
            return self.buffer[leftPos], None
        else:
            return self.buffer[leftPos], self.buffer[rightPos]

    def __swap(self, xPos, yPos):
        # Update position mapping
        self.posDict[self.buffer[xPos].id] = yPos
        self.posDict[self.buffer[yPos].id] = xPos
        # Swap nodes
        temp = self.buffer[xPos]
        self.buffer[xPos] = self.buffer[yPos]
        self.buffer[yPos] = temp

    def __heapifyUp(self, node):
        childPos = self.posDict[node.id]
        parent = self.__getParent(childPos)
        if parent == None or parent.value <= node.value:
            return

        parentPos = self.posDict[parent.id]
        self.__swap(childPos, parentPos)
        self.__heapifyUp(node)

    def __heapifyDown(self, node):
        parentPos = self.posDict[node.id]
        leftChild, rightChild = self.__getChildren(parentPos)
        if leftChild == None:
            return

        smallerChild = leftChild
        if rightChild != None and rightChild.value < leftChild.value:
            smallerChild = rightChild
        if smallerChild.value >= node.value:
            return

        smallerChildPos = self.posDict[smallerChild.id]
        self.__swap(parentPos, smallerChildPos)
        self.__heapifyDown(node)


def testHeap():
    testBuild = [Vertex(0, 12), Vertex(1, 5), Vertex(2, 3), Vertex(3, 7), Vertex(
        4, 9), Vertex(5, 1), Vertex(6, 4), Vertex(7, 5), Vertex(8, 8)]
    testUpdate = [Vertex(0, 8), Vertex(1, 7), Vertex(2, 20), Vertex(3, 5), Vertex(
        4, 2), Vertex(5, 3), Vertex(6, 6), Vertex(7, 1), Vertex(8, 9)]

    heap = Heap()
    # Build heap
    for vert in testBuild:
        heap.insert(vert)
    # Update elements
    for vert in testUpdate:
        heap.update(vert)
    min = heap.extractMin()
    while min != None:
        print(str(min))
        min = heap.extractMin()


def readGraphData(fileName):
    numVertices = None
    numEdges = None
    graphData = None
    with open(fileName) as f:
        firstRow = f.readline().split()
        numVertices = int(firstRow[0])
        numEdges = int(firstRow[1])
        # Init vertex array
        graphData = [None] * numVertices
        for i in range(numVertices):
            graphData[i] = Vertex(i)
        # Read edge data
        for line in f:
            rowData = line.split()

            if int(rowData[0]) == 0 or int(rowData[1]) == 0:
                print("Starts from 0")

            sourceId = int(rowData[0]) - 1
            targetId = int(rowData[1]) - 1
            edgeLen = int(rowData[2])
            # Its an undirected graph, so make it bidirectional
            graphData[sourceId].adjVertices.append((targetId, edgeLen))
            graphData[targetId].adjVertices.append((sourceId, edgeLen))

        f.close()

    return graphData, numEdges


# Note that Prim's minimum spanning tree algorithm is very
# similar to Dijkstra's algorithm. Simply consider changing the
# Dij's greedy criteria of choosing a vertex that minimizes:
#    minDist(s) + adjEdgeLen
# to Prim's greedy criteria of choosing a vertex that minimizes:
#    adjEdgeLen
#
# Thus the running time upper bound is also the same: O(Elog(V))
# (see the analysis in Dij's solution)
def prim(oriGraphData):
    graphData = deepcopy(oriGraphData)
    heap = Heap()
    totalLen = 0
    # Start from vertex 0
    visited = {0: True}
    lastAdded = graphData[0]

    while len(visited) < len(graphData):
        for adjEdge in lastAdded.adjVertices:
            adjVertId = adjEdge[0]
            edgeLen = adjEdge[1]
            if adjVertId in visited:
                continue

            graphData[adjVertId].value = edgeLen
            exist = heap.get(adjVertId)
            if exist == None:
                heap.insert(graphData[adjVertId])
            elif exist.value > edgeLen:
                heap.update(graphData[adjVertId])

        lastAdded = heap.extractMin()
        visited[lastAdded.id] = True
        totalLen += lastAdded.value

    return totalLen


graphData, _ = readGraphData("./edges")
totalLen = prim(graphData)
print(str(totalLen))
