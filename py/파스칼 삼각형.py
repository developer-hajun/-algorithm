from collections import deque

r,c,w = map(int,input().split())

arr = [[1]]
for i in range(r+w):
    new_arr = []
    new_arr.append(arr[-1][0])
    for j in range(1,len(arr[-1])):
        new_arr.append(arr[-1][j]+arr[-1][j-1])
    new_arr.append(arr[-1][-1])
    arr.append(new_arr)

queue=deque()
queue.append([r-1,c-1,0])

visit = [[0]*100 for _ in range(100)]


answer = 0
while queue:
    y,x,h = queue.popleft()
    if h==w:
        break
    if visit[y][x]==1:
        continue
    visit[y][x]=1
    answer += arr[y][x]
    queue.append([y+1,x,h+1])
    queue.append([y+1,x+1,h+1])
print(answer)