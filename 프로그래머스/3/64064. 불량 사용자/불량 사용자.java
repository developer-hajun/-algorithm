import java.util.*;

class Solution {
    Set<Set<Integer>> result = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        Map<String, Integer> banCount = new HashMap<>();
        Map<String, ArrayList<Integer>> userCount = new HashMap<>();


        for (String id : banned_id) {
            banCount.put(id, banCount.getOrDefault(id, 0) + 1);
        }

   
        for (int u = 0; u < user_id.length; u++) {
            String id = user_id[u];
            for (String key : banCount.keySet()) {
                if (id.length() != key.length()) continue;
                boolean match = true;
                for (int i = 0; i < id.length(); i++) {
                    char id_ch = id.charAt(i);
                    char ban_ch = key.charAt(i);
                    if (ban_ch != '*' && id_ch != ban_ch) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    userCount.putIfAbsent(key, new ArrayList<>());
                    userCount.get(key).add(u);
                }
            }
        }


        List<List<Integer>> candidates = new ArrayList<>();
        for (String ban : banned_id) {
            candidates.add(userCount.getOrDefault(ban, new ArrayList<>()));
        }

        dfs(candidates, new HashSet<>(), 0);

        return result.size();
    }

    private void dfs(List<List<Integer>> candidates, Set<Integer> selected, int depth) {
        if (depth == candidates.size()) {
            result.add(new HashSet<>(selected));
            return;
        }

        for (int id : candidates.get(depth)) {
            if (!selected.contains(id)) {
                selected.add(id);
                dfs(candidates, selected, depth + 1);
                selected.remove(id);
            }
        }
    }
}
