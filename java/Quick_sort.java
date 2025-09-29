import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quick_sort {

    public static void q_sort(Integer[] list,int start,int end){
        int p = swap(list,start,end);
        if(start<p-1) q_sort(list,start,p-1);
        if (p<end) q_sort(list,p,end);
    }

    public static int swap(Integer[] list, int start, int end){
        int pivot = (start+end)/2;
        int value = list[pivot];

        while(start<=end) {
            while (list[start] < value) start += 1;
            while (list[end] > value) end -= 1;
            if (start <= end) {
                int v = list[start];
                list[start] = list[end];
                list[end] = v;
                start += 1;
                end -= 1;
            }
        }


        return start;


    }
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("inupt_sort.txt"));
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        while(sc.hasNextLine()) {
            int T = sc.nextInt();
            sc.nextLine();
            arr2.add(T);
        }
        Integer[] arr = arr2.toArray(Integer[]::new);
        q_sort(arr,0,arr.length-1);
        int now=0;
        for(Integer i : arr) {
            System.out.println(now+" : "+ i);
            now+=1;
        }
    }
}