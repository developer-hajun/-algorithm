def solution(cards):
    answer = []
    def dfs(one,two,visit,count,box,num):
        if count==len(cards):
            answer.append(one*two)
            return
        if box==1:
            nv = cards[num]-1
            if visit[nv]==0:
                visit[nv]=1
                dfs(one+1,two,visit,count+1,1,nv)
                visit[nv]=0
            else:
                for i in range(len(cards)):
                    if visit[i]==0:
                        visit[i]=1
                        dfs(one,1,visit,count+1,2,i)
                        visit[i]=0
        elif box==2:
            nv = cards[num]-1
            if visit[nv]==0:
                visit[nv]=2
                dfs(one,two+1,visit,count+1,2,nv)
                visit[nv]=0
            else:
                answer.append(one*two)
                return
    for i in range(len(cards)): #임의 상자 번호
        now = [0]*len(cards)
        now[i] = 1
        dfs(1,0,now,1,1,i)
    return max(answer)