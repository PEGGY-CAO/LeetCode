# Solve the problem that given an array of integers, return the smallest positive integer that is greater than 0


def solution(A):

    length = len(A)
    if length == 0:
        return 1
    A.sort()
    if A[length-1] <= 0:
        return 1

    # initial result
    result = 1
    index = binarySearch(A, length, 1)
    if A[index] < 1:
        return result
    else:
        # when A[index] == 1
        for i in range(index, length-2):
            if A[i+1] - A[i] <= 1:
                continue
            else:
                result = A[i] + 1

    return result


def binarySearch(arr, n, target):
    if arr[0] >= target:
        return 0
    if arr[n-1] <= target:
        return n-1

    i = 0; j = n; mid = 0
    while i < j:
        mid = i + (j - i) / 2
        if arr[mid] == target:
            return mid

        if arr[mid] > target:
            if mid > 0 and arr[mid - 1] < target:
                return mid-1
            j = mid
        else:
            if mid < n-1 and target < arr[mid+1]:
                return mid
            i = mid+1

    return mid


# drive code
A = [1, 4, 3, 10, 7]

print solution(A)
