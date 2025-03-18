def solution(board, skill):
    new_board = [[0] * (len(board[0]) + 1) for _ in range(len(board) + 1)]

    for ty, r1, c1, r2, c2, degree in skill:
        if ty == 1:
            new_board[r1][c1] -= degree
            new_board[r2 + 1][c2 + 1] -= degree
            new_board[r1][c2 + 1] += degree
            new_board[r2 + 1][c1] += degree
        else:
            new_board[r1][c1] += degree
            new_board[r2 + 1][c2 + 1] += degree
            new_board[r1][c2 + 1] -= degree
            new_board[r2 + 1][c1] -= degree

    for x in range(len(new_board[0])):
        for y in range(1, len(new_board)):
            new_board[y][x] += new_board[y - 1][x]

    for y in range(len(new_board)):
        for x in range(1, len(new_board[0])):
            new_board[y][x] += new_board[y][x - 1]
    count = 0
    for y in range(len(new_board) - 1):
        for x in range(len(new_board[0]) - 1):
            if 0 < board[y][x] + new_board[y][x]:
                count += 1
    return count
