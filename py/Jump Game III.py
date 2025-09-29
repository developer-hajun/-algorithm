class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        q = deque([start])
        visited = set()
        while q:
            idx = q.popleft()
            if arr[idx] == 0:
                return True

            if idx in visited:
                continue

            visited.add(idx)

            if idx + arr[idx] < len(arr):
                q.append(idx + arr[idx])

            if idx - arr[idx] >= 0:
                q.append(idx - arr[idx])

        return False