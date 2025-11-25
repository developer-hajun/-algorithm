import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static class node{
        int[] nums;
        int value;
        public node(int[] nums_r ,int value_r){
            nums =nums_r;
            value=value_r;
        }
    }
    public static void main(String[] args) throws Exception {

        //----------------------------------------
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        st= new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) num[i]= Integer.parseInt(st.nextToken());

        //----------------------------------------
        HashMap<String,Integer> map = new HashMap<>();
        map.put(Arrays.toString(num),0);

        //----------------------------------------
        int e = Integer.parseInt(br.readLine());
        List<int[]> order = new ArrayList<>();
        for(int i=0;i<e;i++) {
            st= new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int value = Integer.parseInt(st.nextToken());
            order.add(new int[]{start,end,value});
        }
        //----------------------------------------

        PriorityQueue<node> queue = new PriorityQueue<>((o1,o2)->{return o1.value-o2.value;});
        queue.add(new node(num,0));
        while(!queue.isEmpty()){
            node now= queue.poll();
            int[] nums = now.nums;
            int value = now.value;
            for(int[] command : order){
                int[] clones = nums.clone();
                int temp = clones[command[0]];
                clones[command[0]]= clones[command[1]];
                clones[command[1]] = temp;
                String v = Arrays.toString(clones);
                int next_val=value+command[2];
                if(map.containsKey(v)&&map.get(v)<=next_val) continue;
                map.put(v,next_val);
                queue.add(new node(clones,next_val));
            }
        }
         //----------------------------------------
        Arrays.sort(num);
        if(map.containsKey(Arrays.toString(num))){
            System.out.println(map.get(Arrays.toString(num)));
        }
        else{
            System.out.println(-1);
        }
    }
}
