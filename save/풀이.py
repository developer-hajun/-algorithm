import re
from itertools import combinations
from collections import Counter


def solution(user_id, banned_id):
    regex_patterns = [p.replace('*', '.') for p in banned_id]
    dic = {j: set() for j in regex_patterns}

    for i in user_id:
        for j in regex_patterns:
            if len(i) == len(j) and re.match(j, i):
                dic[j].add(i)

    count_regex = {a: b for a, b in Counter(regex_patterns).items()}
    global lens
    lens = len(regex_patterns)
    arr = set()

    def dfs(now, value):
        print(now, value)
        if now == lens:
            if len(value) == len(regex_patterns):
                arr.add(tuple(sorted(value)))
            return
        regex = regex_patterns[now]
        for _ in combinations(dic[regex], count_regex[regex]):
            dfs(now + count_regex[regex], set().union(value, _))

    dfs(0, set())
    print(arr)
    return len(arr)

