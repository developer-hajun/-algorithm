import math

def find(a,b,n,h):
    if h==0:
        return matrix[a][b]
    q=find(a,b,n//2,h-1)
    w=find(a,b+n//2,n//2,h-1)
    e=find(a+n//2,b,n//2,h-1)
    r=find(a+n//2,b+n//2,n//2,h-1)
    if [q,w,e,r]==[1,1,1,1]:
        return 1
    elif [q,w,e,r]==[0,0,0,0]:
        return 0
    else:
        for _ in [q,w,e,r]:
            if _==1:
                dic["blue"]+=1
            elif _==0:
                dic["white"]+=1
        return 2


n= int(input())
matrix = [list(map(int,input().split())) for _ in range(n)]
dic = {"blue":0,"white":0}
re = find(0,0,n,math.log2(n))
if re==1:
    dic["blue"] += 1
elif re==0:
    dic["white"]+=1
print(dic["white"])
print(dic["blue"])