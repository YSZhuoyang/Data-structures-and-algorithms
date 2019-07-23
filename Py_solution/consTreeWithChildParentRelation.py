class Node:
    def __init__(self, v):
        self.v = v
        self.children = []


def readRelationData(fileName):
    relations = []
    with open(fileName) as f:
        # Read parent - child relations
        for line in f:
            rowData = line.split()
            p = int(rowData[0])
            c = int(rowData[1])
            relations.append((p, c))
        f.close()

    return relations


def reconstruct(relations):
    pareChildMap = {}
    root = None
    # Find root order and parent - children set
    for r in relations:
        p, c = r
        if p == c:
            # Init root node seperately
            root = Node(p)
        else:
            if p in pareChildMap:
                pareChildMap[p].append(c)
            else:
                pareChildMap[p] = [c]

    print(pareChildMap)
    # Do DFS
    stack = [root]
    while stack:
        node = stack.pop()
        pid = node.v
        if pid in pareChildMap:
            cValues = pareChildMap[pid]
            for cv in cValues:
                cNode = Node(cv)
                node.children.append(cNode)
                stack.append(cNode)

    return root


def printTree(root: Node):
    # BFS
    queue = [(root, 0)]
    preDepth = 0
    while queue:
        node, depth = queue.pop(0)
        if depth > preDepth:
            print('')

        print(node.v, end=' ')
        preDepth = depth
        for c in node.children:
            queue.append((c, depth + 1))

    print('')


relations = readRelationData('parentChildRelations')
tree = reconstruct(relations)
printTree(tree)