
class Stack:
    def __init__(self):
        self.elements = []

    def push(self, ele):
        self.elements.append(ele)

    def pop(self):
        if self.empty():
            return None

        return self.elements.pop(len(self.elements) - 1)

    def empty(self):
        return len(self.elements) == 0


# Processed vertex uses 0 indexed scheme
class Vertex:
    def __init__(self, id, adjVertices):
        self.id = id
        self.adjVertices = adjVertices

    def __str__(self):
        vertexStr = str(self.id) + ":"
        for vert in self.adjVertices:
            vertexStr += " " + str(vert)
        return vertexStr


# Raw vertex data uses 1 indexed scheme
def readGraphData(fileName, numVertices):
    graphData = [Vertex(i, []) for i in range(numVertices)]
    revGraphData = [Vertex(i, []) for i in range(numVertices)]
    with open(fileName) as f:
        for line in f:
            edge = line.split()
            start = int(edge[0]) - 1
            end = int(edge[1]) - 1
            # Ignore self connected edges
            if start == end:
                continue

            graphData[start].adjVertices.append(end)
            revGraphData[end].adjVertices.append(start)

        f.close()

    return graphData, revGraphData


def findStronglyConnectedGraph(graphData, revGraphData):
    numVertices = len(graphData)
    visited = {}
    for i in range(numVertices):
        visited[i] = False

    # First pass of DFS to do a topological sort to figure out
    # meta-graph info, which contains post order of each vertex
    # and topological ordering of each SCC.
    subDFSOrderStack = Stack()
    for i in range(numVertices):
        subDFSOrder = dfsI(revGraphData, i, visited)
        if len(subDFSOrder) > 0:
            subDFSOrderStack.push(subDFSOrder)

    # Reconstruct first DFS order of the first pass DFS
    insertionId = 0
    firstDFSOrder = [None] * numVertices
    while not subDFSOrderStack.empty():
        subDFSOrder = subDFSOrderStack.pop()
        newInsertionId = insertionId + len(subDFSOrder)
        firstDFSOrder[insertionId:newInsertionId] = subDFSOrder
        insertionId = newInsertionId

    # Reset visited record
    for i in range(numVertices):
        visited[i] = False

    sccSizes = []
    # Second pass of DFS to find out all SCCs
    for vertId in firstDFSOrder:
        size = dfsII(graphData, vertId, visited)
        if size > 0:
            sccSizes.append(size)

    return sccSizes


def dfsI(revGraphData, initialVertId, visited):
    if visited[initialVertId]:
        return []

    path = []
    stack = Stack()
    stack.push(initialVertId)
    while not stack.empty():
        vertId = stack.pop()
        # Important!!! It is possible traversing a cyclic graph
        # may result in pushing duplicated nodes into the stack/queue.
        if visited[vertId]:
            continue

        path.append(vertId)
        visited[vertId] = True
        vertex = revGraphData[vertId]
        for adjVertId in vertex.adjVertices:
            if not visited[adjVertId]:
                stack.push(adjVertId)

    return path


def dfsII(graphData, initialVertId, visited):
    if visited[initialVertId]:
        return 0

    nodeCounter = 0
    stack = Stack()
    stack.push(initialVertId)
    while not stack.empty():
        vertId = stack.pop()
        # Important!!! It is possible traversing a cyclic graph
        # may result in pushing duplicated nodes into the stack/queue.
        if visited[vertId]:
            continue

        visited[vertId] = True
        vertex = graphData[vertId]
        for adjVertId in vertex.adjVertices:
            if not visited[adjVertId]:
                stack.push(adjVertId)

        nodeCounter += 1

    return nodeCounter


graphData, revGraphData = readGraphData("./SCC", 875714)
sizes = findStronglyConnectedGraph(graphData, revGraphData)
sizes.sort(reverse=True)
print(sizes[:5])
