from collections import deque

n = int(input())
day_max = 0
sang = []
for i in range(n):
    a,b = map(int,input().split())
    day_max = max(day_max,a)
    sang.append([a,b])
q = deque()
day = [0]*(n+1)
    value = sang[i][1]
        continue
print(max(day))