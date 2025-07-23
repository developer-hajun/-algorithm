import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static char[] arr;
    static char[] use;
    static int l,c;
    static String word = "aeiou";
    public static void dfs(int ja,int mo,int next,int count){
        if(count==l){
            if(mo<1 || ja<2 ) return;
            System.out.println(new String(arr));
            return;
        }
        for(int i=next;i<use.length;i++){
            char index = use[i];
            arr[count] = index;
            if(word.indexOf(index)!=-1){
                dfs(ja,mo+1,i+1,count+1);
            }
            else{
                dfs(ja+1,mo,i+1,count+1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l=sc.nextInt();c=sc.nextInt();
        arr = new char[l];
        use = new char[c];
        for(int i=0;i<c;i++) use[i] = sc.next().charAt(0);
        Arrays.sort(use);
        dfs(0,0,0,0);//자음개수,모음개수,다음위치,카운트

    }
}