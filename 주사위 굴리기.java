import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[][] dices ={ {},
                    {4,2,1,6,5,3},//동
                    {3,2,6,1,5,4},//서
                    {5,1,3,4,6,2}, //북
                    {2,6,3,4,1,5}, //남
                           };
    static int[][] move = {{},{0,1},{0,-1},{-1,0},{1,0}};
    static int[][] map;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int y = sc.nextInt();
        int x = sc.nextInt();
        int k = sc.nextInt();
        int[] dice = new int[6];
        map = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]= sc.nextInt();
            }
        }
        for(int i=0;i<k;i++){
            int dir = sc.nextInt();

            int ny = y+move[dir][0],nx = x+move[dir][1];
            if(ny<0||ny>=n||nx<0||nx>=m) continue;
            int[] new_dice = new int[6];
            for(int pick=0;pick<6;pick++){
                new_dice[pick] = dice[dices[dir][pick]-1];
            }
            dice = new_dice;
            y=ny;
            x=nx;

            if(map[y][x]==0){
                map[y][x]= dice[5];
            }
            else{
                dice[5]=map[y][x];
                map[y][x]=0;
            }
            System.out.println(dice[0]);
            
        }
        
    }
}