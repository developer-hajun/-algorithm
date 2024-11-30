from collections import Counter
from itertools import combinations


def solution(orders, course):
    arr = []
    for i in orders:
        for j in course:
            if len(i) >= j:
                for k in combinations(i, j):
                    k = list(k)
                    k.sort()
                    arr.append(''.join(k))

    answer = [[] for _ in range(max(course) + 1)]
    max_count = [2] * (max(course) + 1)
    for key, count in Counter(arr).items():
        if max_count[len(key)] < count:
            max_count[len(key)] = count
            answer[len(key)] = []
        elif max_count[len(key)] > count:
            continue
        answer[len(key)].append(key)
    result = []
    for _ in course:
        result += answer[_]
    result.sort()
    return result



