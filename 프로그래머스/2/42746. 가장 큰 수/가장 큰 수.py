from itertools import combinations,permutations
def solution(numbers):
    str_list = list(map(str,numbers))
    str_list.sort(key = lambda x:x*3,reverse = True)
    
    return str(int(''.join(str_list)))