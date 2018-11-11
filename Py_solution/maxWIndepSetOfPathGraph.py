

def readPathGraphData(fileName):
    vertices = None
    with open(fileName) as f:
        # Read number of symbols
        numVert = int(f.readline())
        # Init vertex buffer
        vertices = [None] * numVert

        # Read undirected edge length and weight.
        # Note that i-j & j-i are equivalent and only
        # one of them will appear and be stored
        i = 0
        for line in f:
            weight = int(line)
            vertices[i] = weight
            i += 1
        f.close()

    return vertices


# Returns indices of vertices belonging to the independent set with max weight
def reconstruct(start, vertices, memo, solution):
    if start >= len(vertices):
        return

    if start == len(vertices) - 1:
        solution.append(start)
        return

    if start == len(vertices) - 2:
        if memo[start] > memo[start + 1]:
            solution.append(start)
        else:
            solution.append(start + 1)

        return

    choiceA = vertices[start] + memo[start + 2]
    choiceB = memo[start + 1]
    if choiceA > choiceB:
        solution.append(start)
        reconstruct(start + 2, vertices, memo, solution)
    else:
        reconstruct(start + 1, vertices, memo, solution)


def divide(start, vertices, memo):
    if vertices == None or start >= len(vertices):
        return 0

    if start in memo:
        return memo[start]

    excludedVertW = vertices[start]
    choiceA = excludedVertW + divide(start + 2, vertices, memo)
    choiceB = divide(start + 1, vertices, memo)
    memo[start] = max(choiceA, choiceB)

    return memo[start]


def maxWeightOfIndependentSetOfPathGraph(vertices):
    memo = {}
    solution = []
    divide(0, vertices, memo)
    reconstruct(0, vertices, memo, solution)

    return solution


vertices = readPathGraphData("./mwis")
solution = maxWeightOfIndependentSetOfPathGraph(vertices)
sum = sum([vertices[i] for i in solution])
print(str(sum))

# Question: of the vertices 1, 2, 3, 4, 17, 117, 517, and 997,
# which ones belong to the maximum-weight independent set?
# (By "vertex 1" we mean the first vertex of the graph---there is no vertex 0.)
# Answer the question with a 8-bit string, where the ith bit should be 1 if the
# ith of these 8 vertices is in the maximum-weight independent set, and 0 otherwise.
ans = ""
vertToCheck = {}
for i in [1, 2, 3, 4, 17, 117, 517, 997]:
    id = i - 1
    vertToCheck[id] = False

for v in solution:
    if v in vertToCheck:
        vertToCheck[v] = True

for v, exist in vertToCheck.items():
    if exist:
        ans += "1"
    else:
        ans += "0"

print(ans)
