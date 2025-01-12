

from collections import deque
s = 1
while True:
    n = int(input())
    if n==0:
        break
    matrix = [list(map(int,input().split())) for _ in range(n)]
    value = [[99999]*n for _ in range(n)]
    value[0][0]=matrix[0][0]
    queue=deque([[0,0,value[0][0]]])
    while queue:
        y,x,count = queue.popleft()
        for ny,nx in (y-1,x),(y+1,x),(y,x-1),(y,x+1):
            if 0<=ny<n and 0<=nx<n and value[ny][nx]>count+matrix[ny][nx]:
                value[ny][nx]=count+matrix[ny][nx]
                queue.append([ny,nx,count+matrix[ny][nx]])
    print("Problem "+str(s)+": "+ str(value[-1][-1]))
    s+=1