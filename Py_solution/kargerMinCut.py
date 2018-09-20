
from random import randint


# Processed vertex uses 0 indexed scheme
class Vertex:
    def __init__(self, id, adjEdges):
        self.id = id
        self.adjEdges = adjEdges

    def __str__(self):
        adjEdgesStr = ""
        for edge in self.adjEdges:
            adjEdgesStr += " " + str(edge)
        return str(self.id) + ":" + adjEdgesStr


class Edge:
    def __init__(self, start, end):
        self.start = start
        self.end = end

    def __str__(self):
        return str(self.start.id) + "-" + str(self.end.id)


# Raw vertex data uses 1 indexed scheme
def readIntegers(fileName):
    rawGraphData = []
    with open(fileName) as f:
        for line in f:
            array = line.split()
            rawVertData = []

            for vert in array:
                rawVertData.append(int(vert) - 1)
            rawGraphData.append(rawVertData)

        f.close()

    return rawGraphData


# def deepCloneGraph(vertices, edges):
#     verticesCopy = []
#     edgesCopy = []
#     memoV = {}
#     memoE = {}
#     cloneVertex(vertices[0], memoV, memoE, verticesCopy, edgesCopy)
#     return verticesCopy, edgesCopy


# def cloneEdge(edge, memoV, memoE, verticesCopy, edgesCopy):
#     if str(edge) in memoE:
#         return memoE[str(edge)]

#     vertA = cloneVertex(edge.start, memoV, memoE, verticesCopy, edgesCopy)
#     vertB = cloneVertex(edge.end, memoV, memoE, verticesCopy, edgesCopy)
#     edgeCopy = Edge(vertA, vertB)
#     edgesCopy.append(edgeCopy)
#     memoE[str(edge)] = edgeCopy
#     return edgeCopy


# def cloneVertex(vertex, memoV, memoE, verticesCopy, edgesCopy):
#     if str(vertex) in memoV:
#         return memoV[str(vertex)]

#     adjEdges = []
#     for edge in vertex.adjEdges:
#         edge = cloneEdge(edge, memoV, memoE, verticesCopy, edgesCopy)
#         adjEdges.append(edge, memoV, memoE, verticesCopy, edgesCopy)
#     vertexCopy = Vertex(vertex.id, adjEdges)
#     verticesCopy.append(vertexCopy)
#     memoV[str(vertex)] = vertexCopy
#     return vertexCopy


def constructGraph(rawGraphData, vertId, vertices, edges, edgeFilter):
    if vertices[vertId] != None:
        return

    rawVertData = rawGraphData[vertId]
    adjVertIds = rawVertData[1:]
    vertices[vertId] = Vertex(vertId, [])
    adjEdges = vertices[vertId].adjEdges
    for adjVertId in adjVertIds:
        constructGraph(rawGraphData, adjVertId, vertices, edges, edgeFilter)
        oppositeEdgeStr = str(adjVertId) + "-" + str(vertId)
        if oppositeEdgeStr not in edgeFilter:
            adjEdge = Edge(vertices[vertId], vertices[adjVertId])
            edgeFilter[str(adjEdge)] = adjEdge
            adjEdges.append(adjEdge)
            edges.append(adjEdge)
        else:
            adjEdge = edgeFilter[oppositeEdgeStr]
            adjEdges.append(adjEdge)

    return


def randomCut(vertices, edges):
    if vertices == None or edges == None:
        return 0

    for _ in range(len(vertices) - 2):
        # Randomly select an edge and remove it
        randId = randint(0, len(edges) - 1)
        randEdge = edges.pop(randId)
        # Fuse vertices: merge B into A
        vertA = randEdge.start
        vertB = randEdge.end
        # Remove randomly selected edge from both B and A
        vertA.adjEdges.remove(randEdge)
        vertB.adjEdges.remove(randEdge)
        # Update edges in B, add them to A and remove self connected edges
        for edge in vertB.adjEdges:
            if edge.start == vertB:
                edge.start = vertA
            if edge.end == vertB:
                edge.end = vertA

            if edge.start == edge.end:
                edges.remove(edge)
                vertA.adjEdges.remove(edge)
            else:
                vertA.adjEdges.append(edge)

    return len(edges)


def kargerMinCut(rawGraphData):
    numVert = len(rawGraphData)
    minCut = numVert
    for _ in range(numVert):
        vertices = [None] * numVert
        edges = []
        edgeFilter = {}
        constructGraph(rawGraphData, 0, vertices, edges, edgeFilter)
        cut = randomCut(vertices, edges)
        if cut < minCut:
            minCut = cut
    return minCut


rawGraphData = readIntegers('./kargerMinCut')
count = kargerMinCut(rawGraphData)
print(count)
