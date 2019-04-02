# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")

def solution(T):
    # write your code in Python 3.6

    length = len(T)
    # use binary search to find the partitian place such that max(left) < min(right)
    index = binarySearch(T, length)

    return index + 1


def binarySearch(arr, n):
    mid = 0 + (n - 1) // 2

    # print (max(arr[0 : mid]))

    i = 0;
    j = n

    while i < j:
        mid = i + (j - i) // 2
        if mid == i or mid == j:
            return mid

        if max(arr[0: mid]) >= min(arr[mid: n - 1]):
            # if mid > 0 and
            i = mid

        else:
            j = mid

    return mid


arr = [-3, -2, -4, 0, -5, -1, -3, 4, 5, 8, 6]
print(solution(arr))