def solution(n, now, cmd):
    answer = {i: [None if i - 1 < 0 else i - 1, None if i + 1 >= n else i + 1, 'O'] for i in range(n)}
    stack = []
    for command in cmd:
        if command == "C":
            left, right, live = answer[now]
            answer[now][2] = 'X'
            stack.append([left, now, right])
            if right == None:
                now = left
            else:
                now = right

            if left == None:
                answer[right][0] = None
            elif right == None:
                answer[left][1] = None
            else:
                answer[right][0] = left
                answer[left][1] = right
        elif command == "Z":
            left, cur, right = stack.pop()
            answer[cur][2] = 'O'
            if left == None:
                answer[right][0] = cur
            elif right == None:
                answer[left][1] = cur
            else:
                answer[right][0] = cur
                answer[left][1] = cur
        else:
            com, move = command.split()
            move = int(move)
            if com == "U":
                for i in range(move):
                    now = answer[now][0]
            elif com == "D":
                for i in range(move):
                    now = answer[now][1]
    return ''.join(answer[i][2] for i in range(n))