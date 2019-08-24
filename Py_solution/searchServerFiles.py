#
# Given an interface: getFilesFolders(absPath, queryStr) which makes a request to a server
# and receives a response containing all files and folders under the given path,
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
# Followup: change getFilesFolders(...) to be async and non-blocking and called with a 3rd
#           param 'callback'
#
# Idea:
# 1. visualize the tree of directories
# 2. search for file recursively in the tree
#
# Time: O(D + F * LP * LQ) where:
#   D is total number of directories
#   F is total number of files
#   LP, LQ is the max length of path strings and length of query string

import asyncio


# Stub method returning folders and files under /root dir
def getFilesFolders(absPath, query):
    return [''], ['file']


def searchFile(absPath, query):
    files, folders = getFilesFolders(absPath, query)
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

# Work around async - await with counting
callCount = 0
respCount = 0


# Stub method
def getFilesFoldersAsync(absPath, query, callback):
    callback([''], [''])


def searchFileAsync(absPath, query):
    res = None
    callCount += 1

    def callback(files, folders):
        for f in files:
            absFilePath = absPath + '/' + f
            if query in absFilePath:
                res = absFilePath
                respCount += 1
                return

        for d in folders:
            absDirPath = absPath + '/' + d
            searchFileAsync(absDirPath, query)

        respCount += 1

    getFilesFoldersAsync(absPath, query, callback)

    while callCount > respCount:
        continue

    return res


# Solution 2 with await
async def searchFileAsyncII(absPath, query):
    files, folders = await getFilesFoldersAsync(absPath, query)
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
