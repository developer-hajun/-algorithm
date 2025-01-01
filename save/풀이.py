from collections import deque

n = int(input())

sang = []
sang.append([])
for i in range(n):
    a,b = map(int,input().split())
    sang.append([a,b])

q = deque()

day = [0]*(n+2)

def bosu(a,b):
    global day
    for i in range(1,n+1):
        if i<a or i+sang[i][0]>n+1: #현재 날짜보다 그전에 의뢰이거나,의뢰를 받았을때 n일을 넘으면 패쓰
            continue
        else:
            if b+sang[i][1] <= day[i+sang[i][0]]: #내가 상담을 함으로써 받을수 있는 보수보다, 다른 상담으로 받을수 있는 액수가 더 클때
                continue
            else:
                day[i+sang[i][0]] = b+sang[i][1]
                bosu(i+sang[i][0],b+sang[i][1])

bosu(1,0)#상담받을수 있는날짜,현재보수
print(max(day))