def solution(genres, plays):
    dic = {}
    for i in set(genres):
        dic[i] = [0, []]

    for i in range(len(plays)):
        gen = genres[i]
        play = plays[i]
        dic[gen][0] += play
        dic[gen][1].append([play, i])
    arr = []
    for i in dic:
        now = dic[i][1]
        now.sort(key=lambda x: [-x[0], x[1]])
        arr.append([dic[i][0], now])
    arr.sort(reverse=True)
    answer = []
    for a, l in arr:
        if len(l) == 1:
            answer.append(l[0][1])
        else:
            answer.append(l[0][1])
            answer.append(l[1][1])
    return answer