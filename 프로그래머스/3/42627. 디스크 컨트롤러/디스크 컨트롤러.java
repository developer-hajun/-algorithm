import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[][] job) {
        
        
        PriorityQueue<int[]> readyqueue = new PriorityQueue<>((o1,o2)->{
            return o1[1]-o2[1];
        });
        for(int i=0;i<job.length;i++){
            readyqueue.add(new int[]{job[i][1],job[i][0],i});
        }
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            if(o1[0]!=o2[0]) return o1[0]-o2[0];
            if(o1[1]!=o2[1]) return o1[1]-o2[1];
            return o1[2]-o2[2];
        });
        
        int job_time=0;
        int answer=0;
        
        while(!readyqueue.isEmpty()){
            while(!readyqueue.isEmpty()&&readyqueue.peek()[1]<=job_time) 
                queue.add(readyqueue.poll());
            if(!queue.isEmpty()){
                int[] now = queue.poll();
               
                job_time+=now[0];
                
                answer+=(job_time-now[1]);
            }
            else job_time+=1;
        }
        
        while(!queue.isEmpty()){
            if(!queue.isEmpty()){
                int[] now = queue.poll();
                job_time+=now[0];
                answer+=(job_time-now[1]);
            }
            else job_time+=1;
        }
        
        
        
        
        return answer/job.length;
    }
}
//동작방식
// 작업 번호,요청시각,소요시간 저장하는 대기큐
// 대기큐가 비어있지않다면 가장 우선순위가 높은 작업을 대기큐에서 꺼내서 시킴
// -> 우선순위 : 작업 쇼요시간 -> 작업의 요청시각 ->  작업의 번호가 작은것
// 한번 작업을 시작하면 마칠떄까지 그것만함
// 하드 디스크가 마치는 시점 = 다른 작업 요청이 들어오는 시간과 겹침=> 대기큐에넣음
// 마치자 마자 시작한다고 가정
//대기큐 -> 작업하기 