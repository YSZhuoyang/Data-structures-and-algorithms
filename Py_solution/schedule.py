
from functools import cmp_to_key


class Job:
    def __init__(self, w, l):
        self.weight = w
        self.length = l


def readIntegers(fileName):
    jobs = []
    with open(fileName) as f:
        # Read edge number (ignored)
        f.readline()
        # Read edge length and weight
        for line in f:
            rowData = line.split()
            jobs.append(Job(int(rowData[0]), int(rowData[1])))
        f.close()

    return jobs

def compare(x, y):
    firstRound = (x.weight - x.length) - (y.weight - y.length)
    if firstRound != 0:
        return firstRound
    
    secondRound = x.weight - y.weight
    return secondRound

def schedule1(jobs):
    # sortedJobs = sorted(jobs, key=lambda job: job.weight, reverse=True)
    # sortedJobs = sorted(sortedJobs, key=lambda job: job.weight - job.length, reverse=True)
    sortedJobs = sorted(jobs, key=cmp_to_key(compare), reverse=True)
    completion = 0
    totalWeightedCompletion = 0
    for job in sortedJobs:
        completion += job.length
        totalWeightedCompletion += completion * job.weight
    return totalWeightedCompletion

def schedule2(jobs):
    sortedJobs = sorted(jobs, key=lambda job: float(job.weight) / float(job.length), reverse=True)
    completion = 0
    totalWeightedCompletion = 0
    for job in sortedJobs:
        completion += job.length
        totalWeightedCompletion += completion * job.weight
    return totalWeightedCompletion


jobs = readIntegers("./jobs")
totalWeightedCompletion = schedule2(jobs)
print(str(totalWeightedCompletion))
