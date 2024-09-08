
answer = 26

def ch(y,x,ey,ex):
    for i in range(y,ey):
        for j in range(x,ex):
            if not matrix[i][j]:
                return False
    return True
def change(y,x,ey,ex,w):
    count =0
    for i in range(y,ey+1):
        for j in range(x,ex+1):
            matrix[i][j]=w
            count+=1
    return count

def dfs(y,x, count,paper):
    global answer
    if count == 0:
        answer = min(answer, 25 - sum(paper))
        return
    for ny in range(y, 10):
        for nx in range(10):
            if matrix[ny][nx]:
                for n in range(0,5):
                    now_y,now_x = ny+n , nx+n
                    if now_y>=10 or now_x>=10:
                        break
                    if not ch(ny,nx,now_y+1,now_x+1):
                        break
                    if paper[n]==0:
                        continue
                    cc = change(ny,nx,now_y,now_x,0)
                    paper[n]-=1
                    dfs(ny,nx,count-cc,paper)
                    paper[n]+=1
                    change(ny, nx, now_y, now_x, 1)
                return


matrix = [list(map(int, input().split())) for _ in range(10)]
white = 0
for i in range(10):
    white += sum(matrix[i])
dfs(0, 0,white,[5,5,5,5,5])
if answer==26:
    print(-1)
else:
    print(answer)