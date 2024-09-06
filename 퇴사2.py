from collections import deque

n = int(input())
day_max = 0
sang = []
sang.append([])
for i in range(n):
    a,b = map(int,input().split())
    day_max = max(day_max,a)
    sang.append([a,b])
q = deque()
day = [0]*(n+1)

for i in range(1,n+1):
    day[i]=max(day[i],day[i-1])
    during_day = sang[i][0]
    value = sang[i][1]
    if i+during_day-1>n:
        continue
    day[i+during_day-1]=max(day[i+during_day-1],day[i-1]+value)
    print(day)
print(max(day))