class Solution:
    def longestPalindromicSubsequence(self, s: str, k: int) -> int:

        n = len(s)

        def dp(i, j, k_left):
            if i > j:
                return 0
            if i == j:
                return 1
            if s[i] == s[j]:
                return 2 + dp(i + 1, j - 1, k_left)
            else:
                min_ops = min((ord(s[i]) - ord(s[j])) % 26, (ord(s[j]) - ord(s[i])) % 26)
                res = max(dp(i + 1, j, k_left), dp(i, j - 1, k_left))  # skip one side
                if k_left >= min_ops:
                    res = max(res, 2 + dp(i + 1, j - 1, k_left - min_ops))
                return res

        return dp(0, n - 1, k)
