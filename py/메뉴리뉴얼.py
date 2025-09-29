from itertools import product, combinations, permutations


def solution(orders, course):
    dic = {}
    for i in orders:
        for j in course:
            if len(i) >= j:
                for k in combinations(i, j):
                    k = sorted(list(k))
                    k = ''.join(list(k))
                    if k in dic:
                        dic[k] += 1
                    else:
                        dic[k] = 1
    find = {}
    for i in course:
        find[i] = []
    for i in dic:
        if dic[i] >= 2:
            find[len(i)].append([i, dic[i]])
    for i in find:
        find[i].sort(key=lambda x: x[1], reverse=True)
    answer = []
    for a in course:
        ans = []
        k = -1
        for b, c in find[a]:
            if not ans:
                ans.append(b)
                k = c
            elif k == c:
                ans.append(b)
        answer.extend(ans)
    answer.sort()
    return answer

