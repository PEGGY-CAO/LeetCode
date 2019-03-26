# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")

from extratypes import Tree  # library with types used in the task


def solution(T):
    # write your code in Python 3.6
    if T == None:
        return 0

    # initial result
    result = 0

    # recursively find visible node
    # store a maxValue for each path to the node
    maxValue = T.x
    result = dfs(result, maxValue, T)

    return result


def dfs(passInRes, passInMax, Treenode):
    if Treenode == None:
        return passInRes

    # corner case
    if passInMax <= Treenode.x:
        passInRes = passInRes + 1
        # update new max value
        passInMax = max(passInMax, Treenode.x)

    passInRes = dfs(passInRes, passInMax, Treenode.l)
    passInRes = dfs(passInRes, passInMax, Treenode.r)

    return passInRes