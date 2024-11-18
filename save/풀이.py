def solution(commands):
    arr = [["EMPTY"] * 51 for _ in range(51)]
    answer = []
    for i in commands:
        now = list(i.split())
        if now[0] == "UPDATE":
            if len(now) == 4:
                y, x = int(now[1]), int(now[2])
                while isinstance(arr[y][x], list):
                    y = arr[y][x][0]
                    x = arr[y][x][1]
                arr[y][x] = now[3]
            else:
                for y in range(1, 51):
                    for x in range(1, 51):
                        if arr[y][x] == now[1]:
                            arr[y][x] = now[2]
        elif now[0] == "MERGE":
            y1, x1, y2, x2 = int(now[1]), int(now[2]), int(now[3]), int(now[4])
            arr[y2][x2] = [y1, x1]
        elif now[0] == "UNMERGE":
            y, x = int(now[1]), int(now[2])
            cy, cx = int(now[1]), int(now[2])
            while isinstance(arr[y][x], list):
                ny = arr[y][x][0]
                nx = arr[y][x][1]
                arr[y][x] = "EMPTY"
                y = ny
                x = nx
            arr[cy][cx], arr[y][x] = arr[y][x], "EMPTY"
        else:
            y, x = int(now[1]), int(now[2])
            while isinstance(arr[y][x], list):
                y, x = arr[y][x]
            answer.append(arr[y][x])
    return answer
solution(["UPDATE 1 1 apple", "MERGE 1 1 2 2", "MERGE 2 2 3 3", "UNMERGE 1 1", "UNMERGE 2 2", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3"])