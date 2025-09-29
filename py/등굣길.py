def solution(m, n, puddles):
    visit = [[0]*(m+1) for _ in range(n+1)]
    visit[1][1] = 1
    for y in range(1,n+1):
        for x in range(1,m+1):
            if [x,y] in puddles or [1,1]==[y,x]:
                continue
            visit[y][x]=(visit[y-1][x]+visit[y][x-1])%1000000007
    return visit[n][m]