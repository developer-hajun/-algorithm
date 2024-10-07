import copy
import math

file = open('save/input_closest_pair.txt', 'r')

now = file.readlines()



arr = []
for line in now:
    line = line.replace('\n','')
    line = line.split("\t")
    arr.append(list(map(int,line)))
arr.sort()
index = 1
index_arr_x = []
index_arr_y = []
for x,y in arr:
    index_arr_x.append([index,x,y])
    index_arr_y.append([index, x, y])
    index += 1
index_arr_y.sort(key = lambda x: x[2])



def dist(now_value):
    answer = 99999
    for i in range(len(now_value)):
        for j in range(i+1,len(now_value)):
            p1x,p1y=now_value[i][1],now_value[i][2]
            p2x,p2y=now_value[j][1],now_value[j][2]
            answer =min(answer,math.sqrt(math.pow(p1x-p2x,2) + math.pow(p1y-p2y,2)))
    return answer
def dist2(now_value,dists):
    answer = 99999
    for i in range(len(now_value)):
        for j in range(i+1,len(now_value)):
            p1x,p1y=now_value[i][1],now_value[i][2]
            p2x,p2y=now_value[j][1],now_value[j][2]
            answer =min(answer,math.sqrt(math.pow(p1x-p2x,2) + math.pow(p1y-p2y,2)))
            if abs(p1y-p2y)>dists:
                break
    return answer

def devide(start,end):
    if end-start<=3:
        now = dist(index_arr_x[start:end])
        return now
    mid = (start+end)//2
    cpl = devide(start,mid)
    cpr = devide(mid,end)
    min_dist = min(cpl,cpr)
    mid_arr=[]
    mid_point = []
    if mid%2==0:#개수가 홀수인경우
        value1,value2=index_arr_x[mid][1],index_arr_x[mid+1][1]
        for i in range(start,end+1):
            if abs(value1-index_arr_x[i][1])<=min_dist:
                mid_arr.append(index_arr_x[i][0])
            elif abs(index_arr_x[i][1]-value2)<=min_dist:
                mid_arr.append(index_arr_x[i][0])

    else: #개수가 짝수인경우
        value = index_arr_x[mid][0]
        for i in range(start,end+1):
            if abs(value-index_arr_x[i][1])<=min_dist:
                mid_arr.append(index_arr_x[i][0])

    for index, x, y in index_arr_y:
        if index in mid_arr:
            mid_point.append((index, x, y))
        if len(mid_arr) == len(mid_point):
            break
    cpm = dist2(mid_point,min_dist)
    return min(cpl,cpr,cpm)

print(devide(0,index-2))