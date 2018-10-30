# A Dijkstra's shortest path algorithm using heap.
# Running time: O(Elog(V))

from sys import maxsize
from copy import deepcopy, copy


class Vertex:
    def __init__(self, id, value=maxsize):
        self.id = id
        # Shortest path distance computed with Dijkstra's algorithm
        self.value = value
        self.adjVertices = []

    def __str__(self):
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

    def get(self, vertexId):
        if vertexId not in self.posDict:
            return None

        pos = self.posDict[vertexId]
        return copy(self.buffer[pos])

    def insert(self, vertex):
        clone = copy(vertex)
        preSize = len(self.buffer)
        self.buffer.append(clone)
        self.posDict[clone.id] = preSize
        # Perform a heapify by bubbling up inserted key
        self.__heapifyUpIter(preSize)

    def update(self, vertex):
        if vertex.id not in self.posDict:
            raise Exception("Vertex not found")

        pos = self.posDict[vertex.id]
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
        self.posDict[self.buffer[0].id] = 0
        self.posDict.pop(min.id)

        # Perform a heapify by bubbling down moved element
        self.__heapifyDownIter(0)

        return min

    #-----------------------------------------------------#
    #------------------ Util functions -------------------#
    #-----------------------------------------------------#

    def __swap(self, posX, posY):
        # Update position mapping
        self.posDict[self.buffer[posX].id] = posY
        self.posDict[self.buffer[posY].id] = posX
        # Update vertex buffer
        temp = self.buffer[posX]
        self.buffer[posX] = self.buffer[posY]
        self.buffer[posY] = temp

    def __shift(self, vertex, newPos):
        self.buffer[newPos] = vertex
        self.posDict[vertex.id] = newPos

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
        root = self.buffer[rootId]
        parentId = rootId
        while True:
            leftId, rightId = self.__getChildrenId(parentId)
            if leftId == None:
                self.__shift(root, parentId)
                break

            smallerChildId = leftId
            if rightId != None:
                right = self.buffer[rightId]
                left = self.buffer[leftId]
                if left.value > right.value:
                    smallerChildId = rightId

            smallerChild = self.buffer[smallerChildId]
            if smallerChild.value >= root.value:
                self.__shift(root, parentId)
                break

            # Shift smaller child up
            self.__shift(smallerChild, parentId)
            parentId = smallerChildId

    def __heapifyUpIter(self, leafId):
        leaf = self.buffer[leafId]
        childId = leafId
        while True:
            parentId = self.__getParentId(childId)
            if parentId == None:
                self.__shift(leaf, childId)
                break

            parent = self.buffer[parentId]
            if parent.value <= leaf.value:
                self.__shift(leaf, childId)
                break

            # Shift parent down
            self.__shift(parent, childId)
            childId = parentId

    #-----------------------------------------------------#
    #----------------- Recursive heapify -----------------#
    #-----------------------------------------------------#

    def __heapifyDown(self, parentId):
        parent = self.buffer[parentId]
        leftId, rightId = self.__getChildrenId(parentId)
        if leftId == None:
            return

        smallerChildId = leftId
        if rightId != None:
            right = self.buffer[rightId]
            left = self.buffer[leftId]
            if right.value < left.value:
                smallerChildId = rightId

        smallerChild = self.buffer[smallerChildId]
        if smallerChild.value < parent.value:
            # Swap smaller child with parent
            self.__swap(smallerChildId, parentId)
            self.__heapifyDown(smallerChildId)

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
        print(str(min))
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
    numVertices = len(graphData)

    # Base case, initialize visited set with source vertex
    # and update Dij scores for its adjacent vertices
    source = graphData[sourceVertId]
    source.value = 0
    visitedSet = {source.id: 0}
    lastAddedVertex = source

    while len(visitedSet) < numVertices:
        # Update Dij scores for adjacent vertices of the newly added vertex
        for adjEdge in lastAddedVertex.adjVertices:
            adjVertId = adjEdge[0]
            if adjVertId in visitedSet:
                continue

            edgeLen = adjEdge[1]
            newScore = lastAddedVertex.value + edgeLen
            graphData[adjVertId].value = newScore
            existAdj = heap.get(adjVertId)
            if existAdj == None:
                heap.insert(graphData[adjVertId])
            elif existAdj.value > newScore:
                heap.update(graphData[adjVertId])

        # Extract the vertex with the minimum Dij score and add it to visited set
        lastAddedVertex = heap.extractMin()
        visitedSet[lastAddedVertex.id] = lastAddedVertex.value

    return visitedSet


graphData = readGraphData("./dijkstraData", 200)
visitedSet = dijkstra(graphData, 0)
# print(visitedSet)
assignAns = ","
verticesToCheck = [str(visitedSet[i - 1])
                   for i in [7, 37, 59, 82, 99, 115, 133, 165, 188, 197]]
assignAns = assignAns.join(verticesToCheck)
print(assignAns)
