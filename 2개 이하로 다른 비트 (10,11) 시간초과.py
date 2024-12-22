def solution(numbers):
    answer = []
    for number in numbers:
        bin1 = bin(number)[2:]
        st = number+1
        while True:
            bin2 = bin(st)[2:]
            max_len = max(len(bin1), len(bin2))
            bin1= bin1.zfill(max_len)
            bin2= bin2.zfill(max_len)
            count = 0
            for i in range(len(bin1)):
                if bin1[i]!=bin2[i]:
                    count+=1
                if count>2:
                    break
            if count<=2:
                answer.append(st)
                break
            st+=1
    return answer