import math
import sys
from collections import deque

def find(a,b,n,h):
    if h==0:
        return str(matrix[a][b])
    value = n//2
    q=find(a,b,value,h-1)
    w=find(a,b+value,value,h-1)
    e=find(a+value,b,value,h-1)
    r=find(a+value,b+value,value,h-1)
    if [q,w,e,r]==['1','1','1','1']:
        return '1'
    elif [q,w,e,r]==['0','0','0','0']:
        return '0'
    else:
        return '('+str(q)+str(w)+str(e)+str(r)+')'


n = int(input())
matrix=[list(map(int,input().strip())) for i in range(n)]
print(find(0,0,n,math.log2(n)))

