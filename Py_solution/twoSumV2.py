# A significantly faster solution for 2 sum problem,
# using an AVL tree and a hashmap.


class TreeNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.index = None
        self.height = 0


# Assume no duplicate exists
class AVLTree:
    def __init__(self):
        self.root = None

    def insert(self, value):
        if self.root == None:
            self.root = TreeNode(value)
            return

        if not self.__recInsert(self.root, value):
            return

        balanceFactor = self.__getBalance(self.root)
        if balanceFactor == 2 or balanceFactor == -2:
            self.root = self.__balance(self.root)
        else:
            self.root.height = self.__UpdateHeight(self.root)

    def print(self):
        self.__inOrderPrint(self.root)

    def sort(self):
        sorted = []
        self.__inOrderSort(self.root, sorted)
        return sorted

    def findInsertPos(self, value):
        return self.__recSearch(self.root, value)

    def height(self):
        return self.__getHeight(self.root)

    def __recSearch(self, node, value):
        if node == None:
            return -1

        if value < node.value:
            leftRes = self.__recSearch(node.left, value)
            if leftRes == -1:
                return node.index
            else:
                return leftRes
        elif value > node.value:
            rightRes = self.__recSearch(node.right, value)
            if rightRes == -1:
                return node.index
            else:
                return rightRes
        else:
            return node.index

    # Return true to new node was inserted successfully
    def __recInsert(self, node, value):
        if node == None or node.value == value:
            # Insertion failed
            return False

        if value < node.value:
            if node.left == None:
                node.left = TreeNode(value)
                if node.right == None:
                    # A new child is inserted to the left while right child does not exist,
                    # thus height is increased by one
                    node.height = 1
                return True
            elif self.__recInsert(node.left, value):
                leftBalance = self.__getBalance(node.left)
                if leftBalance == -2 or leftBalance == 2:
                    # Child height increased which led to unbalance
                    node.left = self.__balance(node.left)
                else:
                    node.left.height = self.__UpdateHeight(node.left)
                return True
        else:
            if node.right == None:
                node.right = TreeNode(value)
                if node.left == None:
                    # A new child is inserted to the right while left child does not exist,
                    # thus height is increased by one
                    node.height = 1
                return True
            elif self.__recInsert(node.right, value):
                rightBalance = self.__getBalance(node.right)
                if rightBalance == -2 or rightBalance == 2:
                    # Child height increased which led to unbalance
                    node.right = self.__balance(node.right)
                else:
                    node.right.height = self.__UpdateHeight(node.right)
                return True

        return False

    def __leftRot(self, node):
        if node == None or node.right == None:
            return node

        newRoot = node.right
        newLeft = node
        # Shift subtree
        newLeft.right = newRoot.left
        # Reconnect
        newRoot.left = newLeft
        # Update height
        newLeft.height = self.__UpdateHeight(newLeft)
        newRoot.height = self.__UpdateHeight(newRoot)

        return newRoot

    def __rightRot(self, node):
        if node == None or node.left == None:
            return node

        newRoot = node.left
        newRight = node
        # Shift subtree
        newRight.left = newRoot.right
        # Reconnect
        newRoot.right = newRight
        # Update height
        newRight.height = self.__UpdateHeight(newRight)
        newRoot.height = self.__UpdateHeight(newRoot)

        return newRoot

    def __leftRightRot(self, node):
        if node == None or node.left == None or node.left.right == None:
            return node

        node.left = self.__leftRot(node.left)
        return self.__rightRot(node)

    def __rightLeftRot(self, node):
        if node == None or node.right == None or node.right.left == None:
            return node

        node.right = self.__rightRot(node.right)
        return self.__leftRot(node)

    def __balance(self, node):
        if node == None:
            return None

        # Note that the case where:
        # abs(node.balanceFactor) == 2 && anyChild.balanceFactor == 0
        # does not exist
        rootBalance = self.__getBalance(node)
        leftBalance = self.__getBalance(node.left)
        rightBalance = self.__getBalance(node.right)
        if rootBalance == 2 and rightBalance == 1:
            # Right - right double heavy
            return self.__leftRot(node)
        elif rootBalance == -2 and leftBalance == -1:
            # Left - left double heavy
            return self.__rightRot(node)
        elif rootBalance == 2 and rightBalance == -1:
            # Right - left double heavy
            return self.__rightLeftRot(node)
        elif rootBalance == -2 and leftBalance == 1:
            # Left - right double heavy
            return self.__leftRightRot(node)

        return node

    def __UpdateHeight(self, node):
        return max(self.__getHeight(node.left), self.__getHeight(node.right)) + 1

    # balanceFactor = height(right) - height(left)
    def __getBalance(self, node):
        if node == None:
            return None

        leftH = self.__getHeight(node.left)
        rightH = self.__getHeight(node.right)
        return rightH - leftH

    def __getHeight(self, node):
        if node == None:
            return -1

        return node.height

    def __inOrderSort(self, node, sorted):
        if node == None:
            return

        self.__inOrderSort(node.left, sorted)
        node.index = len(sorted)
        sorted.append(node.value)
        self.__inOrderSort(node.right, sorted)

    def __inOrderPrint(self, node):
        if node == None:
            return

        self.__inOrderPrint(node.left)
        print(str(node.value))
        self.__inOrderPrint(node.right)


def testAVLTree():
    testData = [5, 3, 6, 7, 9, 4, 45, 345, 8, 565, 34, 43]
    tree = AVLTree()
    for i in testData:
        tree.insert(i)
    tree.sort()
    tree.print()
    print(str(tree.findInsertPos(43)))
    print(str(tree.height()))

# testAVLTree()


def readIntegers(fileName):
    array = []

    with open(fileName) as f:
        for line in f:
            array.append(int(line))
        f.close()

    return array


def twoSum(array):
    memo = {}
    tree = AVLTree()
    for x in array:
        tree.insert(x)

    sorted = tree.sort()
    for x in sorted:
        upper = 10000 - x
        lower = -10000 - x

        upperPos = tree.findInsertPos(upper)
        lowerPos = tree.findInsertPos(lower)

        if sorted[upperPos] + x > 10000:
            upperPos -= 1

        if sorted[lowerPos] + x < -10000:
            lowerPos += 1

        for pos in range(lowerPos, upperPos + 1):
            test = sorted[pos] + x
            memo[test] = True

    return len(memo)


array = readIntegers("./2sum")
numAvail = twoSum(array)
print(str(numAvail))
