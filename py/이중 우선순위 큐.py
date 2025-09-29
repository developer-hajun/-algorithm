import heapq

t = int(input())

for i in range(t):
    dict={}
    min_heap = []
    max_heap = []
    c = int(input())
    now =0
    for _ in range(c):
        a,b = input().split()
        if a=='I':
            heapq.heappush(min_heap,int(b))
            heapq.heappush(max_heap,-int(b))
            if int(b) in dict:
                dict[int(b)]+=1
            else:
                dict[int(b)]=1
        if a=='D':
            if int(b)==1:
                while True:
                    if not max_heap:
                        break
                    now = heapq.heappop(max_heap)
                    if dict[-now]!=0:
                        dict[-now]-=1
                        break
            else:
                while True:
                    if not min_heap:
                        break
                    now = heapq.heappop(min_heap)
                    if dict[now]!=0:
                        dict[now]-=1
                        break
    d=[]
    for _ in dict:
        if dict[_]!=0:
            d.append(_)
    if d:
        print(max(d),min(d))
    else:
        print("EMPTY")