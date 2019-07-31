
import math
import datetime
import sys


def readInputData(fileName):
    vertices = None
    distances = None
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
            x = float(rawData[0])
            y = float(rawData[1])
            vertices[i] = (x, y)
            i += 1

        f.close()

    # Init distances buffer
    distances = [[0 for i in range(numVert)] for j in range(numVert)]
    for vertA in range(numVert):
        xA, yA = vertices[vertA]
        for vertB in range(numVert):
            xB, yB = vertices[vertB]
            distances[vertA][vertB] = math.sqrt(
                math.pow(xA - xB, 2) + math.pow(yA - yB, 2))

    return vertices, distances


#############################################################
# A naive bruteforce search approach with running time: O(N!)
#############################################################


class TreeNode:
    def __init__(self, vertId, dist=0):
        self.vertId = vertId
        self.dist = dist
        self.children = []

    def __str__(self):
        return "V: " + str(self.vertId) + " Len: " + self.dist


def constructPathTree(vertices, dist):
    numVert = len(vertices)
    vertAvail = list(range(numVert))
    root = TreeNode(0)
    extendPath(root, vertAvail, dist)

    return root


def extendPath(node, vertAvail, dist):
    if len(vertAvail) == 0:
        return

    for i, vertId in enumerate(vertAvail):
        child = TreeNode(vertId, dist[node.vertId][vertId])
        node.children.append(child)
        # Exclude added vertex as every vertex can only be visited once
        newVertAvail = vertAvail[:i] + vertAvail[i + 1:]
        extendPath(child, newVertAvail, dist)


################################################################
# A dynamic programming approach with running time: O(N^2 * 2^N),
# by decomposing the problem into subproblems in below approach:
#   memo[S, dest] = min(memo[S - {dest}][k] + dist[k][dest])
#
# Correctness proof:
#   Assume there exists another set S2 and k2 so that
#   memo[S2 - {dest}][k2] + dist[k2][dest] is optimal rather than
#   memo[S - {dest}][k] + dist[k][dest] obtained from our optimal
#   substructure, then:
#       it contradicts the rule saying S and k is obtained by
#       taking the mininum among all possible candidates.
#
# Complexity analysis:
# 1. Compute the length of shortest path for every subset of vertices: N ^ 2
# 2. Total number of subsets: 2 ^ N
################################################################

# Represent +infinity
maxDist = sys.maxsize


def getSubSetMask(subSet):
    subSetMask = 0
    for v in subSet:
        subSetMask |= 1 << v

    return subSetMask


# An iterative approach which optimizes memory space use.
#
# Note:
# 1. 'subset' denotes the set of vertices visited in the path,
# excluding the source vertex 0 and destination vertex j.
# 2. Use a 32 bit integer to store vertices in the subset (1 for existence, 0 otherwise)
def travellingSalesmanDPIter(vertices, dist):
    numVert = len(vertices)
    memo = {}
    # Base case:
    #   start with going through an empty set of nodes ending at node 0,
    #   from which it goes to other nodes
    subSet = 0
    memo[subSet] = {}
    for dest in range(1, numVert):
        memo[subSet][dest] = dist[0][dest]

    # Outer loop increasing subset size from 1 to N - 2
    for _ in range(numVert - 2):
        # Enum and loop over all previous subsets
        for subSet, distsToKs in list(memo.items()):
            for dest in range(1, numVert):
                if subSet & (1 << dest):
                    # Destination exists in the subset
                    continue

                # Enum and loop over destinations go through
                # intermediate vertex k
                for k, distToK in distsToKs.items():
                    if dest == k:
                        continue

                    # Compute the new dist to the vertex going through k
                    newDist = distToK + dist[k][dest]

                    # Generate a new subset with kth bit enabled
                    newSubSet = subSet | (1 << k)
                    if newSubSet not in memo:
                        # Add the new subset containing k to the new set of subsets
                        memo[newSubSet] = {dest: newDist}
                    elif dest not in memo[newSubSet] or newDist < memo[newSubSet][dest]:
                        memo[newSubSet][dest] = newDist

            # Remove previously used subsets
            memo.pop(subSet)

    # Find min dist by adding the edge returning to source vertex 0
    minDist = maxDist
    for subSet in memo:
        for dest, distToDest in memo[subSet].items():
            minDist = min(minDist, distToDest + dist[dest][0])

    return minDist


# A recursive implementation which is faster then the iterative approach,
# but it takes a lot more memory space.
#
# Note: 'subset' denotes the set of vertices visited in the path,
# including the destination vertex j.
def travellingSalesmanDPRec(vertices, dist):
    numVert = len(vertices)
    memo = {}
    # Init with base cases:
    # Base case 1: subset {0} and dest is 0
    memo[str([0])] = {0: 0}
    for dest in range(1, numVert):
        # Base case 2: subset {0} and dest is not 0
        memo[str([0])][dest] = maxDist

    minDist = maxDist
    # Exclude source vertex
    vertSet = list(range(numVert))
    vertSetKeyStr = str(vertSet)
    for dest in vertSet:
        # Add the last edge returning back to the source vertex
        totalDist = divide(vertSet, vertSetKeyStr, dist,
                           memo, dest) + dist[dest][0]
        if totalDist < minDist:
            minDist = totalDist

    return minDist


def divide(vertSubSet, subSetKeyStr, dist, memo, dest):
    # Note that the subset of vertex ids are naturally sorted
    if subSetKeyStr not in memo:
        memo[subSetKeyStr] = {}
    elif dest in memo[subSetKeyStr]:
        return memo[subSetKeyStr][dest]

    # Remove j from subset
    newVertSubSet = []
    for id in vertSubSet:
        if id == dest:
            continue
        newVertSubSet.append(id)
    newSubSetKeyStr = str(newVertSubSet)

    minDist = maxDist
    for k in newVertSubSet:
        # Dist to k plus dist between k-j and find min
        totalDist = divide(newVertSubSet, newSubSetKeyStr,
                           dist, memo, k) + dist[k][dest]
        if totalDist < minDist:
            minDist = totalDist
    memo[subSetKeyStr][dest] = minDist

    return minDist


#############################################################
# An approximation approach using greedy strategy
# ...
#############################################################


START_TIME = datetime.datetime.now().replace(microsecond=0)

vertices, dist = readInputData("./tspMid")
min = travellingSalesmanDPIter(vertices, dist)

END_TIME = datetime.datetime.now().replace(microsecond=0)

print(str(min))
print(str(END_TIME - START_TIME))
