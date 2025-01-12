#        -
# -      -
# -      -
# -    - -

h,w = map(int,input().split())
matrix = [[0]*w for _ in range(h)]

now = 0
for i in list(map(int,input().split())):
    for y in range(h-1,h-i-1,-1):
        matrix[y][now]=1
    now+=1
answer = 0
for y in range(h-1,-1,-1):
    for x in range(w):
        if matrix[y][x]!=0:
            continue
        arr =[x]
        count,ch = 1,True
        while True:
            if x-count<0:
                ch=False
                break
            if matrix[y][x-count]==1:
                break
            arr.append(x-count)
            count+=1
        count=1
        while True:
            if x+count>=w:
                ch=False
                break
            if matrix[y][x+count]==1:
                break
            arr.append(x+count)
            count+=1
        inv =-1
        if ch:
            inv=2
            answer+=len(arr)
        for i in arr:
            matrix[y][i]=inv
print(answer)

