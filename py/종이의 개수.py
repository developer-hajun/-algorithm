import math


def v(a,b,n,h):
    if h==0:
        return matrix[a][b]
    value=n//3
    q = v(a,b,value,h-1)
    w = v(a,b+value,value,h-1)
    e = v(a,b+(value*2),value,h-1)
    r = v(a+value,b,value,h-1)
    t = v(a + value, b+value, value, h - 1)
    y = v(a + value, b+(value*2), value, h - 1)
    u = v(a+(value*2), b, value,h-1)
    i = v(a+(value*2),b+value, value, h - 1)
    o = v(a+(value*2), b+(value*2),value,h-1)
    if [q,w,e,r,t,y,u,i,o] ==[0,0,0,0,0,0,0,0,0]:
        return 0
    elif [q,w,e,r,t,y,u,i,o] == [1,1,1,1,1,1,1,1,1]:
        return 1
    elif [q,w,e,r,t,y,u,i,o] == [-1,-1,-1,-1,-1,-1,-1,-1,-1]:
        return -1
    else:
        for _ in [q,w,e,r,t,y,u,i,o]:
            if _!=2:
                dic[_]+=1
        return 2


n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]
dic={-1:0,0:0,1:0}
q =v(0,0,n,round(math.log(n,3)))
if q!=2:
    dic[q]+=1
print(dic[-1])
print(dic[0])
print(dic[1])