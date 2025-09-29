import sys
from collections import deque

n = int(input())
m = int(input())
which = list(map(int, input().split()))
line =[0]*n
count=0

queue=deque()
for i in which:
  if 0<=i<n and line[i]==0:
    count += 1
    line[i]=1
  if 0<=i-1<n and line[i-1]==0:
    count+=1
    line[i-1]=1
  queue.append([i+1,i-2,2])

if count==n:
  print(1)
  sys.exit()
while queue:
  check = True
  right,left,cost=queue.popleft()
  if 0<=right<n and line[right]==0:
    check=False
    count+=1
    line[right]=1
  if 0<=left<n and line[left]==0:
    check = False
    count+=1
    line[left]=1
  if count==n:
    print(cost)
    break
  if check:
    continue
  queue.append([right+1,left-1,cost+1])
