def solution(enroll, referral, seller, amount):
    dic = {}
    money = {}
    for i in range(len(enroll)):
        dic[enroll[i]] = referral[i]
        money[enroll[i]]=0
    for i in range(len(seller)):
        now_sell,moneys = seller[i],amount[i]*100
        while now_sell!='-':
            if moneys<10:
                money[now_sell]+=moneys
                break
            minus = moneys//10
            money[now_sell]+= moneys-minus
            moneys=minus
            now_sell = dic[now_sell]
    arr=[]
    for sell in enroll:
        arr.append(money[sell])
    return arr