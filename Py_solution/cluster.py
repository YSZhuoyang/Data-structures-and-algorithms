
from copy import deepcopy


# Kruskal's MST algorithm (given graph G with N vertices and M edges):
# 1. Sort edges based on the weight in in ascending order.
# 2. For 1 : M:
#        Pick the cheapest edge
#        If adding it to MST results in a cycle:
#            Throw it away and continue
#        Add it to the MST
#        Stop early if: N(MST) == N - 1


class Vertex:
    def __init__(self, id):
        self.id = id
        self.leader = id

    def __str__(self):
        return str(self.id) + " leader: " + str(self.leader)


class Edge:
    def __init__(self, vertA, vertB, w):
        self.vertA = vertA
        self.vertB = vertB
        self.w = w

    def __str__(self):
        return str(self.vertA) + " - " + str(self.vertB) + ", w: " + str(self.w)


def readGraphData(fileName):
    edges = []
    vertices = None
    with open(fileName) as f:
        # Read vertex number (ignored)
        numVert = int(f.readline())
        # Init vertex buffer
        vertices = [None] * numVert
        for i in range(numVert):
            vertices[i] = Vertex(i)

        # Read undirected edge length and weight.
        # Note that i-j & j-i are equivalent and only
        # one of them will appear and be stored
        for line in f:
            rowData = line.split()
            vertA = vertices[int(rowData[0]) - 1]
            vertB = vertices[int(rowData[1]) - 1]
            weight = int(rowData[2])
            edges.append(Edge(vertA, vertB, weight))
        f.close()

    return vertices, edges


# Same as Kruskal's MST algorithm except it stops earlier
# by tracking vertices in each group (e.g. with Union-find),
# and terminate when number of groups is equivalent to
# target number of clusters.
def KruskalCluster(vertices, edges, numClusters):
    numEdges = len(edges)
    sortedEdges = sorted(edges, key=lambda edge: edge.w)

    # Init groups each of which starts with one vertex
    groups = {}
    for i in range(len(vertices)):
        groups[i] = [vertices[i]]

    minEdgeId = 0
    while len(groups) > numClusters and minEdgeId < numEdges:
        # Find the cheapest edge of the rest
        edge = sortedEdges[minEdgeId]
        minEdgeId += 1

        vertA = edge.vertA
        vertB = edge.vertB
        # Check no cycle with union-find
        if vertA.leader == vertB.leader:
            continue

        # Fuse them, find min group, and update its leader
        groupALeader = vertA.leader
        groupBLeader = vertB.leader
        groupA = groups[groupALeader]
        groupB = groups[groupBLeader]
        if len(groupA) > len(groupB):
            for groupBVert in groupB:
                groupBVert.leader = groupALeader
                groupA.append(groupBVert)
            groups.pop(groupBLeader)
        else:
            for groupAVert in groupA:
                groupAVert.leader = groupBLeader
                groupB.append(groupAVert)
            groups.pop(groupALeader)

    # Find max spacing
    while minEdgeId < numEdges:
        # Find the cheapest edge of the rest
        edge = sortedEdges[minEdgeId]
        minEdgeId += 1

        vertA = edge.vertA
        vertB = edge.vertB
        # Check no cycle with union-find
        if vertA.leader != vertB.leader:
            break

    maxSpacing = sortedEdges[minEdgeId - 1].w

    return groups, maxSpacing


vertices, edges = readGraphData("./clustering1")
clusters, maxSpacing = KruskalCluster(vertices, edges, 4)
print(str(maxSpacing))
