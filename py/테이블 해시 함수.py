def solution(data, col, row_begin, row_end):
    data.sort(key = lambda x:[x[col-1],-x[0]])
    data = data[row_begin-1:row_end]
    ans = []
    for i in range(row_begin,row_end+1):
        value = data[i-row_begin]
        d = sum(k%i for k in value)
        ans.append(d)
    result = 0
    for i in range(len(ans)):
        result ^=ans[i]
    return result