n,k = map(int,input().split())

n_f = 1
for i in range(2,n+1):
    n_f*=i
k_f =1
for i in range(2,k+1):
    k_f*=i

n_k_f=1
for i in range(2,n-k+1):
    n_k_f*=i

answer = n_f//(k_f*n_k_f)
print(answer%10007)