n = int(input())
matrix = [list(map(int, input().split())) for i in range(n)]
answer = 999999


def dfs(link, start, person):
    global answer
    if len(link) + len(start) == n:
        value = 0
        for i in link:
            for j in link:
                if i != j:
                    value += matrix[i][j]
        value2 = 0
        for i in start:
            for j in start:
                if i != j:
                    value2 += matrix[i][j]

        answer = min(answer, abs(value - value2))
    else:
        dfs(link + [person], start, person + 1)
        dfs(link, start + [person], person + 1)
dfs([],[],0)
print(answer)