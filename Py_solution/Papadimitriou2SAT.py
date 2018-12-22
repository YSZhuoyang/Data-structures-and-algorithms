
from random import random, randint
from math import log2

# For another solution reducing 2SAT problem to a computing strongly
# connected components problem, refer to:
# https://www.geeksforgeeks.org/2-satisfiability-2-sat-problem/


def readInputData(fileName):
    clauses = None
    variables = set()
    with open(fileName) as f:
        # Read total capacity of weight and number of items available
        firstRow = f.readline().split()
        # which equals to the number of clauses
        numVariables = int(firstRow[0])
        numClauses = numVariables

        # Read clauses (variable pairs)
        clauses = [None] * numClauses
        i = 0
        for line in f:
            rawData = line.split()
            v = int(rawData[0])
            w = int(rawData[1])
            variables.add(abs(v))
            variables.add(abs(w))
            clauses[i] = (v, w)
            i += 1
        f.close()

    return variables, clauses, numVariables, numClauses


# This method is run repeatedly to reduce number of clauses and variables.
# If any variable has only one representation (negated or not) in all clauses,
# than we can remove all this clauses from the task. Because we can easely
# make all such clause to be valid, by setting this variable to true or false.
def reduction(clauses, variables):
    positiveVars = set()
    negativeVars = set()
    for literalA, literalB in clauses:
        if literalA > 0:
            positiveVars.add(literalA)
        else:
            negativeVars.add(-literalA)

        if literalB > 0:
            positiveVars.add(literalB)
        else:
            negativeVars.add(-literalB)

    # Find all literals that are all positive / negative in all clauses
    singleSideVars = set()
    for posLiteral in positiveVars:
        if posLiteral not in negativeVars:
            singleSideVars.add(posLiteral)

    for negLiteral in negativeVars:
        if negLiteral not in positiveVars:
            singleSideVars.add(negLiteral)

    reducedClauses = []
    for literalA, literalB in clauses:
        if (abs(literalA) not in singleSideVars) and (abs(literalB) not in singleSideVars):
            reducedClauses.append((literalA, literalB))

    reducedVars = set()
    for literalA, literalB in reducedClauses:
        reducedVars.add(abs(literalA))
        reducedVars.add(abs(literalB))

    return reducedVars, reducedClauses, len(reducedVars), len(reducedClauses)


def randGenStartingSolution(variables):
    initialAttempt = {}
    for var in variables:
        initialAttempt[var] = (random() >= 0.5)

    return initialAttempt


# Papadimitriou's random local search algorithm with probability
# of correctly reporting an unsatisfiable input: 100%, and probability
# of correctly reporting an satisfiable input: >= 1 - 1/n.
def PapadimitriouRandom(variables, clauses, numVariables):
    numTriesLimit = int(log2(numVariables))
    for i in range(numTriesLimit):
        initialAttempt = randGenStartingSolution(variables)
        isSatisfied = localSearch(initialAttempt, clauses, numVariables)
        if isSatisfied:
            return True

    return False


def testWithClauses(attempt, clauses):
    unsatisfiedClauses = []
    for clause in clauses:
        literalA, literalB = clause
        # A literal is represented by an integer.
        # A positive literal takes its original boolean value,
        # a negative literal takes its opposite boolean value.
        boolA = attempt[abs(literalA)]
        boolB = attempt[abs(literalB)]
        if literalA < 0:
            boolA = not boolA
        if literalB < 0:
            boolB = not boolB

        if not bool(boolA or boolB):
            unsatisfiedClauses.append(clause)

    if len(unsatisfiedClauses) > 0:
        # Randomly return an unsatisfied clause
        return False, unsatisfiedClauses[randint(0, len(unsatisfiedClauses) - 1)]

    return True, None


def localSearch(attempt, clauses, numVariables):
    isSatisfied, unsatisfiedClause = testWithClauses(attempt, clauses)
    numTries = 1
    numTriesLimit = 2 * numVariables * numVariables
    while (not isSatisfied) and (numTries < numTriesLimit):
        # Randomly pick one var
        randSelectedVar = abs(unsatisfiedClause[randint(0, 1)])
        # Flip the value
        attempt[randSelectedVar] = not attempt[randSelectedVar]
        # Try again
        isSatisfied, unsatisfiedClause = testWithClauses(attempt, clauses)
        numTries += 1

    return isSatisfied


variables, clauses, numVariables, numClauses = readInputData('./2sat1')
reducedVars, reducedClauses, numReducedVars, numReducedClauses = reduction(
    clauses, variables)
while numReducedClauses < numClauses:
    numClauses = numReducedClauses
    numVariables = numReducedVars
    reducedVars, reducedClauses, numReducedVars, numReducedClauses = reduction(
        reducedClauses, reducedVars)

print(numReducedVars)
print(numReducedClauses)
isSatisfied = PapadimitriouRandom(reducedVars, reducedClauses, numReducedVars)
print(isSatisfied)
