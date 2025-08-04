import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    static int num=0;
    static List<List<Integer>> answer = new ArrayList<>();

    public static void dfs(int start,int level){
        int left = tree.get(start).get(0);
        int right = tree.get(start).get(1);
        if(left!=-1) dfs(left,level+1);
        num+=1;
        while(level>=answer.size()) answer.add(new ArrayList<>());
        answer.get(level).add(num);
        if(right!=-1) dfs(right,level+1);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());  
        int[] find_parent = new int[n + 1];

        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            String[] now = sc.nextLine().split(" "); 
            int start = Integer.parseInt(now[0]);
            int left = Integer.parseInt(now[1]);
            int right = Integer.parseInt(now[2]);
            tree.get(start).add(left);
            tree.get(start).add(right);
            if (left != -1) find_parent[left] += 1;
            if (right != -1) find_parent[right] += 1;
        }

        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (find_parent[i] == 0) {
                root = i;
                break;
            }
        }
        dfs(root,1);//시작,현재 번호,깊이
        int m_level = -1;
        int m_weight = -1;
        for(int level=1;level<answer.size();level++){
            List<Integer> now = answer.get(level);
            int value = now.get(now.size()-1)-now.get(0)+1;
            if(value>m_weight){
                m_level=level;
                m_weight=value;
            }
        }
        System.out.println(m_level+" "+m_weight);
    }
}
