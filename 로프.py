import heapq
from itertools import combinations

n = int(input())
lope = []

for _ in range(n):
    heapq.heappush(lope, int(input()))
answer = -1

while lope:
    min = heapq.heappop(lope)
    answer = max(answer,min*(len(lope)+1))
print(answer)