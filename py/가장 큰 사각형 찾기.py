def solution(board):
    board = [[0]*len(board[0])]+board
    for y in range(len(board)):
        board[y]=[0]+board[y]
    answer = 0
    for y in range(1,len(board)):
        for x in range(1,len(board[0])):
            if board[y][x]==0 : continue
            if board[y-1][x]!=0 and board[y][x-1]!=0 and board[y-1][x-1]!=0:
                board[y][x] = min(board[y-1][x],board[y][x-1],board[y-1][x-1])+1
            else:
                board[y][x] = 1
            answer = max(answer,board[y][x])
    return answer*answer