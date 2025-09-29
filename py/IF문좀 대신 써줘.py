import sys
input = sys.stdin.readline
n,m = map(int,input().split())

find=[]
for _ in range(n):
    a,b = input().split()
    find.append([a,int(b)])

for _ in range(m):
    st=0
    end=n
    now = int(input())
    while (end-st)>0:
        mid = (st+end)//2
        if find[mid][1]<now:
            st=mid+1
        else:
            end=mid
    print(find[st][0])