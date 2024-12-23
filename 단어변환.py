from collections import deque
def solution(begin, target, words):
    if target not in words:
        return 0
    answer = 0
    q = deque()
    q.append([begin, 0])
    V = [ 0 for i in range(len(words))]
    while q:
        now,count = q.popleft()
        if now==target:
            return count
        for w in range(len(words)):
            if V[w]:
                continue
            word = words[w]
            count2 = 0
            for i in range(len(now)):
                if now[i]!=word[i]:
                    count2+=1
            if count2==1:
                q.append([words[w],count+1])
                V[w]=1