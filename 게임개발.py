n,m = map(int,input().split())
x,y,dir = map(int,input().split())
maps=[]
for i in range(n):
    maps.append(list(map(int,input().split())))
move = [[1,0],[0,-1],[-1,0],[0,1]]
maps[y][x]=2
count=1
while True:
    stop = False
    for i in range(5):
        if i==4:
            stop = True;
            break;
        dir+=1
        if dir==4:
            dir=0
        nx = x + move[dir][1]
        ny = y + move[dir][0]
        if nx<0 or nx>m-1 or ny<0 or ny>n-1:
            continue
        if maps[ny][nx]==0:
            maps[ny][nx]=2
            count+=1
            x = nx
            y = ny
            break
        else:
            continue
    if stop:
        break

print(count)