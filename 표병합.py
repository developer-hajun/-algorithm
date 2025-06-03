def solution(commands):
    arr = [[None] * 51 for _ in range(51)]

    def update_1(command):
        update, r, c, value = command
        r, c = int(r), int(c)
        if isinstance(arr[r][c], list):
            r, c = arr[r][c]
        arr[r][c] = value

    def update_2(command):
        update, value1, value2 = command
        for y in range(len(arr)):
            for x in range(len(arr[0])):
                if arr[y][x] == value1:
                    arr[y][x] = value2

    def marge(command):
        marge, r1, c1, r2, c2 = command
        r1, c1, r2, c2 = int(r1), int(c1), int(r2), int(c2)
        if isinstance(arr[r1][c1], list):
            r1, c1 = arr[r1][c1]
        # 왼쪽그룹 부모찾기
        if isinstance(arr[r2][c2], list):
            r2, c2 = arr[r2][c2]
        if [r1, c1] == [r2, c2]:
            return
        # 오른쪽 그룹 부모찾기
        arr[r1][c1] = arr[r1][c1] if arr[r1][c1] else arr[r2][c2]
        # 값 설정해주기
        arr[r2][c2] = [r1, c1]
        # 오른쪽 부모 종속
        for y in range(len(arr)):
            for x in range(len(arr[0])):
                if arr[y][x] == [r2, c2]:
                    arr[y][x] = [r1, c1]
        # 오른쪽 자식 종속

    def unmarge(command):
        unmerge, r, c = command
        l, e = int(r), int(c)
        r, c = int(r), int(c)
        if isinstance(arr[r][c], list):
            r, c = arr[r][c]
        # 부모찾기

        value = arr[r][c]
        # 값 뽑아내기
        arr[r][c] = None
        for y in range(len(arr)):
            for x in range(len(arr[0])):
                if arr[y][x] == [r, c]:
                    arr[y][x] = None
        # 자식 부모 전부다 None처리
        arr[l][e] = value
        # 정해진 곳에 값넣기

    def pr(command):
        pr, r, c = command
        r, c = int(r), int(c)
        if isinstance(arr[r][c], list):
            r, c = arr[r][c]
        return arr[r][c]

    commands = [list(i.split()) for i in commands]
    answer = []
    for command in commands:
        if command[0] == "UPDATE":
            if len(command) == 4:
                update_1(command)
            else:
                update_2(command)
        elif command[0] == "MERGE":
            marge(command)
        elif command[0] == "UNMERGE":
            unmarge(command)
        elif command[0] == "PRINT":
            value = pr(command)
            answer.append(value if value else "EMPTY")

    return answer