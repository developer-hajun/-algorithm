

def remove(start,end,n):
    if n==0:
        return
    len_section = (end+1-start)//3
    remove(start,start+len_section-1,n-1)
    for i in range(start+len_section,start+len_section*2):
        answer[i]=' '
    remove(start+(len_section*2),end,n-1)



while True:
    try:
        n = int(input())
        answer = ['-']*(pow(3,n))
        remove(0,len(answer)-1,n)
        print(''.join(answer))
    except:
        break
