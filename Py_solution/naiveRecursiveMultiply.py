
def naiveRecursiveMultiply(x, y):
    lenX = len(x)
    lenY = len(y)
    # Give the 2 integers the same length
    paddedLen = max(lenX, lenY)
    if (paddedLen == 1):
        return str(int(x[0]) * int(y[0]))

    # Pad with 0s to their left to change them to even numbers
    if (paddedLen > 1 and paddedLen & 1):
        paddedLen += 1

    x = x.rjust(paddedLen, '0')
    y = y.rjust(paddedLen, '0')

    halfLen = int(paddedLen / 2)

    # Divide x: a b
    # Divide y: c d
    leftX, rightX = x[:halfLen], x[halfLen:]
    leftY, rightY = y[:halfLen], y[halfLen:]

    # Compute: ac, bd, ad, bc
    leftXLeftY = naiveRecursiveMultiply(leftX, leftY)
    rightXRightY = naiveRecursiveMultiply(rightX, rightY)
    leftXRightY = naiveRecursiveMultiply(leftX, rightY)
    rightXLeftY = naiveRecursiveMultiply(rightX, leftY)
    # Compute: ad + bc
    leftXRightYPlusRightXLeftY = int(leftXRightY) + int(rightXLeftY)

    # x * y = 10 ^ n * ac + 10 ^ (n / 2) * (ad + bc) + bd
    partI = int(leftXLeftY.ljust(paddedLen + len(leftXLeftY), '0'))
    partII = int(str(leftXRightYPlusRightXLeftY).ljust(
        halfLen + len(str(leftXRightYPlusRightXLeftY)), '0'))
    partIII = int(rightXRightY)

    return str(partI + partII + partIII)


print(naiveRecursiveMultiply('3141592653589793238462643383279502884197169399375105820974944592',
                             '2718281828459045235360287471352662497757247093699959574966967627'))
print(3141592653589793238462643383279502884197169399375105820974944592 *
      2718281828459045235360287471352662497757247093699959574966967627)
