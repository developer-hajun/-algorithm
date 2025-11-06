from collections import deque
from math import ceil, log


def segment(left,right,i):
    if left==right:
        segment_tree[i]=[nums[left],left]
        return [segment_tree[i][0],segment_tree[i][1]]
    mid = (left +right)//2
    value , which =segment(left,mid,i*2)
    value2 , which2 = segment(mid+1,right,i*2+1)
    if value<value2:
        segment_tree[i]=[value,which]
    elif value>value2:
        segment_tree[i]=[value2,which2]
    else:
        segment_tree[i]=[value2,min(which,which2)]
    return [segment_tree[i][0],segment_tree[i][1]]

def segment_update(start,end,node,idx,now_val):
    if start>idx or idx>end:
        return
    if start==end:
        segment_tree[node]=[now_val,start]
        return segment_tree[node]
    mid = (start+end)//2
    segment_update(start,mid,node*2,idx,now_val)
    segment_update(mid+1,end, node * 2+1, idx, now_val)
    if segment_tree[node*2][0]<=segment_tree[node*2+1][0]:
        segment_tree[node]=[segment_tree[node*2][0],segment_tree[node*2][1]]
    else:
        segment_tree[node] = [segment_tree[node * 2+1][0], segment_tree[node * 2+1][1]]

def find_min_value(left,right,i):
    global mins


n = int(input())

nums = list(map(int,input().split()))
segment_tree = [0] + [0] * (pow(2, ceil(log(len(nums), 2)+1) + 1) - 1)
segment(0,n-1,1)
m = int(input())
for i in range(m):
    now = list(map(int,input().split()))
    if now[0]==2:
        print(segment_tree[1][1]+1)
    else:
        segment_update(0,n-1,1,now[1]-1,now[2])