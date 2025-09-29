n,m = map(int,input().split())

graph = [0]*(n+1)

qus = []
for i in range(n+1):
    line=[]
    qus.append(line)

for i in range(m):
    a,b = map(int,input().split())
    qus[a].append(b)
    graph[b]+=1

perfect = [0]*(n+1)
count = 0
while count<n:
    for i in range(1,n+1):
        if graph[i]!=0:
            continue
        count+=1
        print(i,end=' ')
        for _ in qus[i]:
            graph[_]-=1
        graph[i]-=1
        break