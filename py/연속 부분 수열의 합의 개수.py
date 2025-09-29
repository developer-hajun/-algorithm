from itertools import combinations
import copy
def solution(elements):
    answer = 0
    arr=set()
    lens = len(elements)
    elements = elements*2
    for i in range(1,lens+1):
        for j in range(len(elements)):
            arr.add(sum(elements[j:j+i]))
    return len(arr)