import sys


def check():
    for i in range(n):
        now = i
        for j in range(h):
            if board[j][now]:
                now +=1
            elif now>0 and board[j][now-1]:
                now -=1
        if now!=i:
            return False
    return True

def find(cnt,y,x):
    global answer
    if check():
        answer = min(answer,cnt)
        return
    if cnt==3 or answer<=cnt:
        return
    for i in range(y,h):
        if i==y:
            now = x
        else:
            now = 0
        for j in range(now,n-1):
            if not board[i][j] and not board[i][j+1]:
                if j>0 and board[i][j-1]:
                    continue
                board[i][j]=1
                find(cnt+1,i,j)
                board[i][j]=0



n,m,h = map(int,input().split())
board = [[0]*n for i in range(h)]
for i in range(m):
    a,b = map(int,input().split())
    board[a-1][b-1]=1
answer = 4
find(0,0,0)
if answer>3:
    print(-1)
    sys.exit()
print(answer)