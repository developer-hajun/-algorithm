now = input()
zero =0
one=0
for i in now:
    if i=='0':
        zero+=1
    else:
        one+=1
zero/=2
one/=2

visit = [0]*len(now)
st = 0
end=len(now)-1
while zero or one:
    if now[st]=='1' and one:
        visit[st]=1
        one-=1
    if now[end]=='0' and zero:
        visit[end]=1
        zero-=1
    st+=1
    end-=1
answer = []
for i in range(len(now)):
    if visit[i]==0:
        answer.append(now[i])
print(''.join(answer))