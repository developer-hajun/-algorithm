import sys
def pa(pt):
    F=[0]*len(pt)
    F[0] = 0
    i = 1
    j = 0
    while i<len(pt):
        if pt[i] == pt[j]:
            F[i] = j + 1
            i +=1
            j +=1
        elif j > 0:
            j = F[j - 1]
        else:
            F[j] = 0
            i+=1
    return F
pat = sys.stdin.readline().rstrip()
ans = 0
for i in range(len(pat)):
    ans = max(ans, max(pa(pat[i:])))
print(ans)