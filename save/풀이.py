def solution(n, words):
    answer = set()
    answer.add(words[0])
    for i in range(1,len(words)):
        if words[i] in answer or words[i-1][-1]!=words[i][0]:
            a=-1
            b=-1
            if (i+1)%n==0:
                a = (i+1)//n
                b=n
            else:
                a = ((i+1)//n)+1
                b = (i+1)%n
            return b,a
        answer.add(words[i])
    return [0,0]