from collections import deque


def getSize(mats, park, i, j):
    ans = -1
    for size in mats:
        sizeable = size
        for x in range(i, i + size):
            if sizeable == -1:
                break
            for y in range(j, j + size):
                if x >= len(park) or y >= len(park[0]):
                    sizeable = -1
                    break
                if park[x][y] != '-1':
                    sizeable = -1
                    break
        ans = max(ans, sizeable)
    return ans


def solution(mats, park):
    answer = -1
    matsable = [[] for i in range(len(park))]

    for i, i_values in enumerate(park):
        for j in [j for j, value in enumerate(i_values) if value == "-1"]:
            matsable[i].append(j)

    for i in range(len(matsable)):
        for j in matsable[i]:
            answer = max(answer, getSize(mats, park, i, j))
    return answer


