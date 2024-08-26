import copy
import heapq
import sys
from collections import deque
from itertools import permutations


#최단 거리 찾기
def bfs(start):
    queue = deque()
    re = [[0]*a for _ in range(b)]
    queue.append([start[0],start[1]])
    re[start[0]][start[1]]=1
    while queue:
        y,x=queue.popleft()
        for next_y,next_x in [y-1,x],[y+1,x],[y,x+1],[y,x-1]:
            if 0<=next_y<b and 0<=next_x<a and re[next_y][next_x]==0 and matrix[next_y][next_x]!='x':
                re[next_y][next_x]=re[y][x]+1
                queue.append([next_y,next_x])
    return re

while True:
    a,b = map(int,input().split())
    if a==0 and b==0:
        break
    now = [] #시작 위치
    dirty = []
    go_dirty=[]
    matrix=[] #맵
    for i in range(b):
        line = list(input().strip())
        matrix.append(line)
        for j in range(a):
            if line[j]=='o':
                now=[i,j]
            if line[j]=='*':
                dirty.append([i,j])
   #첫번쨰 방문 까지의 시간
    dist = bfs(now)
    for y,x in dirty:
        go_dirty.append(dist[y][x]-1)
    if go_dirty.count(-1):
        print(-1)
        continue

    next_find = [[0]*len(dirty)for _ in range(len(dirty))]
    for i in range(len(dirty)):
        dist = bfs(dirty[i])
        for j in range(i+1,len(dirty)):
            next_find[i][j] = dist[dirty[j][0]][dirty[j][1]]-1
            next_find[j][i] = next_find[i][j]

    answer = 99999
    how = [i for i in range(0, len(dirty))]
    for i in permutations(how, len(dirty)):
        value = go_dirty[i[0]]
        for j in range(1, len(i)):
            value += next_find[i[j - 1]][i[j]]
        answer = min(answer, value)
    print(answer)