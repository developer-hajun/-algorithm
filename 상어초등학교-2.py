n = int(input())
matrix = [[0]*n for _ in range(n)]

arr = []
for i in range(n**2):
    a,b,c,d,e =map(int,input().split())
    arr.append([a,[b,c,d,e]])

def find(like):
    findy,findx = -1,-1
    max_like=0
    max_zero =0
    ff = []
    for y in range(n):
        for x in range(n):
            if matrix[y][x] != 0:
                continue
            zero = 0
            likes = 0
            for ny,nx in [y,x-1],[y,x+1],[y+1,x],[y-1,x]:
                if 0<=nx<n and 0<=ny<n:
                    if matrix[ny][nx]==0:
                        zero+=1
                    elif matrix[ny][nx] in like:
                        likes+=1
            ff.append([likes,zero,y,x])
    ff.sort(key=lambda x:(-x[0],-x[1],x[2],x[3]))
    return ff[0][2],ff[0][3]


for now,like in arr:
    y,x= find(like)
    matrix[y][x] = now
arr.sort()


answer = 0

kk=[0,1,10,100,1000]
for y in range(n):
    for x in range(n):
        finds = matrix[y][x]
        like = arr[finds-1][1]
        count = 0
        for ny, nx in [y, x - 1], [y, x + 1], [y + 1, x], [y - 1, x]:
            if 0 <= nx < n and 0 <= ny < n:
                if matrix[ny][nx] in like:
                    count+=1
        answer += kk[count]
print(answer)

