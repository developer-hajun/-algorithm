from collections import deque


def solution(n, t, m, timetable):
    bus = []
    cru = []
    for i in range(540, 540 + t * n, t):
        bus.append(i)
    for i in timetable:
        a, b = i.split(':')
        cru.append(int(a) * 60 + int(b))
    cru.sort(reverse=True)
    cru = deque(cru)
    last = []

    for i in bus[:-1]:
        count = 0
        while cru and cru[-1] <= i and count < m:
            count += 1
            cru.pop()
    while cru and bus[-1] < cru[0]:
        cru.popleft()

    value = 0
    if len(cru) < m:
        value = bus[-1]
    else:
        value = cru[-m] - 1

    hour = str(value // 60)
    if len(hour) == 1:
        hour = '0' + hour
    mins = str(value % 60)
    if len(mins) == 1:
        mins = '0' + mins

    return hour + ":" + mins



