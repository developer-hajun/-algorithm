from collections import deque
from math import ceil, log


def segment(left,right,i):
    if left==right:
        segment_tree[i]=nums[left]
        return segment_tree[i]
    mid = (left +right)//2
    segment_tree[i] =segment(left,mid,i*2) + segment(mid+1,right,i*2+1)
    return segment_tree[i]

def segment_update(start,end,node,idx,now_val,before_value):
    if start>idx or idx>end:
        return
    if start==end:
        segment_tree[node]=now_val
        return
    mid = (start+end)//2
    segment_tree[node] = segment_tree[node]-before_value+now_val
    segment_update(start,mid,node*2,idx,now_val,before_value)
    segment_update(mid+1,end, node * 2+1, idx, now_val,before_value)

def segment_sum(start,end,idx,left,right):
    if left > end or right < start:
        return 0
        # 범위 안에 있는 경우
    if left <= start and right >= end:
        return segment_tree[idx]
        # 그렇지 않다면 두 부분으로 나누어 합을 구하기
    mid = (start + end) // 2
    # start와 end가 변하면서 구간 합인 부분을 더해준다고 생각하면 된다.
    return segment_sum(start, mid, idx * 2, left, right) + segment_sum(mid + 1, end, idx * 2 + 1, left, right)
n,m,k = map(int,input().split())

nums = [int(input()) for _ in range(n)]
segment_tree = {}
segment(0,n-1,1)

for i in range(m+k):
    a,b,c= map(int,input().split())
    if a==1:
        segment_update(0,n-1,1,b-1,c,nums[b-1])
        nums[b-1]=c
    else:
        print(segment_sum(0,n-1,1,b-1,c-1))
