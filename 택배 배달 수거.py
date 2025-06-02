from itertools import permutations, combinations_with_replacement, product


def solution(users, emoticons):
    answer = [-1, -1]
    for percent in product([10, 20, 30, 40], repeat=len(emoticons)):
        emoticon = [int(emoticons[i] - (emoticons[i] * percent[i] / 100)) for i in range(len(emoticons))]

        now = [0, 0]
        for per, money in users:
            use_money = 0
            for case in range(len(emoticon)):
                if percent[case] >= per:
                    use_money += emoticon[case]
            if use_money >= money:
                now[0] += 1
            else:
                now[1] += use_money

        if answer[0] < now[0]:
            answer = now
        elif answer[0] == now[0] and answer[1] < now[1]:
            answer = now
    return answer



