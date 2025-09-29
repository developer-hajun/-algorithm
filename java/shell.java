import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class shell {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = numbers.size();
        int[] gaps = {100, 50, 10, 5, 1};
        long beforeTime = System.nanoTime();
        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                int currentElement = numbers.get(i);
                int j = i;

                while (j >= gap && numbers.get(j - gap) > currentElement) {
                    numbers.set(j, numbers.get(j - gap));
                    j -= gap;
                }
                numbers.set(j, currentElement);
            }
        }
        long afterTime = System.nanoTime();
        long l = afterTime - beforeTime;
        System.out.println("running time: "+l+"nanosec");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("shell_output.txt"))) {
            for (int number : numbers) {
                bw.write(String.valueOf(number));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}