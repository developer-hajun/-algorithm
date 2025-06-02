def solution(cap, n, deliveries, pickups):
    answer = 0
    while deliveries and not deliveries[-1]: deliveries.pop()
    while pickups and not pickups[-1]: pickups.pop()
    while deliveries or pickups:
        move = max(len(deliveries), len(pickups))

        m = cap
        while deliveries and deliveries[-1] <= m:
            m -= deliveries[-1]
            deliveries.pop()
        if deliveries:
            deliveries[-1] -= m
        m = cap
        while pickups and pickups[-1] <= m:
            m -= pickups[-1]
            pickups.pop()
        if pickups:
            pickups[-1] -= m
        answer += move * 2
    return answer