# Image there's a running race and we're told that in the result,
# 'A' beats 'B', 'B' beats 'C', ... , we'd like to figure out the
# orderings of runners in the result.
#
# Given an array of pairs: [['A', 'B'], ['B', 'C'], ...], where
# each pair indicates 1st runner beats 2nd runner of the pair,
# output a list of correct ordering.
#
# Idea: topological ordering
#
#
# Followup:
# Output all possible correct orderings of the result.
#
# Idea: topological ordering with DFS backtracking


# Solution 1:
# 1. Post order DFS with a stack
# 2. Memorize node along the path to detect cycles
#
# Time: O(V + E) where V is the number of runners, E is the number of
#       pairs (edges or dependencies)
def raceDFS(rawDep):
    # Build dependencies of the graph
    dep = {}
    for d in rawDep:
        a, b = d
        # Filter out duplicate edges
        if a in dep:
            dep[a].add(b)
        else:
            dep[a] = set([b])

    stack = []
    visited = set()
    for source in dep:
        cyclic = dfs(source, dep, set(), visited, stack)
        if cyclic:
            return []

    return list(reversed(stack))


def dfs(node, dep, path, visited, stack):
    if node in path:
        return True

    if node in visited:
        return False

    path.add(node)
    visited.add(node)

    if node in dep:
        for c in dep[node]:
            cyclic = dfs(c, dep, path, visited, stack)
            if cyclic:
                return True

    path.remove(node)
    stack.append(node)

    return False


# test = [['A', 'B'], ['A', 'C'], ['B', 'D'], ['B', 'E']]
# print(raceDFS(test))


# Solution 2:
# 1. BFS with indegree counting
#
# Time: O(V + E) where V is the number of runners, E is the number of
#       pairs (edges or dependencies)
def raceBFS(rawDep):
    indegree = {}
    # Build dependencies of the graph
    dep = {}
    for d in rawDep:
        a, b = d
        if a in dep:
            if b in dep[a]:
                # Filter out duplicate edges
                continue

            dep[a].add(b)
        else:
            dep[a] = set([b])
        # Compute indegree
        indegree[a] = indegree.get(a, 0)
        indegree[b] = indegree.get(b, 0) + 1

    numNodes = len(indegree)

    topoOrdering = []
    queue = []
    # Init queue with nodes having 0 indegree
    for node in indegree:
        if indegree[node] == 0:
            queue.append(node)
            topoOrdering.append(node)

    while queue:
        node = queue.pop(0)
        if node not in dep:
            continue

        children = dep[node]
        for c in children:
            indegree[c] -= 1
            if indegree[c] == 0:
                queue.append(c)
                topoOrdering.append(c)

    return topoOrdering if len(topoOrdering) == numNodes else []


# test = [['A', 'B'], ['A', 'C'], ['B', 'D'], ['B', 'E']]
# print(raceBFS(test))


# Followup solution 1: BFS with indegree counting
def raceIIBFS(rawDep):
    indegree = {}
    # Build dependencies of the graph
    dep = {}
    for d in rawDep:
        a, b = d
        if a in dep:
            if b in dep[a]:
                # Filter out duplicate edges
                continue

            dep[a].add(b)
        else:
            dep[a] = set([b])
        # Compute indegree
        indegree[a] = indegree.get(a, 0)
        indegree[b] = indegree.get(b, 0) + 1

    numNodes = len(indegree)

    queue = []
    # Init queue of initial state with nodes having 0 indegree
    for node in indegree:
        if indegree[node] == 0:
            queue.append(node)

    res = []
    # DFS backtrack with a stack storing the state containing:
    # 1. queue for BFS topological ordering of the current path
    # 2. topological ordering of current path
    # 3. indegrees of current path
    stack = [(queue, [], indegree)]
    while stack:
        state = stack.pop()
        queue, topoOrdering, indegree = state
        if len(topoOrdering) == numNodes:
            res.append(topoOrdering)
            continue

        for i in range(len(queue)):
            node = queue[i]
            newOrdering = topoOrdering + [node]
            newIndegree = indegree.copy()
            newQueue = queue[:i] + queue[i + 1:]

            if node in dep:
                children = dep[node]
                for c in children:
                    newIndegree[c] -= 1
                    if newIndegree[c] == 0:
                        newQueue.append(c)

            stack.append((newQueue, newOrdering, newIndegree))

    return res


test = [['A', 'B'], ['A', 'C'], ['B', 'D'], ['B', 'E']]
print(raceIIBFS(test))
