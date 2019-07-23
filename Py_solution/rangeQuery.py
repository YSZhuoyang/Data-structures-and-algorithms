
# Implement below class which checks if a number input is
# within any given ranges which do not overlap

# class RangeQuery:
#   insert_nonoverlapping_range(int start, int end)
#   is_point_in_any_range(int point) --> Bool
#   // Assume 0 <= point <= 1000000.

# Example:
#   insert: (10,39), (112, 179), (77,77)
#   queries: 20 -> true
#            50 -> false
#            77 -> true


# Idea 1:
# Build a bst
# To be improved: build a self balanced bst e.g. AVL tree

# Time: O(log(N)) for both insertion and query
# Space: O(N)

# Idea 2:
# Maintain an array buffer of booleans, and set all values of
# the given range to True during insertion

# Time: O(1) for query and O(M) for insertion where M is size of the given range
# Space: O(X) where X is the range of query value

# Cons: too much memo consumption

class Node:
    def __init__(self, l, u):
        self.l = l
        self.u = u
        self.left = None
        self.right = None

class BST:
    def __init__(self):
        self.root = None

    def find(self, t):
        return self.findRec(t, self.root)

    def findRec(self, t, node):
        if node == None:
            return False
        elif node.l <= t and node.u >= t:
            return True
        elif node.l > t:
            return self.findRec(t, node.left)
        else:
            return self.findRec(t, node.right)

    def insert(self, l, u):
        if self.root == None:
            self.root = Node(l, u)
        else:
            self.insertRec(self.root, l, u)

    def insertRec(self, node, l, u):
        if node.l > u:
            if node.left == None:
                node.left = Node(l, u)
            else:
                self.insertRec(node.left, l, u)
        else:
            if node.right == None:
                node.right = Node(l, u)
            else:
                self.insertRec(node.right, l, u)

class RangeQuery:
    def __init__(self):
        self.tree = BST()

    def insert_nonoverlapping_range(self, start, end):
        self.tree.insert(start, end)

    def is_point_in_any_range(self, point):
        return self.tree.find(point)

solution = RangeQuery()
solution.insert_nonoverlapping_range(10,39)
solution.insert_nonoverlapping_range(112, 179)
solution.insert_nonoverlapping_range(77,77)
print(solution.is_point_in_any_range(20))
print(solution.is_point_in_any_range(50))
print(solution.is_point_in_any_range(77))
