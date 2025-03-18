def solution(fees, records):
    dic = {}
    answer = {}

    for i in records:
        time, num, command = i.split()
        h, m = time.split(':')
        h, m = int(h), int(m)
        if command == "IN":
            dic[num] = h * 60 + m
            if num not in answer:
                answer[num] = 0
        else:
            live = (h * 60 + m) - dic[num]
            answer[num] += live
            del (dic[num])
    for i in dic:
        answer[i] += (23 * 60 + 59) - dic[i]

    result = []
    bt, bm, ut, um = fees
    for i in answer:
        time = answer[i]
        if time <= bt:
            result.append([int(i), bm])
            print(i)
        else:
            money = bm
            time -= bt
            value = time // ut
            if time % ut != 0:
                value += 1
            money += value * um
            result.append([int(i), money])
    result.sort()
    ans = []
    for i, j in result:
        ans.append(j)
    return ans


