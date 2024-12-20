import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        String s = "strong";
        String t = "stone";

        // ε 추가
        char[] sArray = new char[s.length() + 2];
        sArray[0] = 'T';
        sArray[1] = 'ε';
        for (int i = 0; i < s.length(); i++) {
            sArray[i + 2] = s.charAt(i);
        }

        char[] tArray = new char[t.length() + 2];
        tArray[0] = 'S';
        tArray[1] = 'ε';
        for (int i = 0; i < t.length(); i++) {
            tArray[i + 2] = t.charAt(i);
        }

        int[][] arr = new int[sArray.length][tArray.length];

        for (int i = 1; i < tArray.length; i++) {
            arr[0][i] = i-1;
        }
        for (int i = 1; i < sArray.length; i++) {
            arr[i][0] = i-1;
        }

        for (int i = 1; i < sArray.length; i++) {
            for (int j = 1; j < tArray.length; j++) {
                int alphas = (tArray[j] == sArray[i]) ? 0 : 1;
                arr[i][j] = Math.min(Math.min(arr[i - 1][j] + 1, arr[i][j - 1] + 1), arr[i - 1][j - 1] + alphas);
            }
        }
        System.out.print("  ");
        for (char a : tArray){
            System.out.print(a+" ");
        }
        System.out.println();
        for (int i = 0; i < sArray.length; i++) {
            System.out.print(sArray[i]+" ");
            for(int in : arr[i]) System.out.print(in+" ");
            System.out.println();
        }
    }
}
