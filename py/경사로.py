

n,l = map(int,input().split())

matrix = [list(map(int,input().split())) for i in range(n)]


def check(line):
    global q
    visit = [False] * n
    for i in range(n-1):
        if line[i]==line[i+1]:
            continue
        if abs(line[i]-line[i+1])>1:
            return False
        elif line[i]>line[i+1]:
            value = line[i+1]
            for now in range(i+1,i+l+1):
                if now>=n:
                    return False
                if line[now]!=value:
                    return False
                if visit[now]:
                    return False
                visit[now]=True
        else:
            value = line[i]
            for now in range(i,i-l,-1):
                if now<0:
                    return False
                if line[now]!=value:
                    return False
                if visit[now]:
                    return False
                visit[now]=True
    return True
answer =0

for q in range(n):
    if check(matrix[q]):
        answer+=1
for q in range(n):
    if check([matrix[a][q] for a in range(n)]):
        answer+=1
print(answer)