
effectiveness = {}

def date_to_int(date):
    year ,month ,day = map(int ,date.split('.'))
    return year *12*28 +month*28+day

def period(date ,term):
    date = date_to_int(date)
    date+=effectiveness[term ] *28
    return date

def solution(today, terms, privacies):
    today = date_to_int(today)
    for term in terms:
        a ,b = term.split()
        effectiveness[a ] =int(b)
    privacies = [period(a.split()[0] ,a.split()[1]) for a in privacies]
    answer = []
    for count ,date in enumerate(privacies):
        if date<=today:
            answer.append(count +1)
    return answer
