def solution(m, n, board):
    for i in range(len(board)):
        board[i] = list(board[i].strip())
    count = 0
    while True:
        ch = True
        now = set()
        for i in range(len(board) - 1):
            for j in range(len(board[0]) - 1):
                if board[i][j] == '-':
                    continue
                if board[i][j] == board[i][j + 1] == board[i + 1][j] == board[i + 1][j + 1]:
                    now.add((i, j))
                    now.add((i + 1, j))
                    now.add((i, j + 1))
                    now.add((i + 1, j + 1))
                    ch = False

        k = set()
        for i, j in now:
            board[i][j] = '-'
            k.add(j)
        count += len(now)
        # 삭제할 원소 찾고 삭제하기
        for x in k:  # 삭제한 원소들의 x축
            for y in range(len(board) - 2, -1, -1):
                if board[y][x] == '-':
                    continue
                move_y = y
                while move_y != len(board) - 1 and board[move_y + 1][x] == '-':
                    board[move_y][x], board[move_y + 1][x] = board[move_y + 1][x], board[move_y][x]
                    move_y += 1
        if ch:
            break
        # 옮기기
    return count
