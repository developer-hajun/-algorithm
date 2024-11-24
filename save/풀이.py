from collections import defaultdict,Counter

def solution(k, tangerine):
    gul = defaultdict(list)
    arr = []
    for key,value in Counter(tangerine).items():
        arr.append([value,key])
    arr.sort()
    count = 0
    answer = 0
    while count<k:
        value ,key = arr.pop()
        answer+=1
        count+=value
    return answer