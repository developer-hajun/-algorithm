import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[][] arr;
    static int n,m;

    static int[][] delete_dist = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[] answer = new int[]{0,0,0,0};
    public static void delete(int d,int s){
        int[] start = {n/2,n/2};
        for(int i=1;i<=s;i++){
            int ny = start[0]+delete_dist[d][0]*i;
            int nx = start[1]+delete_dist[d][1]*i;
            if(ny<0 || ny>=n || nx<0 || nx>=n) break;
            if(arr[ny][nx]==0) continue;
            //answer[arr[ny][nx]]+=1;
            arr[ny][nx]=0;
        }
    }
    static int[][] move_dist = {{0,-1},{1,0},{0,1},{-1,0}};
    public static void print(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void move(){
        Queue<int[]> stack = new LinkedList<>();
        int y =n/2,x = n/2;
        int[] move_s = {1,1,2,2};
        A : while(true){
            for(int i=0;i<4;i++){
                for(int j=1;j<=move_s[i];j++){
                    y += move_dist[i][0];
                    x += move_dist[i][1];
                    if(y<0 || y>=n || x<0|| x>=n) break A;
                    if(arr[y][x]==0) stack.add(new int[]{y,x});
                    else{
                        if(!stack.isEmpty()){
                            int[] m = stack.poll();
                            arr[m[0]][m[1]] = arr[y][x];
                            arr[y][x]=0;
                            stack.add(new int[]{y,x});
                        }
                    }
                }
                move_s[i]+=2;
            }
        }
    }

    public static boolean boom() {
        Stack<int[]> stack = new Stack<>();
        int y = n / 2, x = n / 2;
        boolean ans = false;


        int[] local_move_s = {1, 1, 2, 2};
        A: while (true) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= local_move_s[i]; j++) {
                    y += move_dist[i][0];
                    x += move_dist[i][1];
                    if (y < 0 || y >= n || x < 0 || x >= n) break A;

                    int value = arr[y][x];
                    if (value == 0) continue;

                    if (stack.isEmpty() || stack.peek()[0] == value) {
                        stack.add(new int[]{value, y, x});
                    } else {
                        if (stack.size() >= 4) {
                            ans=true;
                            while (!stack.isEmpty()) {
                                int[] pop = stack.pop();
                                answer[pop[0]] += 1;
                                arr[pop[1]][pop[2]] = 0;
                            }
                        } else {
                            stack.clear();
                        }
                        stack.add(new int[]{value, y, x});
                    }
                }
                local_move_s[i] += 2;
            }
        }

        // 마지막 남은 그룹 처리
        if (stack.size() >= 4) {
            ans=true;
            while (!stack.isEmpty()) {
                int[] pop = stack.pop();
                answer[pop[0]] += 1;
                arr[pop[1]][pop[2]] = 0;
            }
        }
        return ans;
    }

    public static void magic() {
        Stack<int[]> stack = new Stack<>();
        int y = n / 2, x = n / 2;
        Queue<Integer> ar=new LinkedList<>();

        int[] local_move_s = {1, 1, 2, 2};
        A: while (true) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= local_move_s[i]; j++) {
                    y += move_dist[i][0];
                    x += move_dist[i][1];
                    if (y < 0 || y >= n || x < 0 || x >= n) break A;

                    int value = arr[y][x];
                    if (value == 0) continue;

                    if (stack.isEmpty() || stack.peek()[0] == value) {
                        stack.add(new int[]{value, y, x});
                    } else {
                        ar.add(stack.size());
                        ar.add(stack.peek()[0]);
                        stack.clear();
                        stack.add(new int[]{value, y, x});
                    }
                }
                local_move_s[i] += 2;
            }
        }
        if(!stack.isEmpty()){
            ar.add(stack.size());
            ar.add(stack.peek()[0]);
        }
        y = n / 2; x = n / 2;
        arr = new int[n][n];
        arr[y][x]=-1;
        local_move_s = new int[]{1, 1, 2, 2};
        A: while (true) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= local_move_s[i]; j++) {
                    y += move_dist[i][0];
                    x += move_dist[i][1];

                    if (y < 0 || y >= n || x < 0 || x >= n||ar.isEmpty()) break A;
                    arr[y][x]=ar.poll();
                }
                local_move_s[i] += 2;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][n];
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                arr[y][x]=sc.nextInt();
            }
        }
        arr[n/2][n/2]=-1;
        for(int command=0;command<m;command++){
            int d = sc.nextInt()-1,s=sc.nextInt();
            delete(d,s); //삭제
            move(); // 움직이기
            while(true){
                boolean ch = boom();
                if(!ch) break;
                move();
            }//터트리기
            magic();
        }
        System.out.print(answer[1]+answer[2]*2+answer[3]*3);
    }
}