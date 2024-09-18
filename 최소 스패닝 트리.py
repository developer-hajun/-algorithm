v,e = map(int,input().split())

def find_parent(parent,x):
    if parent[x]!=x:
        parent[x]=find_parent(parent,parent[x])
    return parent[x]

def union_parent(a,b,x,y):
    if a<b:
        parent[a]=y
    else:
        parent[b]=x

edge = []
for j in range(e):
    a,b,c=map(int,input().split())
    edge.append([c,a,b])
parent=[ i for i in range(v+1)]
edge.sort()
total_cost=0
for i in range(e):
    cost,x,y = edge[i]
    a= find_parent(parent,x)
    b= find_parent(parent,y)
    if a!=b:
        union_parent(a,b,x,y)
        total_cost +=cost
print(total_cost)