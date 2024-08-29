n,m = map(int,input().split())
arr = [list(map(int,input().split())) for _ in range(n)]

def check(line):
    visit = [False]*n
    for i in range(0,n-1):
        if line[i]==line[i+1]:
            continue
        elif abs(line[i]-line[i+1])>1:
            return False
        elif line[i]>line[i+1]:
            now = line[i+1]
            for j in range(i+1,i+m+1):
                if j>=n:
                    return False
                if line[j]!=now:
                    return False
                elif visit[j]:
                    return False
                visit[j]=True
        else:
            now = line[i]
            for j in range(i,i-m,-1):
                if j<0:
                    return False
                if line[j]!=now:
                    return False
                elif visit[j]:
                    return False
                visit[j] = True
    return True

answer=0
for i in range(n):
    if check(arr[i]):
        answer+=1
for i in range(n):
    if check([arr[a][i] for a in range(n)]):
        answer+=1
print(answer)
