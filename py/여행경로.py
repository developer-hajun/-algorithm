def solution(tickets):
    # visit을 통해 이미 쓴 여행권인지 확인해야 함
    visit = {}
    for i, j in tickets:
        if (i, j) in visit:
            visit[i, j] += 1
        else:
            visit[i, j] = 1

    dic = {}
    for a, b in tickets:
        dic[a] = []
        dic[b] = []
    for a, b in tickets:
        dic[a].append(b)
    for i in dic:
        dic[i].sort()

    def check():
        for i in visit:
            if visit[i] != 0:
                return False
        return True

    global answer
    answer = []

    def dfs(now, value):
        global answer
        if answer:
            return
        if check():
            answer = value
            return
        for i in dic[now]:
            if visit[now, i] == 0:
                continue
            visit[now, i] -= 1
            dfs(i, value + [i])
            if answer:
                return
            visit[now, i] += 1

    dfs("ICN", ["ICN"])
    return answer
