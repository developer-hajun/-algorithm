def solution(cap, n, deliveries, pickups):
    p = sum(deliveries)
    pp = sum(pickups)
    answer = 0
    while deliveries or pickups:
        if p==0 and pp==0:
            break
        answer += max(len(deliveries), len(pickups)) * 2

        tmp = 0
        while deliveries and tmp + deliveries[-1] <= cap:
            p-=deliveries[-1]
            tmp += deliveries.pop()
        if deliveries:
            deliveries[-1] -= cap - tmp


        tmp = 0
        while pickups and tmp + pickups[-1] <= cap:
            pp-=pickups[-1]
            tmp += pickups.pop()
        if pickups:
            pickups[-1] -= cap - tmp

    return answer