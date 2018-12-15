
import math
import sys
import datetime


def readInputData(fileName):
    vertices = None
    with open(fileName) as f:
        # Read total capacity of weight and number of items available
        firstRow = f.readline()
        numVert = int(firstRow)

        # Init vertices buffer
        vertices = [None] * numVert

        # Read vertex coordinates.
        i = 0
        for line in f:
            rawData = line.split()
            # Read coordinates
            # vertId = int(rawData[0])
            x = float(rawData[1])
            y = float(rawData[2])
            vertices[i] = (x, y)
            i += 1

        f.close()

    return vertices


################################################################
# A approximation approach using greedy heruistics buying speed
# with accuracy, with running time:
################################################################


def travellingSalesManGreedyHeruistic(vertices):
    numVert = len(vertices)
    totalDist = 0
    visitedSet = set()
    # Start from vert 0
    startId = 0
    visitedSet.add(startId)

    # Iterate over all other vertices
    while len(visitedSet) < numVert:
        xA, yA = vertices[startId]
        minDistAdj = None
        minDist = sys.float_info.max
        destId = 0
        for dest in vertices:
            if destId in visitedSet:
                destId += 1
                continue

            xB, yB = dest
            dist = math.sqrt(
                math.pow(xA - xB, 2) + math.pow(yA - yB, 2))

            if minDist == None or dist < minDist:
                minDist = dist
                minDistAdj = destId

            destId += 1

        startId = minDistAdj
        visitedSet.add(startId)
        totalDist += minDist

    # Add the distance between the last visited vertex to source vertex 0
    xA, yA = vertices[startId]
    xB, yB = vertices[0]
    dist = math.sqrt(
        math.pow(xA - xB, 2) + math.pow(yA - yB, 2))
    totalDist += dist

    return totalDist


START_TIME = datetime.datetime.now().replace(microsecond=0)
vertices = readInputData("./nn")
min = travellingSalesManGreedyHeruistic(vertices)
END_TIME = datetime.datetime.now().replace(microsecond=0)
print(str(min))
print(str(END_TIME - START_TIME))
