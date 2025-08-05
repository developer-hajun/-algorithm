import java.util.*;

class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] trees = new int[n];
            int max = 0;
            for (int i = 0; i < n; i++) {
                trees[i] = sc.nextInt();
                max = Math.max(max, trees[i]);
            }
            int one = 0;
            int two = 0;
            for (int i = 0; i < n; i++) {
                int val =max-trees[i];
                one+=val%2;
                two+=val/2;
            }
           if(two > one) {
				while(Math.abs(two - one) > 1) {
					two--;
					one += 2;
				}
			}
			
			int result = 0;
			if(one > two) { 
				result = one * 2 - 1;
				
			} else if(two > one) { 
				result = two * 2;
				
			} else { 
				result = one + two;
			}
			
			System.out.println("#" + test_case + " " + result);
            
        }
    }
}
