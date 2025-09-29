n,m = map(int,input().split())
a = set(map(int,input().split()))
b = set(map(int,input().split()))
now = list(a.difference(b))
if now:
    print(len(now))
    now.sort()
    for i in now:
        print(i,end=' ')
else:
    print(0)