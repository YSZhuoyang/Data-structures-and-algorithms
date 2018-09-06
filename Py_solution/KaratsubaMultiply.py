
def KaratsubaMultiply(x, y):
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

    # Compute ac
    leftXLeftY = KaratsubaMultiply(leftX, leftY)
    # Compute bd
    rightXRightY = KaratsubaMultiply(rightX, rightY)
    # Less recursive calls with Gau's trick:
    # ad + bc = (a + b)(c + d) - ac - bd
    leftXRightYPlusRightXLeftY = str(int(KaratsubaMultiply(str(int(
        leftX) + int(rightX)), str(int(leftY) + int(rightY)))) - int(leftXLeftY) - int(rightXRightY))

    # x * y = 10 ^ n * ac + 10 ^ (n / 2) * (ad + bc) + bd
    partI = int(leftXLeftY.ljust(paddedLen + len(leftXLeftY), '0'))
    partII = int(leftXRightYPlusRightXLeftY.ljust(
        halfLen + len(leftXRightYPlusRightXLeftY), '0'))
    partIII = int(rightXRightY)

    return str(partI + partII + partIII)


print(KaratsubaMultiply('3141592653589793238462643383279502884197169399375105820974944592',
                        '2718281828459045235360287471352662497757247093699959574966967627'))
print(3141592653589793238462643383279502884197169399375105820974944592 *
      2718281828459045235360287471352662497757247093699959574966967627)
