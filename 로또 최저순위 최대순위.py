def solution(lottos, win_nums):
    value = len(set(win_nums)-set(lottos))-lottos.count(0)
    a,b = min(6,1+value),min(6,1+len(set(win_nums)-set(lottos)))
    return a,b