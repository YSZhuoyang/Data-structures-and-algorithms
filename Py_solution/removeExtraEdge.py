# From: https://leetcode.com/discuss/interview-question/358676/google-remove-extra-edge

# Given a binary tree, where an arbitary node has 2 parents
# i.e two nodes in the tree have the same child. Identify the
# defective node and remove an extra edge to fix the tree.
#
# Assume value in each node is unique.
#
# Similar to: https://leetcode.com/problems/validate-binary-search-tree/

# Input range:
#

# Idea:
# 1. Detect cycles in a graph with e.g. DFS

# Time: O(N)
# Space: O(N)


class Node:
    def __init__(self, v):
        self.v = v
        self.l = None
        self.r = None


def removeExtraEdge(tree):
    visited = set()
    stack = [tree]
    while stack:
        node = stack.pop()
        if node.l:
            if node.l in visited:
                node.l = None
            else:
                stack.append(node.l)
        if node.r:
            if node.r in visited:
                node.r = None
            else:
                stack.append(node.r)

    return tree


# Followup: what if the tree is a BST?
#
# Idea:
# 1. DFS with upper and lower constraint
#
# 2. In order DFS

# Time: O(N)
# Space: O(1) ignoring space consumed by input


def removeExtraEdgeFromBST(tree):
    # lmax: upper bound for left subtree
    # rmin: lower bount for right subtree
    def rebuild(node, lmin, rmax):
        if node == None:
            return None

        isValid = (lmin == None or node.v > lmin) and (rmax == None
                                                       or node.v < rmax)
        if not isValid:
            print('found lower: ' + str(lmin))
            print('found upper: ' + str(rmax))
            print('found lower failed: ' + str(node.v))
            return None

        node.l = rebuild(node.l, lmin, node.v)
        node.r = rebuild(node.r, node.v, rmax)

        return node

    return rebuild(tree, None, None)


def inOrderPrint(tree):
    if not tree:
        return

    inOrderPrint(tree.l)
    print(tree.v)
    inOrderPrint(tree.r)


root = Node(5)
root.l = Node(3)
root.r = Node(8)
root.l.l = Node(1)
root.l.r = Node(4)
root.r.l = root.l.r

inOrderPrint(removeExtraEdgeFromBST(root))