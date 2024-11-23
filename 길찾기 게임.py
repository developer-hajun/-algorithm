
import heapq
from collections import defaultdict
import sys

sys.setrecursionlimit(10 ** 6)


def solution(nodeinfo):
    tree = defaultdict(list)
    level = set()
    for i in range(len(nodeinfo)):
        tree[nodeinfo[i][1]].append((nodeinfo[i][0], i + 1))
        level.add(nodeinfo[i][1])
    for i in level:
        tree[i].sort(reverse=True)
    level = sorted(list(level), reverse=True)
    TREE_DEPTH = len(level)
    answer1 = []
    answer2 = []

    def one(left, right, depth):
        if depth == TREE_DEPTH or len(tree[level[depth]]) <= 0 or not (left < tree[level[depth]][-1][0] < right):
            return
        x, index = tree[level[depth]].pop()
        answer1.append(index)
        one(left, x, depth + 1)
        one(x, right, depth + 1)
        answer2.append(index)

    one(-1, 100001, 0)
    return answer1, answer2



