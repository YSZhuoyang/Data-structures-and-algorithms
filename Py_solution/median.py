

class Heap:
    def __init__(self, minHeap=True):
        self.buffer = []
        self.minHeap = minHeap

    def getSize(self):
        return len(self.buffer)

    def insert(self, ele):
        preSize = len(self.buffer)
        self.buffer.append(ele)
        # Perform a heapify by bubbling up inserted key
        self.__heapifyUpIter(preSize)

    def extractMin(self):
        preSize = len(self.buffer)
        if preSize == 0:
            return None

        min = self.buffer[0]
        if preSize == 1:
            self.buffer = []
            return min

        # Shift the bottom right most element to the top
        self.buffer[0] = self.buffer.pop(preSize - 1)
        # Perform a heapify by bubbling down moved element
        self.__heapifyDownIter(0)

        return min

    def peek(self):
        if len(self.buffer) == 0:
            return None

        return self.buffer[0]

    #-----------------------------------------------------#
    #------------------ Util functions -------------------#
    #-----------------------------------------------------#

    def __getChildrenId(self, parentId):
        size = len(self.buffer)
        left = parentId * 2 + 1
        right = left + 1

        if left >= size:
            return None, None

        if right >= size:
            return left, None

        return left, right

    def __getParentId(self, child):
        if child < 0:
            raise Exception("Invalid child id")

        if child == 0:
            return None

        parentId = int((child - 1) / 2)

        return parentId

    #-----------------------------------------------------#
    #----------------- Iterative heapify -----------------#
    #-----------------------------------------------------#

    def __heapifyDownIter(self, rootId):
        root = self.buffer[rootId]
        parentId = rootId
        while True:
            leftId, rightId = self.__getChildrenId(parentId)
            if leftId == None:
                self.buffer[parentId] = root
                break

            selectedChildId = leftId
            if rightId != None:
                right = self.buffer[rightId]
                left = self.buffer[leftId]
                if (self.minHeap and left > right) or (not self.minHeap and left < right):
                    selectedChildId = rightId

            selectedChild = self.buffer[selectedChildId]
            if (self.minHeap and selectedChild >= root) or (not self.minHeap and selectedChild <= root):
                self.buffer[parentId] = root
                break

            # Shift selected child up
            self.buffer[parentId] = selectedChild
            parentId = selectedChildId

    def __heapifyUpIter(self, leafId):
        leaf = self.buffer[leafId]
        childId = leafId
        while True:
            parentId = self.__getParentId(childId)
            if parentId == None:
                self.buffer[childId] = leaf
                break

            parent = self.buffer[parentId]
            if (self.minHeap and parent <= leaf) or (not self.minHeap and parent >= leaf):
                self.buffer[childId] = leaf
                break

            # Shift parent down
            self.buffer[childId] = self.buffer[parentId]
            childId = parentId


def testHeap():
    testData = [4, 2, 5, 3, 4, 6, 7, 8, 1, 4, 2, 345, 21, 5, 7]
    heap = Heap()
    # Build heap
    for vert in testData:
        heap.insert(vert)
    min = heap.extractMin()
    while min != None:
        print(str(min))
        min = heap.extractMin()


def readIntegers(fileName):
    array = []

    with open(fileName) as f:
        for line in f:
            array.append(int(line))
        f.close()

    return array


def maintainMedian(testData):
    # Greater half of elements
    minHeap = Heap()
    # Smaller half of elements
    maxHeap = Heap(minHeap=False)
    medians = []
    maxHeap.insert(testData[0])
    medians.append(testData[0])
    for ele in testData[1:]:
        if ele < maxHeap.peek():
            maxHeap.insert(ele)
        else:
            minHeap.insert(ele)

        maxHeapSize = maxHeap.getSize()
        minHeapSize = minHeap.getSize()

        while maxHeapSize > minHeapSize + 1:
            smallerMax = maxHeap.extractMin()
            minHeap.insert(smallerMax)
            maxHeapSize -= 1
            minHeapSize += 1

        while minHeapSize > maxHeapSize + 1:
            greaterMin = minHeap.extractMin()
            maxHeap.insert(greaterMin)
            maxHeapSize += 1
            minHeapSize -= 1

        if minHeapSize > maxHeapSize:
            medians.append(minHeap.peek())
        else:
            medians.append(maxHeap.peek())

    return medians


# testData = [4, 2, 5, 3, 4, 6, 7, 8, 1, 4, 2, 345, 21, 5, 7]
testData = readIntegers('./Median')
arrayLen = len(testData)
medians = maintainMedian(testData)
sumOfMedians = 0
for median in medians:
    sumOfMedians += median
print(str(sumOfMedians % arrayLen))
