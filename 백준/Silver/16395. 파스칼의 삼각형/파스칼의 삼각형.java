import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken()),k=Integer.parseInt(st.nextToken());
        List<Integer> arr = new ArrayList<>();
        arr.add(1);

        for(int i=2;i<=n;i++){
            List<Integer> nextArr = new ArrayList<>();
            nextArr.add(arr.get(0));
            for(int e=1;e<arr.size();e++)
                nextArr.add(arr.get(e-1)+arr.get(e));
            nextArr.add(arr.get(arr.size()-1));
            arr=nextArr;
        }
        System.out.println(arr.get(k-1));

    }
}