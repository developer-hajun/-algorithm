from itertools import combinations


def solution(relation):
    arr = []
    for i in range(1, len(relation[0]) + 1):
        arr.extend(combinations(range(len(relation[0])), i))
    # 모든 경우의 수
    arr2 = []
    for i in arr:
        now = [tuple(r[num] for num in i) for r in relation]
        if len(set(now)) == len(relation):
            arr2.append(i)

    minimize = set(arr2)
    unique_len = len(arr2)
    for i in range(unique_len):
        for j in range(i + 1, unique_len):
            if len(arr2[i]) == len(set(arr2[j]).intersection(arr2[i])):
                minimize.discard(arr2[j])
    return len(minimize)


