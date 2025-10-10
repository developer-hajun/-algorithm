import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

     
        long atk = Long.parseLong(st.nextToken());

        long left = 1L; 
        long right = Long.MAX_VALUE;

        int[][] commands = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(st.nextToken());
            commands[i][1] = Integer.parseInt(st.nextToken());
            commands[i][2] = Integer.parseInt(st.nextToken());
        }

        long answer = right;

        while (left <= right) {
            long max_hp = left + (right - left) / 2;

   
            long now_atk = atk;
            long now_hp = max_hp;
            boolean check = true;

            for(int[] command : commands) {
                int t = command[0];

   
                long a = command[1];
                long h = command[2];

                if(t == 1){ 

        
                    long attack = (long)Math.ceil((double)h/now_atk);

                    now_hp -= (attack - 1) * a;

                } else { 
                    now_atk += a;
                    
                    now_hp = Math.min(now_hp + h, max_hp);
                }

                if(now_hp <= 0){
                    check = false;
                    break;
                }
            }

            if(check){
                answer = max_hp;
                right = max_hp-1;
            }
            else{
                left = max_hp + 1; 
            }
        }
        System.out.println(answer);
    }
}