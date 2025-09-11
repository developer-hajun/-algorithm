

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Set<Integer> set = new HashSet<>();
        for (int t = 0; t < n; t++) {
            boolean check = false;

            String now = br.readLine();
            String[] dan = now.split(" ");
            for (int i = 0; i < dan.length; i++) {

                char find = dan[i].charAt(0);
                int value = -1;

                value = Character.toLowerCase(find) - 'a';

                if (!set.contains(value)) {
                    set.add(value);

                    for (int e = 0; e < i; e++) {
                        System.out.print(dan[e] + " ");
                    }

                    System.out.print("[" + find + "]");
                    

                    for (int e = 1; e < dan[i].length(); e++) {
                        System.out.print(dan[i].charAt(e));
                    }

                    System.out.print(" ");

                    for (int e = i + 1; e < dan.length; e++) {
                        System.out.print(dan[e] + " ");
                    }

                    System.out.println();
                    check = true;
                    break;
                }

            }

            if (check) continue;


            for (int i = 0; i < now.length(); i++) {

                char find = now.charAt(i);
                int value = -1;


                if (Character.isLetter(find)) { 
                    value = Character.toLowerCase(find) - 'a';

                    if (!set.contains(value)) {
                        set.add(value);
                        check = true;
                        for (int e = 0; e < i; e++) {
                            System.out.print(now.charAt(e));
                        }
                        System.out.print("[" + find + "]");
                        for (int e = i + 1; e < now.length(); e++) {
                            System.out.print(now.charAt(e));
                        }
                        System.out.println();
                        break;
                    }
                }
            }

            if (check) continue;

 
            System.out.println(now);
        }
    }
}
