n = int(input())

matrix = [[0] * n for _ in range(n)]
student = []
for i in range((n ** 2) + 1):
    line = []
    student.append(line)


def input_student():
    ins = list(map(int, input().split()))
    return ins[0], ins[1:]


def find_like_student():
    seat = {}
    for num in like_student:
        if not student[num]:
            continue
        y, x = student[num]
        for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
            if 0 <= nx < n and 0 <= ny < n and matrix[ny][nx] == 0:
                if (ny, nx) in seat:
                    seat[ny, nx] += 1
                else:
                    seat[ny, nx] = 1
    seat = [k for k, v in seat.items() if max(seat.values()) == v]
    return seat


def not_find_friend():
    value = -1
    se = []
    for y in range(n):
        for x in range(n):
            if matrix[y][x]!=0:
                continue
            count = 0
            for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
                if 0 <= nx < n and 0 <= ny < n and matrix[ny][nx] == 0:
                    count += 1
            if count > value:
                value = count
                se = [y, x]
    return se

def find_friend():
    value = -1
    se = []
    for y,x in find_friend_seat:
        count = 0
        for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
            if 0 <= nx < n and 0 <= ny < n and matrix[ny][nx] == 0:
                count += 1
        if count > value:
            value = count
            se = [[y,x]]
        elif count==value:
            se.append([y,x])
    if len(se)==1:
        return se[0]
    else:
        se.sort(key = lambda x :(x[0],x[1]))
        return se[0]



satisfaction = []
for i in range(n ** 2):
    now_student, like_student = input_student()
    satisfaction.append([now_student, like_student])
    find_friend_seat = find_like_student()
    if find_friend_seat:
        if len(find_friend_seat) == 1:
            y, x = find_friend_seat[0]
            matrix[y][x] = now_student
            student[now_student] = [y, x]
        else:
            y, x = find_friend()
            matrix[y][x] = now_student
            student[now_student] = [y, x]
    else:
        y, x = not_find_friend()
        matrix[y][x] = now_student
        student[now_student] = [y, x]


answer = 0
for now_student, like_student in satisfaction:
    y,x = student[now_student]
    count = 0
    for ny, nx in [y + 1, x], [y - 1, x], [y, x + 1], [y, x - 1]:
        if 0 <= nx < n and 0 <= ny < n and matrix[ny][nx] in like_student:
            count+=1
    if count==1:
        answer+=1
    elif count==2:
        answer+=10
    elif count==3:
        answer+=100
    elif count==4:
        answer+=1000
print(answer)