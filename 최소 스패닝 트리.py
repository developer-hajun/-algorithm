#v,e = map(int,input().split())

def find_parent(parent,x):
    if parent[x]!=x:
        parent[x]=find_parent(parent,parent[x])
    return parent[x]

def union_parent(a,b,x,y):
    if a<b:
        parent[a]=y
    else:
        parent[b]=x

edge = [[1,1,2],[1,2,5],[2,1,5],[2,0,3],[3,3,4],[4,0,4],[4,1,3],[7,3,5],[8,0,1],[9,4,5]]
# for j in range(e):
#     a,b,c=map(int,input().split())
#     edge.append([c,a,b])
parent=[ i for i in range(6)]
#edge.sort()
total_cost=0
edges = []
for i in range(10):
    cost,x,y = edge[i]
    a= find_parent(parent,x)
    b= find_parent(parent,y)
    if a!=b:
        union_parent(a,b,x,y)
        edges.append([x,y,cost])
print(edges)