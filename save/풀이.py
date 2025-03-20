def solution(info, query):
    data = dict()
    for a in ['cpp', 'java', 'python', '-']:
        for b in ['backend', 'frontend', '-']:
            for c in ['junior', 'senior', '-']:
                for d in ['chicken', 'pizza', '-']:
                    data.setdefault((a, b, c, d), list())
    for i in info:
        i = i.split()
        for a in [i[0], '-']:
            for b in [i[1], '-']:
                for c in [i[2], '-']:
                    for d in [i[3], '-']:
                        data[(a, b, c, d)].append(int(i[4]))
    for k in data:
        data[k].sort()
    answer = []
    for i in query:
        i = i.split()
        scores = data[(i[0], i[2], i[4], i[6])]
        wanted = int(i[7])

        start, end = 0, len(scores)
        while start < end:
            mid = (start + end) // 2
            if scores[mid] >= wanted:
                end = mid
            else:
                start = mid + 1
        answer.append(len(scores) - start)
    return answer
