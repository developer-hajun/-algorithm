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
pro = 0
for i in range(n):
    pro = max(pro,day[i])
    value = sang[i][1]
    if i+sang[i][0]>n:
        continue
    day[i+sang[i][0]]=max(day[i+sang[i][0]],pro+value)
print(max(day))