#
# Given an interface: getFilesFolders(absPath) which makes a request to a server
# and receives a response containing all files and folders under the dir of given path,
# implement a method: searchFile(absPath, queryStr) which can return a file matched by
# the given query string within the directory & sub directories of given path.
#
# Example of searchFile(...) input:
# 1. '/root/dir1', 'file1'
# 2. '/root/dir1', 't/d'
#
# Example of getFilesFolders(...) output:
#   ['file1', 'file2'], ['/folder1', '/folder2'] (relative paths of its sub directories)
#
# Note: be careful of the output format
#
# Followup: change getFilesFolders(...) to be async and non-blocking and called with a 'callback'
#
# Idea:
# 1. visualize the tree of directories
# 2. DFS / BFS search
#
# Time: O(D + F * LP * LQ) where:
#   D is total number of directories
#   F is total number of files
#   LP, LQ is the max length of path strings and length of query string

import asyncio


# Stub method returning folders and files under /root dir
def getFilesFolders(absPath):
    return [''], ['file']


def searchFile(absPath, query):
    files, folders = getFilesFolders(absPath)
    for f in files:
        absFilePath = absPath + '/' + f
        if query in absFilePath:
            return absFilePath

    for d in folders:
        absDirPath = absPath + '/' + d
        res = searchFile(absDirPath, query)
        if res:
            return res

    return None


print(searchFile('/root', 't/f'))

# Followup solution:
#
# Time: O(log(D)) since code at the same level of the tree can be executed
#       concurrently, the depth of the tree has the upper bound of O(log(D))

from threading import Thread, Lock


def searchFileAsync(absPath, query):
    # Note that a thread cannot write to local vars in another thread,
    # but it can be done through references
    res = set()
    queue = [absPath]
    currDepth = [1]
    maxDepth = 3
    # Avoid thread conflicts when manipulating result set and queue
    appQueueLock = Lock()
    mergeResLock = Lock()

    def searchDirFunc(absPath, depth):
        currDirs, currFiles, currDepth[0] = getFilesFoldersFunc(absPath, depth)
        p = searchFunc(currFiles, currDirs, absPath)
        if p:
            with mergeResLock:
                res.add(p)

    def getFilesFoldersFunc(absPath, currDepth):
        # Stub download method
        if currDepth[0] > maxDepth:
            return [], [], currDepth[0]

        return ['curr', 'curr2'], ['file1', 'file2'], currDepth[0] + 1

    def searchFunc(files, folders, absPath):
        for f in files:
            absFilePath = absPath + '/' + f
            if query in absFilePath:
                return absFilePath

        with appQueueLock:
            for d in folders:
                absDirPath = absPath + '/' + d
                queue.append(absDirPath)

        return None

    while queue:
        tasks = []
        for currPath in queue:
            task = Thread(target=searchDirFunc, args=(currPath, currDepth))
            tasks.append(task)
        queue.clear()

        for task in tasks:
            task.start()

        for task in tasks:
            task.join()
            if len(res) > 0:
                return list(res)[0]

    return None


print(searchFileAsync('/root', 'ot/curr/file1'))


# Solution 2 with await
# Stub method
async def getFilesFoldersAsync(absPath):
    return ([''], [''])


async def searchFileAsyncII(absPath, query):
    files, folders = await getFilesFoldersAsync(absPath)
    for f in files:
        absFilePath = absPath + '/' + f
        if query in absFilePath:
            return absFilePath

    tasks = []
    for d in folders:
        absDirPath = absPath + '/' + d
        tasks.append(searchFileAsync(absDirPath, query))

    results = asyncio.gather(tasks)
    for res in results:
        if res:
            return res

    return None
