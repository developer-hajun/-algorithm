import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class bubble {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        File file = new File("input.txt");

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    arr.add(Integer.parseInt(line));
                }
            }
            int[] arr2 = arr.stream().mapToInt(Integer::intValue).toArray();
            long beforeTime = System.nanoTime();
            for(int pass =0;pass<arr2.length;pass++){
                for(int i=1;i<arr2.length-pass;i++){
                    if(arr2[i]<arr2[i-1]){
                        int temp = arr2[i];
                        arr2[i] = arr2[i-1];
                        arr2[i-1]= temp;
                    }
                }
            }
            long afterTime = System.nanoTime();
            long l = afterTime - beforeTime;
            System.out.println("running time: "+l+"nanosec");
            FileWriter fileWriter = new FileWriter("bubble_output.txt");
            for(int i: arr2) {
                fileWriter.write(( i +"\n"));
            }
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}