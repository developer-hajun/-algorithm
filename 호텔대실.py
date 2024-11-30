import heapq


def tt(t):
    t1, t2 = t.split(":")
    return int(t1) * 60 + int(t2)


def solution(book_time):
    for i in range(len(book_time)):
        book_time[i] = [tt(book_time[i][0]), tt(book_time[i][1])]
    book_time.sort()

    answer = []
    for i in book_time:
        ch = True
        for t in range(len(answer)):
            if answer[t] <= i[0]:
                answer[t] = i[1] + 10
                ch = False
                break
        if ch:
            answer.append(i[1] + 10)
    return len(answer)