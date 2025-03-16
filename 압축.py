def solution(msg):
    answer = {}
    num = 1
    for i in range(65, 91):
        answer[chr(i)] = num
        num += 1
    v = []

    w = msg[0]

    for c in msg[1:].strip():
        if w + c not in answer:
            v.append(answer[w])
            answer[w + c] = len(answer) + 1
            w = c
        else:
            w += c
    v.append(answer[w])
    return v

