from collections import deque


def solution(alp, cop, problems):
    max_alp = 0
    max_cop = 0
    time = 0
    for a, b, c, d, e in problems:
        max_alp = max(max_alp, a)
        max_cop = max(max_cop, b)
        time += e
    # 목표 알고력
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    INF = float('inf')
    dp = [[INF] * (max_cop + 1) for _ in range(max_alp + 1)]
    dp[alp][cop] = 0

    for al in range(alp, max_alp + 1):
        for co in range(cop, max_cop + 1):
            if dp[al][co] == INF:
                continue
            if al + 1 <= max_alp:
                dp[al + 1][co] = min(dp[al + 1][co], dp[al][co] + 1)
            if co + 1 <= max_cop:
                dp[al][co + 1] = min(dp[al][co + 1], dp[al][co] + 1)

            for p_alp, p_cop, up_alp, up_cop, time in problems:
                if p_alp <= al and p_cop <= co:
                    now_alp = min(max_alp, al + up_alp)
                    now_cop = min(max_cop, co + up_cop)
                    dp[now_alp][now_cop] = min(dp[now_alp][now_cop], dp[al][co] + time)
    return dp[-1][-1]