import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Pair{
    int a;
    int b;
    Pair(int a,int b){
        this.a=a;
        this.b=b;
    }
    public String toString(){
        return a+":"+b;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pair[] p = new Pair[6];
        for(int i=0;i<6;i++){
            p[i] = new Pair(sc.nextInt(),sc.nextInt());
        }
        Pair pick_y = new Pair(0,0);
        int p_y = -1;
        Pair pick_x = new Pair(0,0);
        int p_x = -1;
        for(int i=0;i<6;i++){
            if(p[i].a==3 || p[i].a==4){
                if(p[i].b>pick_y.b){
                    pick_y=p[i];
                    p_y = i;
                }
            }
            else{
                if(p[i].b>pick_x.b){
                    pick_x=p[i];
                    p_x = i;
                }
            }
        }
        int pick_len_x = 210000000;
        for(int k : new int[]{p_y-1,p_y+1}){
            if(k<0) k = 5;
            else if(k==6) k=0;
            pick_len_x = Math.min(pick_len_x,p[k].b);
        }
        int pick_len_y = 210000000;
        for(int k : new int[]{p_x-1,p_x+1}){
            if(k<0) k = 5;
            else if(k==6) k=0;
            pick_len_y = Math.min(pick_len_y,p[k].b);
        }
        int big = pick_x.b*pick_y.b;
        int small = (pick_y.b-pick_len_y)*(pick_x.b-pick_len_x);
        System.out.println(n*(big-small));
    }

}
