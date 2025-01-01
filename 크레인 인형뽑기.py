def solution(board, moves):
    stack = []
    les = len(board[0])
    answer = 0
    for pick in moves:
        pick = pick - 1
        check = True
        for i in range(les):
            if board[i][pick] != 0:
                stack.append(board[i][pick])
                check = False
                board[i][pick] = 0
                break
        if check:
            continue
        while len(stack) > 1 and stack[-1] == stack[-2]:
            stack.pop()
            stack.pop()
            answer += 2
    return answer
