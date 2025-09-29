import sys
n = int(sys.stdin.readline())
matrix = [[]]
for _ in range(n):
    matrix.append([0] + list(map(int, sys.stdin.readline().split())))


def max_min():
    count = [0,0,0,0,0]
    find = [[0]*(n+1) for _ in range(n+1)]
    for i in range(d1+1):
        find[y-i][x+i] = 5
        find[y+d2-i][x+d2+i] = 5
    for i in range(d2+1):
        find[y + i][x + i] = 5
        find[y-d1+i][x+d1+i]=5
    for i in range(x+1,x+d1+d2):
        H= False
        for j in range(n+1):
            if find[j][i]==5:
                if H:
                    break
                else:
                    H=True
            else:
                if H:
                    find[j][i]=5

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if j <= x + d1 and i < y and find[i][j] != 5:
                count[0] += matrix[i][j]
                find[i][j]=1
            elif j < x + d2 and y <= i and find[i][j] != 5:
                count[1] += matrix[i][j]
                find[i][j]=2
            elif x + d1 <= j and i <= y - d1 + d2 and find[i][j] != 5:
                count[2] += matrix[i][j]
                find[i][j] = 3
            elif x + d2 <= j and y - d1 + d2 < i and find[i][j] != 5:
                count[3] += matrix[i][j]
                find[i][j] = 4
            elif find[i][j] == 5:
                count[4] += matrix[i][j]
    return max(count)-min(count)

answer = 999999
for y in range(1,n+1):
    for x in range(1,n+1):
        for d1 in range(1,n+1):
            for d2 in range(1,n+1):
                if 1<=x+d1+d2<=n and 1<=y-d1<y+d2<=n:
                    answer = min(answer,max_min())
print(answer)
