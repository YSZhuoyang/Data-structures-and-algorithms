# A Dijkstra's shortest path algorithm using heap.
# Running time: O(Elog(V))

from sys import maxsize
from copy import deepcopy


class Vertex:
    def __init__(self, id, value=maxsize):
        self.id = id
        # Shortest path length computed with Dijkstra's algorithm
        self.value = value
        self.adjVertices = []

    def __str__(self):
        return str(self.id)

    def fullStr(self):
        return str(self.id) + ": " + str(self.value)


class Heap:
    def __init__(self):
        # Store Dijkstra's greedy score of each vertex
        self.buffer = []
        # Store vertex as key and its position in the buffer array as value,
        # assuming vertex ids are unique
        self.posDict = {}

    def getSize(self):
        return len(self.buffer)

    def get(self, vertex):
        if str(vertex) not in self.posDict:
            return None

        pos = self.posDict[str(vertex)]
        return deepcopy(self.buffer[pos])

    def insert(self, vertex):
        clone = deepcopy(vertex)
        preSize = len(self.buffer)
        self.buffer.append(clone)
        self.posDict[str(clone)] = preSize
        # Perform a heapify by bubbling up inserted key
        self.__heapifyUpIter(preSize)

    def update(self, vertex):
        vertexStr = str(vertex)
        if vertexStr not in self.posDict:
            raise Exception("Vertex not found")

        pos = self.posDict[vertexStr]
        prevValue = self.buffer[pos].value
        if prevValue == vertex.value:
            return

        self.buffer[pos].value = vertex.value
        if prevValue < vertex.value:
            self.__heapifyDownIter(pos)
        else:
            self.__heapifyUpIter(pos)

    def extractMin(self):
        preSize = len(self.buffer)
        if preSize == 0:
            return None

        if preSize == 1:
            self.posDict = {}
            return self.buffer.pop(0)

        min = self.buffer[0]
        # Shift the bottom right most element to the top
        self.buffer[0] = self.buffer.pop(preSize - 1)
        self.posDict[str(self.buffer[0])] = 0
        self.posDict.pop(str(min))

        # Perform a heapify by bubbling down moved element
        self.__heapifyDownIter(0)

        return min

    #-----------------------------------------------------#
    #------------------ Util functions -------------------#
    #-----------------------------------------------------#

    def __swap(self, posX, posY):
        # Update position mapping
        self.posDict[str(self.buffer[posX])] = posY
        self.posDict[str(self.buffer[posY])] = posX
        # Update vertex buffer
        temp = self.buffer[posX]
        self.buffer[posX] = self.buffer[posY]
        self.buffer[posY] = temp

    def __getChildrenId(self, parentId):
        size = len(self.buffer)
        left = parentId * 2 + 1
        right = left + 1

        if left >= size:
            return None, None

        if right >= size:
            return left, None

        return left, right

    def __getParentId(self, child):
        if child < 0:
            raise Exception("Invalid child id")

        if child == 0:
            return None

        parentId = int((child - 1) / 2)

        return parentId

    #-----------------------------------------------------#
    #----------------- Iterative heapify -----------------#
    #-----------------------------------------------------#

    def __heapifyDownIter(self, rootId):
        parentId = rootId
        leftId, rightId = self.__getChildrenId(parentId)
        while leftId != None:
            smallerId = leftId
            if rightId != None:
                right = self.buffer[rightId]
                left = self.buffer[leftId]
                if left.value > right.value:
                    smallerId = rightId

            smallerChild = self.buffer[smallerId]
            parent = self.buffer[parentId]
            if smallerChild.value >= parent.value:
                break

            # Swap smaller child with parent
            self.__swap(smallerId, parentId)
            parentId = smallerId
            leftId, rightId = self.__getChildrenId(parentId)

    def __heapifyUpIter(self, leafId):
        childId = leafId
        parentId = self.__getParentId(childId)
        while parentId != None:
            parent = self.buffer[parentId]
            child = self.buffer[childId]
            if parent.value <= child.value:
                break

            self.__swap(parentId, childId)
            childId = parentId
            parentId = self.__getParentId(childId)

    #-----------------------------------------------------#
    #----------------- Recursive heapify -----------------#
    #-----------------------------------------------------#

    def __heapifyDown(self, parentId):
        parent = self.buffer[parentId]
        leftId, rightId = self.__getChildrenId(parentId)
        if leftId == None:
            return

        smallerId = leftId
        if rightId != None:
            right = self.buffer[rightId]
            left = self.buffer[leftId]
            if right.value < left.value:
                smallerId = rightId

        smallerChild = self.buffer[smallerId]
        if smallerChild.value < parent.value:
            # Swap smaller child with parent
            self.__swap(smallerId, parentId)
            self.__heapifyDown(smallerId)

    def __heapifyUp(self, childId):
        parentId = self.__getParentId(childId)
        if parentId == None:
            return

        child = self.buffer[childId]
        parent = self.buffer[parentId]
        if parent.value > child.value:
            self.__swap(parentId, childId)
            self.__heapifyUp(parentId)


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
        print(min.fullStr())
        min = heap.extractMin()


# Raw vertex data (adjacency list) uses 1 indexed scheme
def readGraphData(fileName, numVertices):
    graphData = [Vertex(i) for i in range(numVertices)]
    with open(fileName) as f:
        for line in f:
            rowData = line.split()
            vertexId = int(rowData[0]) - 1
            for adj in rowData[1:]:
                rawAdjVert = adj.split(",")
                adjVertId = int(rawAdjVert[0]) - 1
                # Ignore self connected edges
                if adjVertId == vertexId:
                    continue

                edgeLen = int(rawAdjVert[1])
                graphData[vertexId].adjVertices.append((adjVertId, edgeLen))

        f.close()

    return graphData


def dijkstra(oriGraphData, sourceVertId):
    heap = Heap()
    graphData = deepcopy(oriGraphData)
    source = graphData[sourceVertId]
    source.value = 0
    visitedSet = {source.id: 0}
    numVertices = len(graphData)
    while len(visitedSet) < numVertices:
        for visited in visitedSet:
            for adjEdge in graphData[visited].adjVertices:
                adjVertId = adjEdge[0]
                if adjVertId in visitedSet:
                    continue

                edgeLen = adjEdge[1]
                newScore = visitedSet[visited] + edgeLen
                graphData[adjVertId].value = newScore
                existAdj = heap.get(Vertex(adjVertId))
                if existAdj == None:
                    heap.insert(graphData[adjVertId])
                elif existAdj.value > newScore:
                    heap.update(graphData[adjVertId])

        min = heap.extractMin()
        visitedSet[min.id] = min.value

    return visitedSet


graphData = readGraphData("./dijkstraData", 200)
visitedSet = dijkstra(graphData, 0)
# print(visitedSet)
assignAns = ","
verticesToCheck = [str(visitedSet[i - 1])
                   for i in [7, 37, 59, 82, 99, 115, 133, 165, 188, 197]]
assignAns = assignAns.join(verticesToCheck)
print(assignAns)