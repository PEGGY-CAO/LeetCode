# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")

def solution(N):
    # write your code in Python 3.6

    # construct a mapping array according to each digit
    mapping = {}

    while int(N) != 0:
        # print (N)
        digit = N % 10

        N = N // 10

        if digit in mapping:
            counts = mapping[digit]
            counts = counts + 1
            mapping[digit] = counts
        else:
            mapping[digit] = 1

    keys = mapping.keys()
    keyList = list(keys)
    keyList.sort(reverse=True)
    result = 0

    for key in keyList:
        nums = mapping[key]
        while nums != 0:
            result *= 10
            result += key

            nums = nums - 1

    return result


print solution(153324)