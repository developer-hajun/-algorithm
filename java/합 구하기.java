import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] arr = new int[t+1];
        for(int i=1;i<=t;i++){
            arr[i]=sc.nextInt()+arr[i-1];
        }
        t = sc.nextInt();
        for(int i=0;i<t;i++){
            int left = sc.nextInt();
            int right = sc.nextInt();
            System.out.println(arr[right]-arr[left-1]);
        }
    }
} 