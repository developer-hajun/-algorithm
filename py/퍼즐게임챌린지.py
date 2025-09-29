import copy
def solution(diffs, times, limit):
    min_value,max_value = 1,max(diffs)
    while min_value<max_value:
        answer=(min_value+max_value)//2
        time = 0
        for i in range(len(diffs)):
            if answer<diffs[i]:
                time+= ((times[i-1]+times[i])*(diffs[i]-answer))+times[i]
            else:
                time+=times[i]
            if time>limit:
                break
        if time>limit:
            min_value = answer+1
        else:
            max_value = answer
    return min_value