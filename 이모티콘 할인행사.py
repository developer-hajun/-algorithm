from itertools import permutations, product


def solution(users, emoticons):
    answer = [0, 0]
    for discount in product([10, 20, 30, 40], repeat=len(emoticons)):
        plus = 0
        money = 0
        for user in users:
            emoticon_buy = 0
            for i in range(len(emoticons)):
                if discount[i] >= user[0]:
                    emoticon_buy += emoticons[i] * ((100 - discount[i]) / 100)

            if user[1] <= emoticon_buy:
                plus += 1
            else:
                money += emoticon_buy
        if answer[0] < plus:
            answer = [plus, int(money)]
        elif answer[0] == plus:
            if answer[1] < money:
                answer = [plus, int(money)]

    return answer