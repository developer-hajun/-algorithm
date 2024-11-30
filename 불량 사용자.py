import re
from itertools import combinations
from collections import Counter


def solution(user_id, banned_id):
    banned_id.sort()
    regex_patterns = [p.replace('*', '.') for p in banned_id]
    dic = {j: set() for j in regex_patterns}

    for i in user_id:
        for j in regex_patterns:
            if len(i) == len(j) and re.match(j, i):
                dic[j].add(i)

    count_regex = Counter(regex_patterns)
    lens = len(regex_patterns)
    arr = set()

    def dfs(now, value):
        if now == lens:
            if len(value) == lens:
                arr.add(tuple(sorted(value)))
            return
        regex = regex_patterns[now]
        for comb in combinations(dic[regex], count_regex[regex]):
            if len(set(comb)) == len(comb):
                dfs(now + count_regex[regex], value.union(set(comb)))

    dfs(0, set())
    return len(arr)
