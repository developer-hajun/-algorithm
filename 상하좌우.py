n = int(input())
move = input().split()
x = 1
y = 1
dx = [-1,1,0,0]
dy = [0,0,-1,1]
move_type = ['L','R','U','D']

for i in move:
    index = move_type.index(i)
    nx = x+dx[index]
    ny = y+dy[index]
    if nx<1 or nx>5 or ny<1 or ny>5 :
        continue
    x= nx
    y= ny
print(str(y)+' '+str(x))