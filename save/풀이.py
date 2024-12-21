def coin_change(coins, amount):
    # amount + 1로 초기화하여 불가능한 경우를 나타냄
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0  # 0원을 만들기 위한 동전의 수는 0개

    for coin in coins:
        for x in range(coin, amount + 1):
            dp[x] = min(dp[x], dp[x - coin] + 1)
    print(dp)
    return dp[amount] if dp[amount] != float('inf') else -1

# 동전 종류와 잔돈
coins = [1, 5, 6, 9]
amount = 13


# 결과 출력
result = coin_change(coins, amount)

print(f"잔돈 {amount}을 만들기 위한 최소 동전의 수: {result}")
