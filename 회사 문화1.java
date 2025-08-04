import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static List<List<Integer>> tree=new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt(),m=sc.nextInt();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        int root=-1;
        for(int i=1;i<=n;i++){
            int val = sc.nextInt();
            if(val==-1){
                root=i;
                continue;
            }
            tree.get(val).add(i);
        }
        int[] answer = new int[n+1];
        for(int i=0;i<m;i++){
            int q= sc.nextInt();int e=sc.nextInt();
            answer[q]+=e;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        int value=0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next: tree.get(now)){
                answer[next]+=answer[now];
                queue.add(next);
            }
        }
        for(int i=1;i<=n;i++){
            System.out.print(answer[i]+" ");
        }
    }
}