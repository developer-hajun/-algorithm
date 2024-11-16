def solution(data, ext, val_ext, sort_by):
    k = {'code':0,'date':1,'maximum':2,'remain':3}
    value = k[ext]
    answer = []
    for i in data:
        if i[value]<val_ext:
            answer.append(i)
    answer.sort(key = lambda x:x[k[sort_by]])
    return answer