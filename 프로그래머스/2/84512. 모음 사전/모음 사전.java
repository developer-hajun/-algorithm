class Solution {
    static String[] vowels = {"A", "E", "I", "O", "U"};
    static int answer = 0;
    static boolean found = false;

    public int solution(String word) {
        dfs("", word);
        return answer;
    }

    private void dfs(String current, String target) {
        if (found) return;

        if (!current.equals("")) {
            answer++;
        }

        if (current.equals(target)) {
            found = true;
            return;
        }

        if (current.length() == 5) return;

        for (String v : vowels) {
            dfs(current + v, target);
        }
    }
}