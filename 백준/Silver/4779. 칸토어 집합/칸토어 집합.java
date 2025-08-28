import java.util.*;
import java.io.*;

class Main {

    public static void remove(char[] arr, int left, int right) {
        if(left==right) return;
        int size = (right-left+1)/3;
        int left_end = left+size-1;
        int right_start = right-size+1;
        remove(arr,left,left_end);
        remove(arr,right_start,right);
        for(int i=left+size;i<=right-size;i++){
            arr[i]=' ';
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int n = Integer.parseInt(input);
            int length = (int) Math.pow(3, n);
            char[] answer = new char[length];
            Arrays.fill(answer, '-');

            remove(answer, 0, length - 1);
            System.out.println(new String(answer));
        }
    }
}
