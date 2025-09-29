import math

t = int(input())

for i in range(t):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    dist = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

    if x1 == x2 and y1 == y2:
        if r1 == r2:
            print(-1)
        else:
            print(0)
    else:
        if r1 + r2 > dist > abs(r1 - r2):
            print(2)
        elif r1 + r2 == dist or abs(r1 - r2) == dist:
            print(1)
        else:
            print(0)