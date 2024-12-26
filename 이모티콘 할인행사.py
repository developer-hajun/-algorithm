from itertools import product


def solution(users, emoticons):
    answer = [0, 0]
    for case in product([10, 20, 30, 40], repeat=len(emoticons)):
        emoticon = []
        for i in range(len(case)):
            emoticon.append([case[i], emoticons[i] - emoticons[i] * (case[i] / 100)])

        now_answer = [0, 0]

        for per, money in users:
            value = 0
            for ep, mo in emoticon:
                if ep >= per:
                    value += mo
            if value >= money:
                now_answer[0] += 1
            else:
                now_answer[1] += value
        if answer[0] < now_answer[0]:
            answer = now_answer
        elif answer[0] == now_answer[0] and answer[1] < now_answer[1]:
            answer = now_answer
    return answer

