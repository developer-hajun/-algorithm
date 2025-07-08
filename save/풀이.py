import sys
sys.setrecursionlimit(10**6)

def solution(n, lighthouse):
    graph = [[] for _ in range(n)]
    for a, b in lighthouse:
        a -= 1
        b -= 1
        graph[a].append(b)
        graph[b].append(a)

    dp = [[0, 0] for _ in range(n)] 
    visited = [False] * n

    def dfs(node):
        visited[node] = True
        dp[node][0] = 0
        dp[node][1] = 1
        for neighbor in graph[node]:
            if not visited[neighbor]:
                dfs(neighbor)
                dp[node][0] += dp[neighbor][1]  
                dp[node][1] += min(dp[neighbor]) 
    dfs(0)
    for _ in dp:
        print(_)
    return min(dp[0]) 
solution(8	,[[1, 2], [1, 3], [1, 4], [1, 5], [5, 6], [5, 7], [5, 8]])