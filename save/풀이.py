from collections import deque
from math import ceil, log


def segment(left,right,i):
    if left==right:
        segment_tree[i]=nums[left]
        return segment_tree[i]%1000000007
    mid = (left +right)//2
    segment_tree[i]=(segment(left,mid,i*2)*segment(mid+1,right,i*2+1))%1000000007
    return segment_tree[i]
def segment_gob(start,end,idx,left,right):
    if left>end or right<start:
        return 1
    if left<=start and right>=end:
        return segment_tree[idx]
    mid = (start+end)//2
    return segment_gob(start,mid,idx*2,left,right)*segment_gob(mid+1,end,idx*2+1,left,right)

def segment_update(start,end,node,idx,e_val,now_val):
    if start>idx or idx>end:
        return segment_tree[node]

    segment_tree[node] = segment_tree[node]/e_val*now_val
    if start!=end:
        mid = (start+end)//2
        segment_update(start,mid,node*2,idx,e_val,now_val)
        segment_update(mid+1,end,node*2+1,idx,e_val,now_val)

n,m,k = map(int,input().split())

nums = [int(input()) for _ in range(n)]
segment_tree = [0] + [0] * (pow(2, ceil(log(len(nums), 2)+1) + 1) - 1)
segment(0,n-1,1)
for _ in range(m+k):
    command,st,en = map(int,input().split())
    if command==1:
        now = nums[st-1]
        segment_update(0,n-1,1,st-1,now,en)
        print(segment_tree)
    if command==2:
        print((int(segment_gob(0,n-1,1,st-1,en))))



