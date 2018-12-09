
from sys import maxsize
import numpy as np


class Vertex:
    def __init__(self, id):
        self.id = id
        # A dict storing adjVertex - edgeLen
        self.adjVertices = {}

    def __str__(self):
        return str(self.id)


def readInputData(fileName):
    vertices = None
    with open(fileName) as f:
        # Read total capacity of weight and number of items available
        firstRow = f.readline().split()
        numVert = int(firstRow[0])
        numEdges = int(firstRow[1])
        # Init vertex buffer
        vertices = [None] * numVert
        for i in range(numVert):
            vertices[i] = Vertex(i)

        # Read vertex ids of the directed edge and its length.
        for line in f:
            rawData = line.split()
            # Make them 0-indexed
            startVert = int(rawData[0]) - 1
            endVert = int(rawData[1]) - 1
            edgeLen = int(rawData[2])
            vertices[startVert].adjVertices[endVert] = edgeLen

        f.close()

    return vertices, numEdges


def floydWarshall(graphData, numEdges):
    numVert = len(graphData)
    # Assume there's no shortest path bigger than the below value
    maxDist = int((maxsize - 1) / 2)
    # Optimize the space use by only memorizing the distance
    # calculated with current & previous k
    memo = [[[maxDist for i in range(numVert)] for j in range(numVert)] for k in range(2)]
    # memo = np.full((numVert, numVert, 2), maxDist)
    # Initialize memo of base cases
    for j in range(numVert):
        for i in range(numVert):
            # Base case where k is 0 meaning the path does no go through any vertex except
            # source and target vertices.
            if i == j:
                # Source and target are the same vertex.
                memo[0][j][j] = 0
            else:
                # Source and target are different and there's a direct edge between them. 
                vertI = graphData[i]
                vertJ = graphData[j]
                if j in vertI.adjVertices:
                    memo[0][i][j] = vertI.adjVertices[j]
                if i in vertJ.adjVertices:
                    memo[0][j][i] = vertJ.adjVertices[i]

    print("Initialization finished")

    for k in range(1, numVert):
        for j in range(numVert):
            for i in range(numVert):
                shortestWithoutK = memo[0][i][j]
                iToK = memo[0][i][k]
                kToJ = memo[0][k][j]
                if shortestWithoutK > iToK + kToJ:
                    memo[1][i][j] = iToK + kToJ
                else:
                    memo[1][i][j] = shortestWithoutK

            # Detect if there're any negative cycles
            if memo[1][j][j] < 0:
                print("negative cycle detected")
                return None
        
        # Overwrite memory of the previous k by swaping 2 buffer arrays
        temp = memo[0]
        memo[0] = memo[1]
        memo[1] = temp

    # for k in range(1, numVert):
    #     for j in range(numVert):
    #         for i in range(numVert):
    #             shortestWithoutK = memo[k - 1][i][j]
    #             iToK = memo[k - 1][i][k]
    #             kToJ = memo[k - 1][k][j]
    #             if shortestWithoutK > iToK + kToJ:
    #                 memo[k][i][j] = iToK + kToJ
    #             else:
    #                 memo[k][i][j] = shortestWithoutK

    # min = maxDist
    # for j in range(numVert):
    #     for i in range(numVert):
    #         if i != j and min > memo[numVert - 1][i][j]:
    #             min = memo[numVert - 1][i][j]

    print("Shortest path computation finished")

    # Find "shortest shortest path"
    min = maxDist
    for j in range(numVert):
        for i in range(numVert):
            if i != j and min > memo[0][i][j]:
                min = memo[0][i][j]

    return min


graphData, numEdges = readInputData("./g1")
min = floydWarshall(graphData, numEdges)
print(str(min))
