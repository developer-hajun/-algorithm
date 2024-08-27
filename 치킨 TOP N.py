import sys


def marge_sort(start,mid,end):
    if (end-start)>(n/h):
        return
    print(start,end)
    i,j,k = start,mid+1,start
    while i<=mid and j<=end:
        if value[i]<=value[j]:
            answer[k] = value[i]
            k+=1
            i+=1
            continue
        answer[k]=value[j]
        k+=1
        j+=1
    if(i>mid):
        for l in range(j,end+1):
            answer[k]=value[l]
            k+=1
    else:
        for l in range(i,mid+1):
            answer[k]=value[l]
            k+=1
    for l in range(start,end+1):
        value[l]=answer[l]
def chicken(start,end):
    if start==end:
        return
    mid = (start+end)//2
    chicken(start,mid)
    chicken(mid+1,end)
    marge_sort(start,mid,end)

n = int(input())
value = list(map(int,input().split()))
answer=[0]*n
h = int(input())
chicken(0,n-1)
for _ in answer:
    print(_,end=' ')
