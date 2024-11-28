from itertools import product
def solution(users, emoticons):
    answer = []
    for case in product([10,20,30,40],repeat = len(emoticons)):
        arr = []
        for i in range(len(emoticons)):
            value = emoticons[i]-(emoticons[i]*(case[i]/100))
            arr.append(value)
        user = [0]*len(users)
        for i in range(len(arr)):
            for j in range(len(user)):
                if case[i]<users[j][0]:
                    continue
                user[j]+=arr[i]
        plus=0
        count = 0
        for i in range(len(user)):
            if user[i]>=users[i][1]:
                plus+=1
            else:
                count+=user[i]
        answer.append([plus,count])
    answer.sort(key=lambda x:[-x[0],-x[1]])
    return answer[0][0],int(answer[0][1])