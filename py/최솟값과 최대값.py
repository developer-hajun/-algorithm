from collections import deque
from math import ceil, log


def segment(left,right,i):
    if left==right:
        segment_tree[i]=[nums[left],nums[left]]
        return segment_tree[i]
    mid = (left +right)//2
    min_now,max_now=segment(left,mid,i*2)
    min_now2,max_now2=segment(mid+1,right,i*2+1)
    segment_tree[i]=[min(min_now,min_now2),max(max_now,max_now2)]
    return segment_tree[i]


def segment_min_max(start,end,idx,left,right):
    if left > end or right < start:
        return (10**9+1, 0)
        # 범위 안에 있는 경우
    if left <= start and right >= end:
        answer[0]= min(answer[0],segment_tree[idx][0])
        answer[1]= max(answer[1],segment_tree[idx][1])
        return
    mid = (start + end) // 2
    # start와 end가 변하면서 구간 합인 부분을 더해준다고 생각하면 된다.
    segment_min_max(start, mid, idx * 2, left, right)
    segment_min_max(mid + 1, end, idx * 2 + 1, left, right)
n,m= map(int,input().split())
nums = [int(input()) for _ in range(n)]
segment_tree = {}
segment(0,n-1,1)

for i in range(m):
    a,b = map(int,input().split())
    answer = [10**9+1, -1]
    segment_min_max(0, n - 1, 1, a-1, b-1)
    for _ in answer:
        print(_,end=' ')
    print()

